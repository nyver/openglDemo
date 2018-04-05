package com.nyver.opengl.demo.engine;

import com.nyver.opengl.demo.engine.state.StateMachine;
import org.lwjgl.opengl.GL;

public abstract class Game {

    protected StateMachine state;

    public abstract void init(Window window) throws Exception;

    public void input(Window window) {
        state.input(window);
    }

    public void update(float interval) {
        state.update(interval);
    }

    public void render(Window window) {
        state.render(window);
    }

    public abstract void cleanup();

    /**
     * Determines if the OpenGL context supports version 3.2.
     *
     * @return true, if OpenGL context supports version 3.2, else false
     */
    public static boolean isDefaultContext() {
        return GL.getCapabilities().OpenGL32;
    }
}
