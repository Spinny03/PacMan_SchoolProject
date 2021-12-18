/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Chika
 */

import java.awt.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Ghost extends Thread{ 
    private int posX;
    private int posY;
    private int imagePosX;
    private int imagePosY;
    private final ImageIcon error;
    private ImageIcon down;
    private ImageIcon up;
    private ImageIcon right;
    private ImageIcon left;
    private ImageIcon stop;
    private final ImageIcon sick;
    private final Map map;
    private final PacMan pac;
    private final JLabel ghostLab;
    private static int liv=-1;
    private static boolean superStop = false;
    private char direction;
    
    public Ghost(Map map, char C, PacMan pac) {
        this.pac = pac;
        liv++;
        posX = 13;
        posY = 11;
        imagePosX = map.getSpriteSize() * posX;
        imagePosY = map.getSpriteSize() * (posY + 3);
        ghostLab  = new JLabel();
        this.map  = map;
        error     =  new ImageIcon(new ImageIcon("sprite/error.png").getImage().getScaledInstance(map.getSpriteSize(), map.getSpriteSize(), Image.SCALE_DEFAULT));
        sick      =  new ImageIcon(new ImageIcon("sprite/ghost/gSick.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        if(C=='B'){
            down  = new ImageIcon(new ImageIcon("sprite/ghost/B/B_down.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            up    = new ImageIcon(new ImageIcon("sprite/ghost/B/B_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            right = new ImageIcon(new ImageIcon("sprite/ghost/B/B_right.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            left  = new ImageIcon(new ImageIcon("sprite/ghost/B/B_left.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            stop  = new ImageIcon(new ImageIcon("sprite/ghost/B/B_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        }
        if(C=='G'){
            down  = new ImageIcon(new ImageIcon("sprite/ghost/G/G_down.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            up    = new ImageIcon(new ImageIcon("sprite/ghost/G/G_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            right = new ImageIcon(new ImageIcon("sprite/ghost/G/G_right.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            left  = new ImageIcon(new ImageIcon("sprite/ghost/G/G_left.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            stop  = new ImageIcon(new ImageIcon("sprite/ghost/G/G_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        }
        if(C=='P'){
            down  = new ImageIcon(new ImageIcon("sprite/ghost/P/P_down.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            up    = new ImageIcon(new ImageIcon("sprite/ghost/P/P_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            right = new ImageIcon(new ImageIcon("sprite/ghost/P/P_right.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            left  = new ImageIcon(new ImageIcon("sprite/ghost/P/P_left.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            stop  = new ImageIcon(new ImageIcon("sprite/ghost/P/P_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        }
        if(C=='R'){
            down  = new ImageIcon(new ImageIcon("sprite/ghost/R/R_down.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            up    = new ImageIcon(new ImageIcon("sprite/ghost/R/R_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            right = new ImageIcon(new ImageIcon("sprite/ghost/R/R_right.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            left  = new ImageIcon(new ImageIcon("sprite/ghost/R/R_left.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
            stop  = new ImageIcon(new ImageIcon("sprite/ghost/R/R_up.gif").getImage().getScaledInstance(map.getSpriteSize()*2, map.getSpriteSize()*2, Image.SCALE_DEFAULT));
        }
        ghostLab.setIcon(stop);
        ghostLab.setBounds(imagePosX, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
        map.add(ghostLab, liv);  
    }

    public static boolean isSuperStop() {
        return superStop;
    }

    public static void setSuperStop(boolean superStop) {
        Ghost.superStop = superStop;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getImagePosX() {
        return imagePosX;
    }

    public void setImagePosX(int imagePosX) {
        this.imagePosX = imagePosX;
    }

    public int getImagePosY() {
        return imagePosY;
    }

    public void setImagePosY(int imagePosY) {
        this.imagePosY = imagePosY;
    }

    @Override
    public void run(){
        int v;
        direction = 'R';
        Random rand = new Random();
        while(true){
            if( (direction != 'e' && (!map.checkWall(this.getPosY(), this.getPosX(), 'L') && !map.checkWall(this.getPosY(), this.getPosX(), 'U'))
              ||(!map.checkWall(this.getPosY(), this.getPosX(), 'R') && !map.checkWall(this.getPosY(), this.getPosX(), 'U'))
              ||(!map.checkWall(this.getPosY(), this.getPosX(), 'L') && !map.checkWall(this.getPosY(), this.getPosX(), 'D'))
              ||(!map.checkWall(this.getPosY(), this.getPosX(), 'R') && !map.checkWall(this.getPosY(), this.getPosX(), 'D')))){
                v = rand.nextInt(4)+1;
                if(v == 1 && direction != 'R'){
                    direction='R';
                }
                if(v == 2 && direction != 'U'){
                    direction='U';
                }
                if(v == 3 && direction != 'D'){
                    direction='D';
                }
                if(v == 4 && direction != 'L'){
                    direction='L';
                }
            }
            if(!superStop)
                drawGhost();
            if(pac.getPosX() == this.getPosX() && pac.getPosY() == this.getPosY() 
            || pac.getPosX()+1 == this.getPosX() && pac.getPosY() == this.getPosY()
            || pac.getPosX()-1 == this.getPosX() && pac.getPosY() == this.getPosY() 
            || pac.getPosX() == this.getPosX() && pac.getPosY()+1 == this.getPosY()
            || pac.getPosX() == this.getPosX() && pac.getPosY()-1 == this.getPosY()){
                if(pac.isSuper()){
                    posX = 13;
                    posY = 13;
                    imagePosX = map.getSpriteSize() * 13;   
                    imagePosY = map.getSpriteSize() * (13+3);  
                }
                else{
                    pac.setDied(true);
                }  
            }
        }
    }
    
    public void drawGhost(){
        
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

        if(direction == 'R' && !map.checkWall(this.getPosY(), this.getPosX(), 'R')){
            if(pac.isSuper()){
                ghostLab.setIcon(sick);
            }
            else{
            ghostLab.setIcon(right);
            }
            for(int i=0; direction != 'e' && i<map.getSpriteSize(); i++){

                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
                }

                imagePosX += 1;
                ghostLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);

                if(imagePosX%map.getSpriteSize() == 0){
                    posX++;
                }
            }
            return;
        }
        if(direction == 'L' && !map.checkWall(this.getPosY(), this.getPosX(), 'L')){
            if(pac.isSuper()){
                ghostLab.setIcon(sick);
            }
            else{
                ghostLab.setIcon(left);
            }
            for(int i=0; direction != 'e' && i<map.getSpriteSize(); i++){
                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagePosX -= 1;
                ghostLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
                if(imagePosX%map.getSpriteSize() == 0){
                    posX--;
                } 
            }
            return;
        }
        
        if(direction == 'U' && !map.checkWall(this.getPosY(), this.getPosX(), 'U')){
            if(pac.isSuper()){
                ghostLab.setIcon(sick);
            }
            else{
            ghostLab.setIcon(up);
            }
            for(int i=0; direction != 'e' && i<map.getSpriteSize(); i++){
                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagePosY -= 1;
                ghostLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);

                if(imagePosY%map.getSpriteSize() == 0){
                    posY--;
                }
            }
            return;
        }
        if(direction == 'D' && !map.checkWall(this.getPosY(), this.getPosX(), 'D')){
            if(pac.isSuper()){
                ghostLab.setIcon(sick);
            }
            else{
            ghostLab.setIcon(down);
            }
            for(int i=0; direction != 'e' && i<map.getSpriteSize(); i++){
                try {
                    sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagePosY += 1;
                ghostLab.setBounds(imagePosX-map.getSpriteSize()/2, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);

                if(imagePosY % map.getSpriteSize() == 0){
                    posY++;
                }
            }
            return;
       }    
    }
    
    public boolean isCage(){
        return (posX >= 11 && posX <= 16 && posY >= 13 && posY <= 15);
    }
    
    public void initGhost(boolean lev){
        direction = 'e';
        posX = 13;
        posY = 11;
        imagePosX = map.getSpriteSize() * posX;
        imagePosY = map.getSpriteSize() * (posY+3);
        ghostLab.setIcon(stop);
        ghostLab.setBounds(imagePosX, imagePosY-map.getSpriteSize()/2, map.getSpriteSize()*2, map.getSpriteSize()*2);
        if(lev)
            map.add(ghostLab, liv);
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
}
