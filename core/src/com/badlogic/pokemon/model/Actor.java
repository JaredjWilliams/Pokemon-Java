package com.badlogic.pokemon.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.pokemon.util.AnimationSet;

public class Actor {

    private TileMap map;
    private float worldX, worldY;
    private int srcX, srcY;
    private int destX, destY;
    private float animationTimer;
    private float ANIMATION_TIME = 0.3f;
    private ACTOR_STATE state;
    private int x;
    private int y;
    private float walkTimer;
    private boolean isMoveRequestThisFrame;
    private DIRECTION facing;
    private AnimationSet animations;

    public enum ACTOR_STATE {
        WALKING,
        STANDING;
    }



    public Actor(TileMap map, int x, int y, AnimationSet animations) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.animations = animations;
        map.getTileFormTileMap(x, y).setActor(this);
        this.state = ACTOR_STATE.STANDING;
        this.facing = DIRECTION.SOUTH;

    }

    public void updateActor(float delta) {
        if (state == ACTOR_STATE.WALKING) {
            updateAnimationTimer(delta);
            updateWalkTimer(delta);
            updateWorldCoordinates();
            finishAnimation();
        }
        isMoveRequestThisFrame = false;
    }

    public void finishAnimation() {
        if (animationTimer > ANIMATION_TIME) {
            float leftOverTime = animationTimer - ANIMATION_TIME;
            walkTimer -= leftOverTime;
            finishMovement();
            moveRequestThisFrame();
        }
    }

    public void moveRequestThisFrame() {
        if (isMoveRequestThisFrame) {
            move(facing);
        } else {
            walkTimer = 0f;
        }
    }


    public void updateWorldCoordinates() {
        worldX = Interpolation.linear.apply(srcX, destX, animationTimer/ANIMATION_TIME);
        worldY = Interpolation.linear.apply(srcY, destY, animationTimer/ANIMATION_TIME);
    }

    public void updateAnimationTimer(float delta) {

        animationTimer += delta;
    }

    public void updateWalkTimer(float delta) {
        walkTimer += delta;
    }

    public boolean move(DIRECTION direction) {
        int nextStepXCoord = x + direction.getDx();
        int nextStepYCoord = y + direction.getDy();
        if (state == ACTOR_STATE.WALKING) {
            switchMoveRequestThisFrame(direction);
            return false;
        } else if (nextStepXCoord >= map.getWidth() || nextStepXCoord < 0 || nextStepYCoord >= map.getHeight() || nextStepYCoord < 0) {
            return false;
        } else if (map.getTileFormTileMap(nextStepXCoord, nextStepYCoord).getActor() != null) {
            return false;
        } else {
            initializeMovement(direction);
            moveActorToTile(direction.getDx(), direction.getDy());
            return true;
        }
    }

    public void switchMoveRequestThisFrame(DIRECTION direction) {
        if (facing == direction) {
            isMoveRequestThisFrame = true;
        }
    }

    private void initializeMovement(DIRECTION direction) {
        this.facing = direction;
        int nextStepXCoord = x + direction.getDx();
        int nextStepYCoord = y + direction.getDy();
        this.srcX = x;
        this.srcY = y;
        this.destX = nextStepXCoord;
        this.destY = nextStepYCoord;
        this.worldX = x;
        this.worldY = y;
        animationTimer = 0f;
        state = ACTOR_STATE.WALKING;
    }

    private void finishMovement() {
        state = ACTOR_STATE.STANDING;
        this.worldX = destX;
        this.worldY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;
    }

    private void moveActorToTile(int changeInX, int changeInY) {
        map.getTileFormTileMap(x, y).setActor(null);
        x += changeInX;
        y += changeInY;
        map.getTileFormTileMap(x, y).setActor(this);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public float getWorldX() { return worldX; }
    public float getWorldY() { return worldY; }
    public TextureRegion getSprite() {
        if (state == ACTOR_STATE.WALKING) {
            return animations.getWalking(facing).getKeyFrame(walkTimer);
        } else if (state == ACTOR_STATE.STANDING) {
            return animations.getStanding(facing);
        } else {
            return animations.getStanding(DIRECTION.SOUTH);
        }
    }

}
