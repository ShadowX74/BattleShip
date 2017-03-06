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
public class PTBoat extends Ship {
    public PTBoat(String s) {
        super(2, s);
        img = new Image("ptboat.png");
        imgvert = new Image("ptboatvert.png");
    }
}
