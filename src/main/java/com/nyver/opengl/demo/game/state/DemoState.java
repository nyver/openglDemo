package com.nyver.opengl.demo.game.state;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.MouseInput;
import com.nyver.opengl.demo.engine.Renderer;
import com.nyver.opengl.demo.engine.state.State;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.game.entity.EntityFactory;
import com.nyver.opengl.demo.graphic.Camera;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class DemoState implements State {

    private final Renderer renderer;

    private final Camera camera;

    private final EntityFactory entityFactory;

    private Entity player;
    private List<Entity> gameItems = new ArrayList<>();

    public DemoState(Renderer renderer, EntityFactory entityFactory) {
        this.renderer = renderer;
        this.entityFactory = entityFactory;
        this.camera = new Camera();
    }

    @Override
    public void enter(Window window) throws Exception {
        renderer.init(window);
        player = entityFactory.createPlayer(0, -1, -2, 0.05f);
        gameItems.add(entityFactory.createAsteroid(0, 1, -2, 0.05f));
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        camera.input(window);
        player.input(window);
        for (Entity gameItem : gameItems) {
            gameItem.input(window);
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.update();

        player.update();

        for (Entity gameItem : gameItems) {
            gameItem.update();
        }
    }

    @Override
    public void render(Window window) {
        renderer.beginRender(window, camera);
        renderer.render(player);
        renderer.render(gameItems);
        renderer.endRender();
    }

    @Override
    public void exit() {
        renderer.delete();
        player.getMesh().delete();
        for (Entity gameItem : gameItems) {
            gameItem.getMesh().delete();
        }
    }
}
