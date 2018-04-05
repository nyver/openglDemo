package com.nyver.opengl.demo.game.entity;

import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.graphic.Mesh;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Cube {
    public Player(Mesh mesh, float speed) {
        super(mesh, speed);
    }

    @Override
    public void input(Window window) {
        setPositionInc(0,0, 0);
        setRotation(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            setPositionInc(0, 1, 0);
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            setPositionInc(0, -1, 0);
        } else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            setPositionInc(-1, 0, 0);
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            setPositionInc(1, 0, 0);
        }
    }
}
