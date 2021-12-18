/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Salvini
 */

import java.util.*;  
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PacMan extends Thread{
    private int lives;
    private int posX;
    private int posY;
    private boolean died;
    private boolean superPac;
    private int imagePosX;
    private int imagePosY;
    private final ImageIcon error;
    private final ImageIcon nulla;
    private final ImageIcon down;
    private final ImageIcon up;
    private final ImageIcon right;
    private final ImageIcon left;
    private final ImageIcon stop;
    private final Map map;
    private final int[][] matBack;
    private int counter;
    private final JLabel pacLab;
    private char direction; //R,L,U,D 
    
    public PacMan(Map map) {
        lives = 3;
        died = false;
        posX = 13;
        posY = 23;
        imagePosX = map.getSpriteSize() * posX;
        imagePosY = map.getSpriteSize() * (posY + 3);
        pacLab = new JLabel();
        this.map = map;
        
        error =  new ImageIcon(new ImageIcon("sprite/error.png").getImage().getScaledInstance(map.getSpriteSize(), map.getSpriteSize(), Image.SCALE_DEFAULT));
        nulla = new ImageIcon(new ImageIcon("sprite/nulla.png").getImage().getScaledInstance(map.getSpriteSize(), map.getSpriteSize(), Image.SCALE_DEFAULT));
        down = new ImageIcon(new ImageIcon("sprite/pacman/down.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        up = new ImageIcon(new ImageIcon("sprite/pacman/up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        right = new ImageIcon(new ImageIcon("sprite/pacman/right.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        left = new ImageIcon(new ImageIcon("sprite/pacman/left.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        stop = new ImageIcon(new ImageIcon("sprite/pacman/stop.png").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        
        pacLab.setIcon(stop);
        pacLab.setBounds(imagePosX, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
        map.add(pacLab, 0);
        
        matBack = new int[map.getROWS()][map.getCOLUMNS()];
        for(int i = 0; i< map.getROWS();i++){
            for(int j = 0; j< map.getCOLUMNS();j++){
                matBack[i][j] = map.getMatMapIJ(i, j);
            }
        }
        counter = 0;
        for(int i = 0; i< map.getROWS();i++){
            for(int j = 0; j< map.getCOLUMNS();j++){
                if(matBack[i][j] == 1)
                    counter++;
            }  
        }
    }

    public void initPac(){
        posX = 13;
        posY = 23;
        imagePosX = map.getSpriteSize() * posX;
        imagePosY = map.getSpriteSize() * (posY + 3);
        pacLab.setIcon(stop);
        pacLab.setBounds(imagePosX, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
        died = false;
        direction='e';
        
        for(int i = 0; i< map.getROWS();i++){
            for(int j = 0; j< map.getCOLUMNS();j++){
                matBack[i][j] = map.getMatMapIJ(i, j);
            }
        }
        counter = 0;
        for(int i = 0; i< map.getROWS();i++){
            for(int j = 0; j< map.getCOLUMNS();j++){
                if(matBack[i][j] == 1)
                    counter++;
            }  
        }
        map.add(pacLab, 0);
        System.out.println("counter: "+ counter);
    }
    
    @Override
    public void run(){
        while(true){
            if(map.checkWall(this.getPosY(), this.getPosX(), direction) || counter == 0){
                pacLab.setIcon(stop);
                direction = 'e';
            }
            else
                drawPacMan();  
            if(died){ 
                pacLab.setIcon(stop);
                direction = 'e';
                posX = 13;
                posY = 23;
                imagePosX = map.getSpriteSize() * posX;
                imagePosY = map.getSpriteSize() * (posY + 3);
                died = false;
                pacLab.setBounds(imagePosX, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
                lives--;
                if(lives == 2)
                    map.getL3().setIcon(nulla);
                if(lives == 1) 
                    map.getL2().setIcon(nulla);
                if(lives == 0)
                    map.getL1().setIcon(nulla);
                map.getL().afterDie();
            }
            if(lives < 0)
                map.endGame();
            
            if(counter == 0){
                System.out.println("helo");
                map.getL().setInGame(false);
                //map.getL().superStop();
                Ghost.setSuperStop(true);
                map.getL().newLevel();
                
                FileWriter w = null;
                try {
                    w=new FileWriter("highscore.txt");
                } catch (IOException ex) {
                    Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedWriter b;
                b=new BufferedWriter (w);
                try {
                    b.write(Integer.toString(map.getHighPoints()));
                    b.flush();
                b.close();
                } catch (IOException ex) {
                    Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
    
 public void drawPacMan(){

    if(this.getPosX()== map.getCOLUMNS()-2 && map.getMatMapIJ(this.getPosY(), 0)!=0){
        this.setPosX(2);
        imagePosX=map.getSpriteSize()*this.getPosX();
        return;
    }
        if(this.getPosX()== 1 && map.getMatMapIJ(this.getPosY(), map.getCOLUMNS()-1)!=0){
        this.setPosX(map.getCOLUMNS()-3);
        imagePosX=map.getSpriteSize()*this.getPosX();
        return;
    }
           
    if(this.getPosY()== map.getROWS()-2 && map.getMatMapIJ(0, this.getPosX())!=0){
        this.setPosY(2);
        imagePosY=map.getSpriteSize()*this.getPosY();
        return;
    }
    if(this.getPosY()== 1 && map.getMatMapIJ(map.getROWS()-1,this.getPosX())!=0){
        this.setPosY(map.getROWS()-3);
        imagePosY=map.getSpriteSize()*this.getPosY();
        return;
    }
    if(matBack[this.getPosY()][this.getPosX()] == 1){
        matBack[this.getPosY()][this.getPosX()] = 0;
        counter--;
        map.setPoints(map.getPoints()+10);
    } 
    if(matBack[this.getPosY()][this.getPosX()] == 3){
        matBack[this.getPosY()][this.getPosX()] = 0;
        superPac=true;
        map.setPoints(map.getPoints()+100);
        Timer t = new Timer();  
        TimerTask tt = new TimerTask() {  
            public void run() {  
                superPac=false; 
                map.getL().endBlue();
            };  
        };  
        t.schedule(tt,10000);  
    } 

        if(direction == 'R' && !map.checkWall(this.getPosY(), this.getPosX(), 'R')){
            pacLab.setIcon(right);
            for(int i=0; i<map.getSpriteSize(); i++){
                try {
                sleep(8);
            } catch (InterruptedException ex) {
                Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
            }
                imagePosX += 1;
                pacLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);

                if(imagePosX%map.getSpriteSize() == 0){
                    posX++;
                    JLabel lab = new JLabel();
                    lab.setIcon(nulla);
                    lab.setBounds(imagePosX, imagePosY, map.getSpriteSize(), map.getSpriteSize());
                    map.add(lab, 7);
                }
            }
            return;
        }
        if(direction == 'L' && !map.checkWall(this.getPosY(), this.getPosX(), 'L')){
            pacLab.setIcon(left);
            for(int i=0; i<map.getSpriteSize(); i++){
                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagePosX -= 1;
                pacLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
                if(imagePosX%map.getSpriteSize() == 0){
                    posX--;
                    JLabel lab = new JLabel();
                    lab.setIcon(nulla);
                    lab.setBounds(imagePosX, imagePosY, map.getSpriteSize(), map.getSpriteSize());
                    map.add(lab, 8);
                } 
            }
            return;
        }
        
       if(direction == 'U' && !map.checkWall(this.getPosY(), this.getPosX(), 'U')){
           pacLab.setIcon(up);
            for(int i=0; i<map.getSpriteSize(); i++){
                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagePosY -= 1;
                pacLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);

                if(imagePosY%map.getSpriteSize() == 0){
                    posY--;
                    JLabel lab = new JLabel();
                    lab.setIcon(nulla);
                    lab.setBounds(imagePosX, imagePosY, map.getSpriteSize(), map.getSpriteSize());
                    map.add(lab, 8);
                }
            }
            return;
       }
        if(direction == 'D' && !map.checkWall(this.getPosY(), this.getPosX(), 'D')){
            pacLab.setIcon(down);
            for(int i=0; i<map.getSpriteSize(); i++){
                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagePosY += 1;
                pacLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);

                if(imagePosY%map.getSpriteSize() == 0){
                    posY++;
                    JLabel lab = new JLabel();
                    lab.setIcon(nulla);
                    lab.setBounds(imagePosX, imagePosY, map.getSpriteSize(), map.getSpriteSize());
                    map.add(lab, 8);
                }
            }
            return;
       }
        
    }

    public int getImagePosX() {
        return imagePosX;
    }

    public void setImagePosX(int imagePosX) {
        this.imagePosX = imagePosX;
    }

    public void setImagePosY(int imagePosY) {
        this.imagePosY = imagePosY;
    }

    public int getImagePosY() {
        return imagePosY;
    }

    public boolean isDied() {
        return died;
    }
    public boolean isSuper() {
        return superPac;
    }
    public void setDied(boolean died) {
        this.died = died;
    }
    
}
