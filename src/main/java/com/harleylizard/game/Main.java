package com.harleylizard.game;

import com.harleylizard.game.graphics.Matrices;
import com.harleylizard.game.graphics.Quad;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.opengl.GL11.*;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialise GLFW");
        }

        try (var window = new Window()) {
            var quad = new Quad();
            var matrices = new Matrices();

            glClearColor(1.0F, 0.0F, 0.0F, 0.0F);
            while (!window.shouldClose()) {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                var projection = matrices.getProjection();
                projection.identity();
                projection.perspective((float) Math.toRadians(70.0F), window.getAspectRatio(),  0.01F, 100.0F);

                var view = matrices.getView();
                view.identity();
                view.translate(0.0F, 0.0F, -10.0F);

                matrices.upload();
                quad.draw();

                window.poll();
            }
        }
    }
}
