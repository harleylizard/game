package com.harleylizard.game.graphics;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.GL_UNIFORM_BUFFER;
import static org.lwjgl.opengl.GL44.GL_DYNAMIC_STORAGE_BIT;
import static org.lwjgl.opengl.GL45.*;

public final class Matrices {
    private final Matrix4f projection = new Matrix4f();
    private final Matrix4f view = new Matrix4f();
    private final Matrix4f model = new Matrix4f();

    private final int ubo = glCreateBuffers();

    {
        glBindBufferBase(GL_UNIFORM_BUFFER, 0, ubo);
        glNamedBufferStorage(ubo, 48 * 4, GL_DYNAMIC_STORAGE_BIT | GL_MAP_READ_BIT | GL_MAP_WRITE_BIT);
    }

    public void upload() {
        var buffer = glMapNamedBuffer(ubo, GL_READ_WRITE);
        projection.get(0, buffer);
        view.get(16 * 4, buffer);
        model.get(32 * 4, buffer);
        glUnmapNamedBuffer(ubo);
    }

    public Matrix4f getProjection() {
        return projection;
    }

    public Matrix4f getModel() {
        return model;
    }

    public Matrix4f getView() {
        return view;
    }
}
