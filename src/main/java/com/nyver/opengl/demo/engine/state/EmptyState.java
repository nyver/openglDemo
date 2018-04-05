package com.nyver.opengl.demo.engine.state;

import com.nyver.opengl.demo.engine.MouseInput;
import com.nyver.opengl.demo.engine.Window;

public class EmptyState implements State {

    @Override
    public void enter(Window window) throws Exception {
        
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {

    }

    @Override
    public void update(float interval, MouseInput mouseInput) {

    }

    @Override
    public void render(Window window) {

    }

    @Override
    public void exit() {

    }
}
