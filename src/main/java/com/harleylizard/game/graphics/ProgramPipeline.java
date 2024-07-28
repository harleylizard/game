package com.harleylizard.game.graphics;

import com.harleylizard.game.Resources;

import java.io.IOException;

import static org.lwjgl.opengl.GL41.*;
import static org.lwjgl.opengl.GL45.glCreateProgramPipelines;

public final class ProgramPipeline {
    private final int pipeline;

    private ProgramPipeline(int pipeline) {
        this.pipeline = pipeline;
    }

    public void bind() {
        glBindProgramPipeline(pipeline);
    }

    public static void unbind() {
        glBindProgramPipeline(0);
    }

    public static final class Builder {
        private final int pipeline = glCreateProgramPipelines();

        public Builder add(Shader shader, String path) throws IOException {
            var program = glCreateShaderProgramv(shader.getType(), Resources.readString(path));
            glUseProgramStages(pipeline, shader.getBit(), program);
            return this;
        }

        public ProgramPipeline build() {
            glValidateProgramPipeline(pipeline);
            return new ProgramPipeline(pipeline);
        }
    }
}
