package com.nyver.opengl.demo.game.state;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.MouseInput;
import com.nyver.opengl.demo.engine.Renderer;
import com.nyver.opengl.demo.engine.state.State;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.game.entity.EntityFactory;
import com.nyver.opengl.demo.graphic.Camera;
import org.joml.Vector2f;

public class DemoState implements State {

    private final Renderer renderer;

    private final Camera camera;

    private final EntityFactory entityFactory;

    private Entity[] gameItems;

    public DemoState(Renderer renderer, EntityFactory entityFactory) {
        this.renderer = renderer;
        this.entityFactory = entityFactory;
        this.camera = new Camera();
    }

    @Override
    public void enter(Window window) throws Exception {
        renderer.init(window);
        gameItems = new Entity[]{entityFactory.createCube(0, 0, -2, 0.5f)};
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        camera.input(window);
        for (Entity gameItem : gameItems) {
            gameItem.input(window);
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.update();

        // Update camera based on mouse
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * Camera.MOUSE_SENSITIVITY, rotVec.y * Camera.MOUSE_SENSITIVITY, 0);
        }
        
        for (Entity gameItem : gameItems) {
            gameItem.update();
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void exit() {
        renderer.delete();
        for (Entity gameItem : gameItems) {
            gameItem.getMesh().delete();
        }
    }
}
