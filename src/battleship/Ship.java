/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.scene.image.Image;

/**
 *
 * @author ShadowX
 */
public abstract class Ship {
    int size, health;
    String dir;
    Image img, imgvert;
    
    public Ship(int a, String s) {
        size = a;
        health = size;
        dir = s;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}