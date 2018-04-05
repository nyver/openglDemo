package com.nyver.opengl.demo.game.entity;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.graphic.Mesh;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {

    private boolean reloaded = true;
    private boolean shot = false;

    public Player(Mesh mesh, float speed) {
        super(mesh, speed);
    }

    @Override
    public void init() {
        
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

        if (window.isKeyPressed(GLFW_KEY_SPACE)
                && reloaded
                && !shot) {
            shot = true;
            reloaded = false;
        }
        if (window.isKeyReleased(GLFW_KEY_SPACE)) {
            reloaded = true;
        }

    }

    public boolean isShot() {
        return shot && reloaded;
    }

    public void fire() {
        shot = false;
    }
}
