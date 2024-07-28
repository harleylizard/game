package com.harleylizard.game.graphics.bakery;

import java.nio.ByteBuffer;

import static org.lwjgl.system.MemoryUtil.memFree;

public final class BakerBuffer {
    private final ByteBuffer buffer;
    private final int vertices;
    private final int elements;

    public BakerBuffer(ByteBuffer buffer, int vertices, int elements) {
        this.buffer = buffer;
        this.vertices = vertices;
        this.elements = elements;
    }

    public void free() {
        memFree(buffer);
    }
}
