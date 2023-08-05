package com.badlogic.pokemon.util;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.pokemon.model.DIRECTION;

import java.util.HashMap;
import java.util.Map;

public class AnimationSet {

    private Map<DIRECTION, Animation<TextureRegion>> walking;
    private Map<DIRECTION, TextureRegion> standing;

    public AnimationSet(Animation<TextureRegion> walkNorth,
                        Animation<TextureRegion> walkSouth,
                        Animation<TextureRegion> walkEast,
                        Animation<TextureRegion> walkWest,
                        TextureRegion standingNorth,
                        TextureRegion standingSouth,
                        TextureRegion standingEast,
                        TextureRegion standingWest) {
        walking = new HashMap<DIRECTION, Animation<TextureRegion>>();
        walking.put(DIRECTION.NORTH, walkNorth);
        walking.put(DIRECTION.SOUTH, walkSouth);
        walking.put(DIRECTION.EAST, walkEast);
        walking.put(DIRECTION.WEST, walkWest);
        standing = new HashMap<DIRECTION, TextureRegion>();
        standing.put(DIRECTION.NORTH, standingNorth);
        standing.put(DIRECTION.SOUTH, standingSouth);
        standing.put(DIRECTION.EAST, standingEast);
        standing.put(DIRECTION.WEST, standingWest);
    }

    public Animation<TextureRegion> getWalking(DIRECTION direction) {
        return walking.get(direction);
    }

    public TextureRegion getStanding(DIRECTION direction) {
        return standing.get(direction);
    }

}
