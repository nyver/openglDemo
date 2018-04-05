package com.nyver.opengl.demo.engine;

import com.nyver.opengl.demo.graphic.Camera;
import com.nyver.opengl.demo.graphic.Mesh;
import com.nyver.opengl.demo.graphic.ShaderProgram;
import com.nyver.opengl.demo.graphic.Transformation;
import org.joml.Matrix4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private final Transformation transformation;

    private ShaderProgram shaderProgram;

    private boolean drawing;
    private Matrix4f viewMatrix;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init(Window window) throws Exception {
        // Create shader
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/shaders/fragment.fs"));
        shaderProgram.link();

        // Create uniforms for world and projection matrices and texture
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");

        // Create uniform for default colour and the flag that controls it
        shaderProgram.createUniform("colour");
        shaderProgram.createUniform("useColour");
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void beginRender(Window window, Camera camera) {
        if (drawing) {
            throw new IllegalStateException("Renderer is already drawing!");
        }
        drawing = true;

        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Update view Matrix
        viewMatrix = transformation.getViewMatrix(camera);

        shaderProgram.setUniform("texture_sampler", 0);
    }

    public void render(Entity gameItem) {
        Mesh mesh = gameItem.getMesh();
        // Set model view matrix for this item
        Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
        shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
        // Render the mesh for this game item
        shaderProgram.setUniform("colour", mesh.getColor().toVector3f());
        shaderProgram.setUniform("useColour", mesh.isTextured() ? 0 : 1);
        mesh.render();
    }

    public void render(List<Entity> gameItems) {
        // Render each gameItem
        for(Entity gameItem : gameItems) {
            render(gameItem);
        }
    }

    public void endRender() {
        if (!drawing) {
            throw new IllegalStateException("Renderer isn't drawing!");
        }
        drawing = false;
        shaderProgram.unbind();
    }

    public void delete() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
