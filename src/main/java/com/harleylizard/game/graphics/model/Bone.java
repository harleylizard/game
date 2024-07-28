package com.harleylizard.game.graphics.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.floats.FloatArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Bone implements Iterable<Cube> {
    public static Codec<Bone> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.FLOAT.listOf().fieldOf("pivot").forGetter(bone -> new FloatArrayList(new float[] {
                        bone.pivotX * 16.0F,
                        bone.pivotY * 16.0F,
                        bone.pivotZ * 16.0F
                })),
                Cube.CODEC.listOf().fieldOf("cubes").forGetter(bone -> bone.cubes)).apply(builder, (pivot, cubes) -> new Bone(Collections.unmodifiableList(cubes),
                pivot.get(0) / 16.0F,
                pivot.get(1) / 16.0F,
                pivot.get(2) / 16.0F));
    });

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

    @Override
    public Iterator<Cube> iterator() {
        return cubes.iterator();
    }
}
