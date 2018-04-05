package com.nyver.opengl.demo.game.entity;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.ObjLoader;
import com.nyver.opengl.demo.graphic.Mesh;
import com.nyver.opengl.demo.graphic.Texture;

public class EntityFactory {
    public Entity createCube(int x, int y, int z, float scale) throws Exception {
        // Create the Mesh
        Texture texture = new Texture("/textures/grassblock.png");
        Mesh mesh = ObjLoader.loadMesh("/models/cube.obj");
        mesh.setTexture(texture);
        Entity gameItem = new Cube(mesh);
        gameItem.setPosition(x, y, z);
        gameItem.setScale(scale);
        return gameItem;
    }
}
