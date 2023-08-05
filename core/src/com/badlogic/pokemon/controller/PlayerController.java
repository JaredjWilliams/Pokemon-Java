package com.badlogic.pokemon.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.pokemon.model.Actor;
import com.badlogic.pokemon.model.DIRECTION;

public class PlayerController extends InputAdapter {

    private Actor player;
    private boolean up, down, left, right;

    public PlayerController(Actor p) {
        this.player = p;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            up = true;
        }
        if (keycode == Input.Keys.DOWN) {
            down = true;
        }
        if (keycode == Input.Keys.LEFT) {
            left = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            right = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            up = false;
        }
        if (keycode == Input.Keys.DOWN) {
            down = false;
        }
        if (keycode == Input.Keys.LEFT) {
            left = false;
        }
        if (keycode == Input.Keys.RIGHT) {
            right = false;
        }
        return false;
    }

    public void update(float delta) {
        if (up) {
            player.move(DIRECTION.NORTH);
            return;
        }
        if (down) {
            player.move(DIRECTION.SOUTH);
            return;
        }
        if (left) {
            player.move(DIRECTION.WEST);
            return;
        }
        if (right) {
            player.move(DIRECTION.EAST);
            return;
        }
    }
}
