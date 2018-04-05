package com.nyver.opengl.demo.game.entity;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.graphic.Mesh;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;

public class Cube extends Entity {
    public Cube(Mesh mesh) {
        super(mesh);
    }

    @Override
    public void init() {

    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update() {
        // Update position
        Vector3f itemPos = getPosition();
        float posx = itemPos.x + getDisplxInc() * 0.01f;
        float posy = itemPos.y + getDisplyInc() * 0.01f;
        float posz = itemPos.z + getDisplzInc() * 0.01f;
        setPosition(posx, posy, posz);

        // Update scale
        float scale = getScale();
        scale += getScaleInc() * 0.05f;
        if (scale < 0) {
            scale = 0;
        }
        setScale(scale);

        // Update rotation angle
        float rotation = getRotation().x + 1.5f;
        if (rotation > 360) {
            rotation = 0;
        }
        setRotation(rotation, rotation, rotation);
    }

    @Override
    public void render() {

    }


}
