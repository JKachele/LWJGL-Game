/******************************************
 *Project-------LWJGL-Game
 *File----------GameObject.java
 *Author--------Justin Kachele
 *Date----------9/22/2022
 *License-------GNU GENERAL PUBLIC LICENSE
 ******************************************/
package com.jkachele.game;

import lombok.*;

@Getter
@Setter
public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render();
}