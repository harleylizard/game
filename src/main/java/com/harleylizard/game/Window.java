package com.harleylizard.game;

import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAddress;

@SuppressWarnings("resource")
public final class Window implements AutoCloseable {
    private final long window;

    private int width;
    private int height;

    {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
        glfwWindowHint(GLFW_CONTEXT_RELEASE_BEHAVIOR, GLFW_RELEASE_BEHAVIOR_FLUSH);

        if ((window = glfwCreateWindow(854, 480, "Game", NULL, NULL)) == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create GLFW Window");
        }

        var monitor = glfwGetPrimaryMonitor();
        if (monitor != NULL) {
            try (var stack = MemoryStack.stackPush()) {
                var buffer = stack.callocInt(6);

                nglfwGetMonitorWorkarea(monitor, memAddress(buffer), memAddress(buffer) + 4, memAddress(buffer) + 8, memAddress(buffer) + 12);
                nglfwGetWindowSize(window, memAddress(buffer) + 16, memAddress(buffer) + 20);

                width = buffer.get(4);
                height = buffer.get(5);

                glfwSetWindowPos(window, (buffer.get(2) - width) / 2, (buffer.get(3) - height) / 2);
            }
        }
        glfwSetWindowSizeCallback(window, (window, width, height) -> {
            this.width = width;
            this.height = height;
            glViewport(0, 0, width, height);
        });

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void poll() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public float getAspectRatio() {
        return (float) width / (float) height;
    }

    @Override
    public void close() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
