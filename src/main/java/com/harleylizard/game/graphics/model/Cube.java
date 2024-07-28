package com.harleylizard.game.graphics.model;

import com.harleylizard.game.graphics.bakery.Baker;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.floats.FloatArrayList;

public final class Cube {
    public static final Codec<Cube> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.FLOAT.listOf().fieldOf("from").forGetter(cube -> new FloatArrayList(new float[] {
                        cube.fromX * 16.0F,
                        cube.fromY * 16.0F,
                        cube.fromZ * 16.0F
                })),
                Codec.FLOAT.listOf().fieldOf("to").forGetter(cube -> new FloatArrayList(new float[] {
                        cube.toX * 16.0F,
                        cube.toY * 16.0F,
                        cube.toZ * 16.0F
                })),
                Codec.INT.fieldOf("x").forGetter(cube -> cube.x),
                Codec.INT.fieldOf("y").forGetter(cube -> cube.y)).apply(builder, (from, to, x, y) -> new Cube(
                        from.get(0) / 16.0F,
                        from.get(1) / 16.0F,
                        from.get(1) / 16.0F,
                        to.get(0) / 16.0F,
                        to.get(1) / 16.0F,
                        to.get(1) / 16.0F, x, y));
    });

    private final float fromX;
    private final float fromY;
    private final float fromZ;
    private final float toX;
    private final float toY;
    private final float toZ;
    private final int x;
    private final int y;

    public Cube(float fromX, float fromY, float fromZ, float toX, float toY, float toZ, int x, int y) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.fromZ = fromZ;
        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
        this.x = x;
        this.y = y;
    }

    private void bake(Baker baker, int width, int height) {

        bakeFace(baker, toX, fromY, fromZ, fromX, fromY, fromZ, fromX, toY, fromZ, toX, toY, fromZ, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F); // n
        bakeFace(baker, toX, fromY, toZ, toX, fromY, fromZ, toX, toY, fromZ, toX, toY, toZ, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F); // e
        bakeFace(baker, fromX, fromY, toZ, toX, fromY, toZ, toX, toY, toZ, fromX, toY, toZ, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F); // s
        bakeFace(baker, fromX, fromY, fromZ, fromX, fromY, toZ, fromX, toY, toZ, fromX, toY, fromZ, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F); // w
        bakeFace(baker, toX, toY, fromZ, fromX, toY, fromZ, fromX, toY, toZ, toX, toY, toZ, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F); // u
        bakeFace(baker, fromX, fromY, fromZ, toX, fromY, fromZ, toX, fromY, toZ, fromX, fromY, toZ, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F); // d
    }

    private void bakeFace(Baker baker,
                         float f00, float f01, float f02,
                         float f10, float f11, float f12,
                         float f20, float f21, float f22,
                         float f30, float f31, float f32,
                         float u0, float v0,
                         float u1, float v1,
                         float nx, float ny, float nz) {
        baker.vertex(f00, f01, f02, u0, v1, nx, ny, nz);
        baker.vertex(f10, f11, f12, u1, v1, nx, ny, nz);
        baker.vertex(f20, f21, f22, u1, v0, nx, ny, nz);
        baker.vertex(f30, f31, f32, u0, v0, nx, ny, nz);
    }
}
