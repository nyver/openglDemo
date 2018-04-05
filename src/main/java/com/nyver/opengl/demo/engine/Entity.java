package com.nyver.opengl.demo.engine;

import com.nyver.opengl.demo.graphic.Mesh;
import org.joml.Vector3f;

public abstract class Entity {

    private int displxInc = 0;

    private int displyInc = 0;

    private int displzInc = 0;

    private int scaleInc = 0;

    private final Mesh mesh;

    private final Vector3f position;

    private float scale;

    private final Vector3f rotation;

    public Entity(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
    }

    public abstract void init();

    public abstract void input(Window window);

    public abstract void update();

    public abstract void render();

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public int getDisplxInc() {
        return displxInc;
    }

    public void setDisplxInc(int displxInc) {
        this.displxInc = displxInc;
    }

    public int getDisplyInc() {
        return displyInc;
    }

    public void setDisplyInc(int displyInc) {
        this.displyInc = displyInc;
    }

    public int getDisplzInc() {
        return displzInc;
    }

    public void setDisplzInc(int displzInc) {
        this.displzInc = displzInc;
    }

    public int getScaleInc() {
        return scaleInc;
    }

    public void setScaleInc(int scaleInc) {
        this.scaleInc = scaleInc;
    }
}

