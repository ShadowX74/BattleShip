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
public class Carrier extends Ship {
    public Carrier(String s) {
        super(5, s);
        this.dir = "carrier";
        img = new Image("carrier.png");
        imgvert = new Image("carriervert.png");
    }
}
