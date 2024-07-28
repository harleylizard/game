package com.harleylizard.game.graphics.model;

import java.util.List;

public final class Bone {
    private final List<Cube> cubes;
    private final float pivotX;
    private final float pivotY;
    private final float pivotZ;

    public Bone(List<Cube> cubes, float pivotX, float pivotY, float pivotZ) {
        this.cubes = cubes;
        this.pivotX = pivotX;
        this.pivotY = pivotY;
        this.pivotZ = pivotZ;
    }
}
