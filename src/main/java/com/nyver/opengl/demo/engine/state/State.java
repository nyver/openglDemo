package com.nyver.opengl.demo.engine.state;

import com.nyver.opengl.demo.engine.MouseInput;
import com.nyver.opengl.demo.engine.Window;

public interface State {
    void enter(Window window) throws Exception;

    void input(Window window, MouseInput mouseInput);

    void update(float interval, MouseInput mouseInput);

    void render(Window window);

    void exit();
}
