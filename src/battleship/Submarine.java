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
public class Submarine extends Ship {
    public Submarine(String s) {
        super(3, s);
        this.dir = "submarine";
        img = new Image("submarine.png");
        imgvert = new Image("submarinevert.png");
    }
}
