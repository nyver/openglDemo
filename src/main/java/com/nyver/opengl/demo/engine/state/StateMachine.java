package com.nyver.opengl.demo.engine.state;

import com.nyver.opengl.demo.engine.Window;

import java.util.HashMap;
import java.util.Map;

public class StateMachine implements State {
    /**
     * Contains all states of this state machine.
     */
    private final Map<String, State> states;

    /**
     * Current active state.
     */
    private State currentState;

    /**
     * Creates a state machine.
     */
    public StateMachine() {
        states = new HashMap<>();
        currentState = new EmptyState();
        states.put(null, currentState);
    }

    /**
     * Adds a state with specified name.
     *
     * @param name  Name of the state
     * @param state The state to add
     */
    public void add(String name, State state) {
        states.put(name, state);
    }

    /**
     * Changes the current state.
     *
     * @param name Name of the desired state
     */
    public void change(Window window, String name) throws Exception {
        currentState.exit();
        currentState = states.get(name);
        currentState.enter(window);
    }

    @Override
    public void input(Window window) {
        currentState.input(window);
    }

    @Override
    public void update(float interval) {
        currentState.update(interval);
    }

    @Override
    public void render(Window window) {
        currentState.render(window);
    }

    @Override
    public void enter(Window window) throws Exception {
        currentState.enter(window);
    }

    @Override
    public void exit() {
        currentState.exit();
    }
}
