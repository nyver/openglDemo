package com.nyver.opengl.demo.engine;

import com.nyver.opengl.demo.graphic.Mesh;
import org.joml.Vector3f;

public abstract class Entity {

    protected final AABB aabb;

    private final float speed;

    private final Mesh mesh;

    private final Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f positionInc = new Vector3f(0, 0, 0);

    private float scale = 1;
    private int scaleInc = 0;

    private final Vector3f rotation = new Vector3f(0, 0, 0);;
    private Vector3f rotationInc = new Vector3f(0, 0, 0);;

    public Entity(Mesh mesh, float speed) {
        this.mesh = mesh;
        this.speed = speed;
        this.aabb = new AABB(this);
    }

    public abstract void init();

    public abstract void input(Window window);

    public void update() {
        // Update position
        Vector3f itemPos = getPosition();
        Vector3f itemPosInc = getPositionInc();
        float posX = itemPos.x + itemPosInc.x * 0.01f;
        float posY = itemPos.y + itemPosInc.y * 0.01f;
        float posZ = itemPos.z + itemPosInc.z * 0.01f;
        setPosition(posX, posY, posZ);

        // Update scale
        float scale = getScale();
        scale += getScaleInc() * 0.05f;
        if (scale < 0) {
            scale = 0;
        }
        setScale(scale);

        // Update rotation angle
        Vector3f rotation = getRotation();
        Vector3f rotationInc = getRotationInc();
        float rotationX = rotation.x + rotationInc.x * 0.01f;
        float rotationY = rotation.y + rotationInc.y * 0.01f;
        float rotationZ = rotation.z + rotationInc.z * 0.01f;
        setRotation(rotationX, rotationY, rotationZ);

        aabb.update(this);
    }

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

    public Vector3f getPositionInc() {
        return positionInc;
    }

    public void setPositionInc(float x, float y, float z) {
        this.positionInc.x = x;
        this.positionInc.y = y;
        this.positionInc.z = z;
    }

    public Vector3f getRotationInc() {
        return rotationInc;
    }

    public void setRotationInc(float x, float y, float z) {
        this.rotationInc.x = x;
        this.rotationInc.y = y;
        this.rotationInc.z = z;
    }
    
    public int getScaleInc() {
        return scaleInc;
    }

    public void setScaleInc(int scaleInc) {
        this.scaleInc = scaleInc;
    }

    public float getSpeed() {
        return speed;
    }

    public AABB getAabb() {
        return aabb;
    }

    public Collision collidesWithBorder() {
        if (position.x < -1.5) {
            position.x = -1.5f;
            return Collision.COLLISION_LEFT;
        }
        if (position.x > 1.5 - getWidth()) {
            position.x = 1.5f - getWidth();
            return Collision.COLLISION_RIGHT;
        }
        if (position.y < -1) {
            position.y = -1f;
            return Collision.COLLISION_BOTTOM;
        }
        if (position.y > 1 - getHeight()) {
            position.y = 1f - getHeight();
            return Collision.COLLISION_TOP;
        }
        return Collision.NO_COLLISION;
    }

    public boolean collidesWith(Entity entity) {
        if (aabb.intersects(entity.getAabb())) {
            return true;
        }
        return false;
    }

    public float getWidth() {
        return getMesh().getWidth() * getScale();
    }

    public float getHeight() {
        return getMesh().getHeight() * getScale();
    }

    public float getDepth() {
        return getMesh().getDepth() * getScale();
    }

}

