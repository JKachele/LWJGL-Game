package com.jkachele.game.Renderer;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final int width, height;
    private final String title;
    private long glfwWindow;

    private static Window window = null;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
    }

    public static Window getInstance() {
        if (Window.window == null)
            window = new Window();
        return window;
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();
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
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL)
            throw new RuntimeException("Failed to create GLFW window");

        //make the openGL context current
        glfwMakeContextCurrent(glfwWindow);
        //enable v-sync
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

    }
}
