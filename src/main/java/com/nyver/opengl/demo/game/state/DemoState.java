package com.nyver.opengl.demo.game.state;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.Renderer;
import com.nyver.opengl.demo.engine.state.State;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.game.entity.EntityFactory;

public class DemoState implements State {

    private final Renderer renderer;

    private final EntityFactory entityFactory;

    private Entity[] gameItems;

    public DemoState(Renderer renderer, EntityFactory entityFactory) {
        this.renderer = renderer;
        this.entityFactory = entityFactory;
    }

    @Override
    public void enter(Window window) throws Exception {
        renderer.init(window);
        gameItems = new Entity[]{entityFactory.createCube(0, 0, -2)};
    }

    @Override
    public void input(Window window) {
        for (Entity gameItem : gameItems) {
            gameItem.input(window);
        }
    }

    @Override
    public void update(float interval) {
        for (Entity gameItem : gameItems) {
            gameItem.update();
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, gameItems);
    }

    @Override
    public void exit() {
        renderer.delete();
        for (Entity gameItem : gameItems) {
            gameItem.getMesh().delete();
        }
    }
}
