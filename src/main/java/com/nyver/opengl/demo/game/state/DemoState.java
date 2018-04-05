package com.nyver.opengl.demo.game.state;

import com.nyver.opengl.demo.engine.Entity;
import com.nyver.opengl.demo.engine.MouseInput;
import com.nyver.opengl.demo.engine.Renderer;
import com.nyver.opengl.demo.engine.state.State;
import com.nyver.opengl.demo.engine.Window;
import com.nyver.opengl.demo.game.entity.EntityFactory;
import com.nyver.opengl.demo.game.entity.Player;
import com.nyver.opengl.demo.graphic.Camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DemoState implements State {

    private final Renderer renderer;

    private final Camera camera;

    private final EntityFactory entityFactory;

    private Player player;
    private List<Entity> gameItems = new ArrayList<>();
    private List<Entity> shots = new ArrayList<>();

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

        for (Entity shot: shots) {
            shot.input(window);
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.update();

        player.update();
        player.collidesWithBorder();
        
        for (Entity shot: shots) {
            shot.update();
        }

        updateAsteroids();

        if (player.isShot()) {
            fire();
        }
    }

    private void updateAsteroids() {
        Iterator<Entity> iteratorAsteroids = gameItems.iterator();
        while (iteratorAsteroids.hasNext()) {
            boolean collision = false;
            Entity asteroid = iteratorAsteroids.next();
            if (asteroid.collidesWith(player)) {
                collision = true;
            }

            Iterator<Entity> iteratorShots = shots.iterator();
            while (iteratorShots.hasNext()) {
                Entity shot = iteratorShots.next();
                if (asteroid.collidesWith(shot)) {
                    collision = true;
                    iteratorShots.remove();
                    continue;
                }
            }

            if (collision) {
                iteratorAsteroids.remove();
            }
            asteroid.update();
        }
    }

    private void fire() {
        try {
            player.fire();
            shots.add(entityFactory.createShot(player.getPosition().x, player.getPosition().y, -2, 0.05f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Window window) {
        renderer.beginRender(window, camera);
        renderer.render(player);
        renderer.render(gameItems);
        renderer.render(shots);
        renderer.endRender();
    }

    @Override
    public void exit() {
        renderer.delete();
        player.getMesh().delete();
        for (Entity gameItem : gameItems) {
            gameItem.getMesh().delete();
        }

        for (Entity shot : shots) {
            shot.getMesh().delete();
        }
    }
}
