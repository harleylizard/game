package com.harleylizard.game.graphics;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.opengl.GL45.glDisableVertexArrayAttrib;

public final class Quad {
    private final int vao = glCreateVertexArrays();
    private final int vbo = glCreateBuffers();
    private final int ebo = glCreateBuffers();

    private final ProgramPipeline pipeline;

    {
        float[] vertices = {
                -0.5F, -0.5F, 0.0F, 1.0F,
                0.5F, -0.5F, 0.0F, 1.0F,
                0.5F,  0.5F, 0.0F, 1.0F,
                -0.5F,  0.5F, 0.0F, 1.0F
        };
        int[] elements = {
                0, 1, 2,
                2, 3, 0
        };

        glVertexArrayVertexBuffer(vao, 0, vbo, 0, 16);
        glVertexArrayAttribBinding(vao, 0, 0);
        glVertexArrayAttribFormat(vao, 0, 4, GL_FLOAT, false, 0);
        glVertexArrayElementBuffer(vao, ebo);

        glNamedBufferStorage(vbo, vertices, 0);
        glNamedBufferStorage(ebo, elements, 0);

        try {
            pipeline = new ProgramPipeline.Builder()
                    .add(Shader.FRAGMENT, "shaders/quad_fragment.glsl")
                    .add(Shader.VERTEX, "shaders/quad_vertex.glsl")
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw() {
        pipeline.bind();
        glBindVertexArray(vao);

        glEnableVertexArrayAttrib(vao, 0);

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

        glDisableVertexArrayAttrib(vao, 0);

        glBindVertexArray(0);
        ProgramPipeline.unbind();
    }

}
