package com.harleylizard.game.graphics.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Collections;
import java.util.Map;

public final class Model {
    public static final Codec<Model> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(Codec.unboundedMap(Codec.STRING, Bone.CODEC).fieldOf("bones").forGetter(model -> model.bones)).apply(builder, bones -> new Model(Collections.unmodifiableMap(bones)));
    });

    private final Map<String, Bone> bones;

    public Model(Map<String, Bone> bones) {
        this.bones = bones;
    }
}
