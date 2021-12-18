/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Martin Scorsese
 */


import javax.swing.*;

public class GameFrame extends JFrame{
    
    public GameFrame(){
        super("PACMAN");
        this.setSize(750, 1000);
        this.setLayout(null);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(new MainMenu(this));
        this.setFocusable(true);
    }
}
