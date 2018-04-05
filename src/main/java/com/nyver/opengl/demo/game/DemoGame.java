package com.nyver.opengl.demo.game;

import com.nyver.opengl.demo.engine.Game;
import com.nyver.opengl.demo.engine.Renderer;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.engine.state.StateMachine;
import com.nyver.opengl.demo.game.entity.EntityFactory;
import com.nyver.opengl.demo.game.state.DemoState;

public class DemoGame extends Game {

    private final Renderer renderer;

    private final EntityFactory entityFactory;

    public DemoGame() {
        this(new Renderer(), new StateMachine(), new EntityFactory());
    }

    public DemoGame(Renderer renderer, StateMachine stateMachine, EntityFactory entityFactory) {
        this.renderer = renderer;
        this.state = stateMachine;
        this.entityFactory = entityFactory;
    }

    @Override
    public void init(Window window) throws Exception {
        state.add("game", new DemoState(renderer, entityFactory));
        state.change(window, "game");
    }


    @Override
    public void cleanup() {
        renderer.delete();
        state.exit();
    }
}
