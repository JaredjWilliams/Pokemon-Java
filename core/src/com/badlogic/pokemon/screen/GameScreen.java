package com.badlogic.pokemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.pokemon.Pokemon;
import com.badlogic.pokemon.Settings;
import com.badlogic.pokemon.controller.PlayerController;
import com.badlogic.pokemon.model.Actor;
import com.badlogic.pokemon.model.Camera;
import com.badlogic.pokemon.model.TERRAIN;
import com.badlogic.pokemon.model.TileMap;
import com.badlogic.pokemon.util.AnimationSet;

public class GameScreen extends AbstractScreen {

    private PlayerController controller;
    private Texture redStandSouth;
    private SpriteBatch batch;
    private Texture grass1;
    private Texture grass2;
    private Camera camera;
    private Actor player;
    private TileMap map;

    public GameScreen(Pokemon app) {
        super(app);
        camera = new Camera();
        redStandSouth = new Texture("unpacked/red_standing_south.png");
        grass1 = new Texture("grass_1.png");
        grass2 = new Texture("wild_grass_1.png");
        batch = new SpriteBatch();

        TextureAtlas atlas = app.getAssetManager().get("packed/red_walking.atlas", TextureAtlas.class);

        AnimationSet animations = generateAnimationSet(atlas);

        map = new TileMap(10, 10);
        player = new Actor(map,1, 1, animations);
        controller = new PlayerController(player);
    }

    private AnimationSet generateAnimationSet(TextureAtlas atlas) {

        Animation<TextureRegion> walkNorth = new Animation(0.3f/2f, atlas.findRegions("red_walking_north"));
        Animation<TextureRegion> walkSouth = new Animation(0.3f/2f, atlas.findRegions("red_walking_south"));
        Animation<TextureRegion> walkEast = new Animation(0.3f/2f, atlas.findRegions("red_walking_east"));
        Animation<TextureRegion> walkWest = new Animation(0.3f/2f, atlas.findRegions("red_walking_west"));

        walkNorth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        walkSouth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        walkEast.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        walkWest.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        return new AnimationSet(
                walkNorth,
                walkSouth,
                walkEast,
                walkWest,
                atlas.findRegion("red_standing_north"),
                atlas.findRegion("red_standing_south"),
                atlas.findRegion("red_standing_east"),
                atlas.findRegion("red_standing_west")
        );
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        player.updateActor(delta);
        camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);
        batch.begin();

        float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight()/2 - camera.getCameraY()*Settings.SCALED_TILE_SIZE;

        for(int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Texture render;
                if (map.getTileFormTileMap(x, y).getTerrain() == TERRAIN.GRASS_1) {
                    render = grass1;
                } else {
                    render = grass2;
                }
                batch.draw(render,
                        worldStartX + x * Settings.SCALED_TILE_SIZE,
                        worldStartY + y * Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE);
            }
        }

        batch.draw(player.getSprite(),
                worldStartX + player.getWorldX() * Settings.SCALED_TILE_SIZE,
                worldStartY + player.getWorldY() * Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE*1.5f);
        batch.end();
    }

    private void renderTileMap() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
