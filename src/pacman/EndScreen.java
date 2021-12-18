/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author BB-8
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EndScreen extends JPanel{  
    private final JLabel gameOver;
    private final JLabel points;
    
    public EndScreen(GameFrame frame, Map map){
        super();
        this.setSize(frame.getWidth(), frame.getHeight());
        this.setBackground(Color.black);
        this.setLayout(null);
        gameOver = new JLabel();
        gameOver.setForeground(new Color(255,0,0));
        gameOver.setText("GAME OVER");
        gameOver.setFont(new Font("Press Start 2P", Font.BOLD, map.getSpriteSize()*2));
        gameOver.setBounds(map.getSpriteSize()*5, map.getSpriteSize()*10, map.getSpriteSize()*25,map.getSpriteSize()*10);
        this.add(gameOver);
        points = new JLabel();
        points.setForeground(new Color(222,222,222));
        points.setText("YOUR SCORE: " + Integer.toString(map.getPoints())+ " POINTS");
        points.setFont(new Font("Press Start 2P", Font.BOLD, map.getSpriteSize()));
        points.setBounds(map.getSpriteSize()*4+10, map.getSpriteSize()*15, map.getSpriteSize()*25,map.getSpriteSize()*10);
        this.add(points); 
    }
}
