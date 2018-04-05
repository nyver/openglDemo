package com.nyver.opengl.demo.game.entity;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.graphic.Mesh;

public class Asteroid extends Entity {
    public Asteroid(Mesh mesh, float speed) {
        super(mesh, speed);
    }

    @Override
    public void init() {

    }

    @Override
    public void input(Window window) {
        setPositionInc(0, -1, 0);
        setRotationInc(150, 150, 0);
    }
}
