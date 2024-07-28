package com.harleylizard.game.graphics.bakery;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatList;

public final class Baker {
    private final FloatList floats = new FloatArrayList();
    private int vertices;

    public void vertex(float x, float y, float z, float u, float v, float nx, float ny, float nz) {
        floats.add(x);
        floats.add(y);
        floats.add(z);
        floats.add(1.0F);
        floats.add(u);
        floats.add(v);
        floats.add(nx);
        floats.add(ny);
        floats.add(nz);
        vertices++;
    }

    public BakerBuffer triangulate() {
        return null;
    }
}
