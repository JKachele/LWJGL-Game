/******************************************
 *Project-------LWJGL-Game
 *File----------Window.java
 *Author--------Justin Kachele
 *Date----------9/22/2022
 *License-------GNU GENERAL PUBLIC LICENSE
 ******************************************/
package com.jkachele.game.Renderer;

import lombok.Getter;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

@Getter
public class Window {

    private final int WIDTH, HEIGHT;
    private final String TITLE;
    private long glfwWindow;

    private static final Window window = null;

    private Window(int WIDTH, int HEIGHT, String TITLE) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.TITLE = TITLE;
        init();
    }

    public static Window getInstance(int WIDTH, int HEIGHT, String TITLE) {
        new Window(WIDTH, HEIGHT, TITLE);
        return window;
    }

    public void init() {
        //setup error callback to System.err
        GLFWErrorCallback.createPrint(System.err).set();

        //initialize GLFW (Graphics Library Framework)
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        //configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);               //Window will remain hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);              //Window will be resizeable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);              //Window will start maximized

        //create the window
        glfwWindow = glfwCreateWindow(this.WIDTH, this.HEIGHT, this.TITLE, NULL, NULL);
        if (glfwWindow == NULL)
            throw new RuntimeException("Failed to create GLFW window");

        //make the openGL context current
        glfwMakeContextCurrent(glfwWindow);
        //enable v-sync (waits 1 screen refresh to render new frame)
        glfwSwapInterval(1);

        //make the window visible
        glfwShowWindow(glfwWindow);

        /* This line is critical for LWJGL's interoperation with GLFW's
        OpenGL context, or any context that is managed externally.
        LWJGL detects the context that is current in the current thread,
        creates the GLCapabilities instance and makes the OpenGL
        bindings available for use. */
        GL.createCapabilities();
    }

    public void loop() {
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        while (!glfwWindowShouldClose(glfwWindow)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glfwWindow); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}