/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Mr. Simpatia
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JPanel{
    private final JLabel pacman;
    
    public MainMenu(GameFrame frame) {
        this.setSize(frame.getWidth(), frame.getHeight());
        this.setBackground(Color.black);
        this.setLayout(null);
        JButton play = new JButton("Play");
        play.setBounds(frame.getWidth()/2-100, 250, 200, 50);
        this.add(play);
        pacman = new JLabel();
        pacman.setForeground(Color.YELLOW);
        pacman.setText("PACMAN");
        pacman.setFont(new Font("Press Start 2P", Font.BOLD, this.getHeight()/9));
        pacman.setBounds(this.getWidth()/50, this.getHeight()/50, this.getWidth()-this.getWidth()/50,this.getHeight()/9);
        this.add(pacman);
        JButton exit = new JButton("Exit");
        exit.setBounds(frame.getWidth()/2-100, 325, 200, 50);
        this.add(exit);
        JPanel prova = this;
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.remove(prova);
                frame.add(new Map(frame));
                frame.revalidate();
                frame.repaint();
            }
        });
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
    }
    
    
}
