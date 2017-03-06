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
public class Bship extends Ship {
    public Bship(String s) {
        super(4, s);
        img = new Image("bship.png");
        imgvert = new Image("bshipvert.png");
    }
    
}
