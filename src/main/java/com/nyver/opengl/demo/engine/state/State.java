package com.nyver.opengl.demo.engine.state;

import com.nyver.opengl.demo.engine.Window;

public interface State {
    void enter(Window window) throws Exception;

    void input(Window window);

    void update(float interval);

    void render(Window window);

    void exit();
}
