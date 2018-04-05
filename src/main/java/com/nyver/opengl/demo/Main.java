package com.nyver.opengl.demo;

import com.nyver.opengl.demo.engine.Engine;
import com.nyver.opengl.demo.engine.Game;
import com.nyver.opengl.demo.game.DemoGame;

public class Main {
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            Game game = new DemoGame();
            Engine engine = new Engine("OpenGL Demo", 800, 600, vSync, game);
            engine.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
