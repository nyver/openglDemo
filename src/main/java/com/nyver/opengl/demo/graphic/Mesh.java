package com.nyver.opengl.demo.graphic;

import com.nyver.opengl.demo.engine.Color;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Mesh {

    private final VertexArrayObject vao;

    private List<VertexBufferObject> vbos;

    private final int vertexCount;

    private Texture texture;

    private Color color;

    private float width;

    private float height;

    private float depth;

    public Mesh(float[] positions, float[] textCoords, float[] normals, int[] indices) {
        FloatBuffer posBuffer = null;
        FloatBuffer textCoordsBuffer = null;
        FloatBuffer vecNormalsBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
            color = Color.WHITE;
            vertexCount = indices.length;
            vbos = new ArrayList<>();

            vao = new VertexArrayObject();
            vao.bind();

            // Position VBO
            VertexBufferObject vboPosition = new VertexBufferObject();
            vbos.add(vboPosition);
            posBuffer = MemoryUtil.memAllocFloat(positions.length);
            posBuffer.put(positions).flip();
            vboPosition.bind(GL_ARRAY_BUFFER);
            vboPosition.uploadData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Texture coordinates VBO
            VertexBufferObject vboTexture = new VertexBufferObject();
            vbos.add(vboTexture);
            textCoordsBuffer = MemoryUtil.memAllocFloat(textCoords.length);
            textCoordsBuffer.put(textCoords).flip();
            vboTexture.bind(GL_ARRAY_BUFFER);
            vboTexture.uploadData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            // Vertex normals VBO
            VertexBufferObject vboNormal = new VertexBufferObject();
            vbos.add(vboNormal);
            vecNormalsBuffer = MemoryUtil.memAllocFloat(normals.length);
            vecNormalsBuffer.put(normals).flip();
            vboNormal.bind(GL_ARRAY_BUFFER);
            vboNormal.uploadData(GL_ARRAY_BUFFER, vecNormalsBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

            // Index VBO
            VertexBufferObject vboIndex = new VertexBufferObject();
            vbos.add(vboIndex);
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            vboIndex.bind(GL_ELEMENT_ARRAY_BUFFER);
            vboIndex.uploadData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);

            calculateParams(positions);
        } finally {
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (textCoordsBuffer != null) {
                MemoryUtil.memFree(textCoordsBuffer);
            }
            if (vecNormalsBuffer != null) {
                MemoryUtil.memFree(vecNormalsBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }
    
    public int getVertexCount() {
        return vertexCount;
    }

    public void render() {
        glEnable(GL_DEPTH_TEST);

        if (isTextured()) {
            // Activate firs texture bank
            glActiveTexture(GL_TEXTURE0);
            // Bind the texture
            glBindTexture(GL_TEXTURE_2D, texture.getId());
        }

        // Draw the mesh
        vao.bind();
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void delete() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        for (VertexBufferObject vbo: vbos) {
            vbo.delete();
        }

        // Delete the texture
        if (null != texture) {
            texture.delete();
        }

        // Delete the VAO
        glBindVertexArray(0);
        vao.delete();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isTextured() {
        return this.texture != null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getDepth() {
        return depth;
    }

    protected void calculateParams(float[] positions) {
        float minX, minY, minZ;
        minX = minY = minZ = 1;
        float maxX, maxY, maxZ;
        maxX = maxY = maxZ = -1;
        
        for (int i = 0; i < positions.length; i = i + 3) {
            minX = Math.min(minX, positions[i]);
            minY = Math.min(minX, positions[i+1]);
            minZ = Math.min(minX, positions[i+2]);

            maxX = Math.max(maxX, positions[i]);
            maxY = Math.max(maxY, positions[i+1]);
            maxZ = Math.max(maxZ, positions[i+2]);
        }
        width = maxX - minX;
        height = maxY - minY;
        depth = maxZ - minZ;
    }
}
