package com.badlogic.pokemon.model;

import java.util.Random;

public class TileMap {

    private Tile[][] tiles;
    private int width, height;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                generateRandomTile(x, y);
            }
        }
    }

    public Tile getTileFormTileMap(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void generateRandomTile(int width, int height) {
        if (Math.random() > 0.5d) {
            tiles[width][height] = new Tile(TERRAIN.GRASS_1);
        } else {
            tiles[width][height] = new Tile(TERRAIN.GRASS_2);
        }
    }

}
