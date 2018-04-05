package com.nyver.opengl.demo.game.entity;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.ObjLoader;
import com.nyver.opengl.demo.graphic.Mesh;
import com.nyver.opengl.demo.graphic.Texture;

public class EntityFactory {
    public Asteroid createAsteroid(float x, float y, float z, float scale) throws Exception {
        // Create the Mesh
        Texture texture = new Texture("/textures/grassblock.png");
        Mesh mesh = ObjLoader.loadMesh("/models/cube.obj");
        mesh.setTexture(texture);
        Asteroid gameItem = new Asteroid(mesh, 1f);
        gameItem.setPosition(x, y, z);
        gameItem.setScale(scale);
        return gameItem;
    }

    public Player createPlayer(float x, float y, float z, float scale) throws Exception {
        // Create the Mesh
        Texture texture = new Texture("/textures/grassblock.png");
        Mesh mesh = ObjLoader.loadMesh("/models/cube.obj");
        mesh.setTexture(texture);
        Player gameItem = new Player(mesh, 1f);
        gameItem.setPosition(x, y, z);
        gameItem.setScale(scale);
        return gameItem;
    }

    public Shot createShot(float x, float y, float z, float scale) throws Exception {
        // Create the Mesh
        Mesh mesh = ObjLoader.loadMesh("/models/cube.obj");
        Shot gameItem = new Shot(mesh, 2f);
        gameItem.setPosition(x, y, z);
        gameItem.setScale(scale);
        return gameItem;
    }
}
