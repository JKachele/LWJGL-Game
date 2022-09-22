/******************************************
 *Project-------LWJGL-Game
 *File----------Main.java
 *Author--------Justin Kachele
 *Date----------9/22/2022
 *License-------GNU GENERAL PUBLIC LICENSE
 ******************************************/
package com.jkachele.game;

import com.jkachele.game.Renderer.Window;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Main implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "Java Game";

    private Thread thread;
    private boolean isRunning = false;

    private final Handler handler;
    private Window window;

    public Main() {
        handler = new Handler();
        window = Window.getInstance(WIDTH, HEIGHT, TITLE);
    }

    public synchronized void start() {
        //starts a new thread to run the game
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop() {
        //stops the current thread
        try {
            thread.join();
            isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //main game loop
        long lastTime = System.nanoTime();          //time since last iteration of loop. used to compute delta
        double amountOfTicks = 60.0;                //max FPS of game
        double ns = 1000000000 / amountOfTicks;     //number of nanoseconds per frame
        double delta = 0;                           //The 'progress' that must be elapsed until the next frame.
        long timer = System.currentTimeMillis();
        int frames = 0;                             //The number of frames elapsed. used for FPS calculations.
        while (isRunning) {
            long time = System.nanoTime();          //The current time. Used to know when to display next the FPS.
            delta += (time - lastTime) / ns;
            lastTime = time;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (isRunning)
                render();
            if (glfwWindowShouldClose(window)) {

            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        //updates the game state
        handler.tick();
    }

    private void render() {
        //updates the game state
        handler.render();
    }

    public static void main(String[] args) {
        new Main();
    }
}