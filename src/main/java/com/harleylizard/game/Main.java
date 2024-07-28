package com.harleylizard.game;

import static org.lwjgl.glfw.GLFW.glfwInit;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialise GLFW");
        }

        try (var window = new Window()) {
            while (!window.shouldClose()) {

                window.poll();
            }
        }
    }
}
