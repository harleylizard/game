package com.harleylizard.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

import static java.util.Objects.requireNonNull;
import static org.lwjgl.system.MemoryUtil.memCalloc;
import static org.lwjgl.system.MemoryUtil.memRealloc;

public final class Resources {

    private Resources() {}

    public static InputStream openStream(String path) throws IOException {
        return requireNonNull(Resources.class.getClassLoader().getResource(path)).openStream();
    }

    public static String readString(String path) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(openStream(path)))) {
            var builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        }
    }

    public static ByteBuffer readImage(String path) throws IOException {
        try (var channel = Channels.newChannel(openStream(path))) {
            var buffer = memCalloc(1024);

            while (channel.read(buffer) != -1) {
                if (buffer.remaining() == 0) {
                    buffer = memRealloc(buffer, buffer.capacity() * 2);
                }
            }
            buffer.flip();
            return buffer;
        }
    }
}
