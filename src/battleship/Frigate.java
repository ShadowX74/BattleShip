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
public class Frigate extends Ship {
    public Frigate(String s) {
        super(3, s);
        this.dir = "frigate";
        img = new Image("frigate.png");
        imgvert = new Image("frigatevert.png");
    }
}
