/******************************************
 *Project-------LWJGL-Game
 *File----------Handler.java
 *Author--------Justin Kachele
 *Date----------9/22/2022
 *License-------GNU GENERAL PUBLIC LICENSE
 ******************************************/
package com.jkachele.game;

import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();

    public void tick() {
        for (GameObject obj : objects) {
            obj.tick();
        }
    }

    public void render() {
        for (GameObject obj : objects) {
            obj.render();
        }
    }

    public void addObject(GameObject obj) {
        this.objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        this.objects.remove(obj);
    }
}