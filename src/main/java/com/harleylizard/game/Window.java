package com.harleylizard.game;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAddress;

public final class Window implements AutoCloseable {
    private final long window;

    {
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

                var width = buffer.get(4);
                var height = buffer.get(5);

                glfwSetWindowPos(window, (buffer.get(2) - width) / 2, (buffer.get(3) - height) / 2);
            }
        }
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void poll() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    @Override
    public void close() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
