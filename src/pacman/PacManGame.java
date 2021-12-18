/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Lupin III
 */

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.WindowConstants;


public class PacManGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/PressStart2P-vaV7.ttf")));
        } catch (IOException|FontFormatException e) {
}
        GameFrame f = new GameFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
}
