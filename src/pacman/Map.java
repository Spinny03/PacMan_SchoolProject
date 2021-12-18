/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Ako-Nee Chan
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Map extends JPanel{
    private final int ROWS;
    private final int COLUMNS;
    private final int spriteSize;
    private int points;
    private int highPoints;
    private final int[][] matMap;
    private JLabel scoreg;
    private JLabel score;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private GameController l;
    private GameFrame frame;
    
    public Map(GameFrame frame){
        ROWS = 31;
        COLUMNS = 28;
        this.frame = frame;
        points = 0;
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("highscore.txt"));
        } catch (FileNotFoundException ex) {
            highPoints =0;
        }
        highPoints = Integer.parseInt(fileScanner.next());
        spriteSize = frame.getWidth()/COLUMNS;
        matMap = new int[ROWS][COLUMNS];
        this.initMap();
        this.setSize(frame.getWidth(), frame.getHeight());
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setFocusable(true);
        l = new GameController(this);
        frame.addKeyListener(l);
        

    }
    
    public boolean checkWall(int i,int j,char dir){
        if(dir == 'R'){
            if(matMap[i][j+1] == 0)
                return true;
            return false;
        }
        if(dir == 'L'){
            if(matMap[i][j-1] == 0)
                return true;
            return false;
        }
        if(dir == 'U'){
            if(matMap[i-1][j] == 0)
                return true;
            return false;
        }
        if(dir == 'D'){
            if(matMap[i+1][j] == 0)
                return true;
            return false;
        }
        return false;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        if(Integer.parseInt(score.getText()) <= Integer.parseInt(scoreg.getText())){
            highPoints = points;
            score.setText(Integer.toString(this.highPoints));
        }
        scoreg.setText(Integer.toString(this.points));
    }
    
    public int getMatMapIJ(int i,int j){
        return matMap[i][j];
    }
    
    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getSpriteSize() {
        return spriteSize;
    }

    public GameController getL() {
        return l;
    }

    public void setL(GameController l) {
        this.l = l;
    }

    public JLabel getL1() {
        return l1;
    }

    public void setL1(JLabel l1) {
        this.l1 = l1;
    }

    public JLabel getL2() {
        return l2;
    }

    public void setL2(JLabel l2) {
        this.l2 = l2;
    }

    public JLabel getL3() {
        return l3;
    }

    public void setL3(JLabel l3) {
        this.l3 = l3;
    }

    public void endGame(){
        this.removeAll();
        Ghost.setSuperStop(true);
        frame.removeKeyListener(l);
        frame.remove(this);
        frame.add(new EndScreen(frame,this));
        frame.revalidate();
        frame.repaint();
    }
    
    public void initLives(PacMan pac){
        
        ImageIcon nulla = new ImageIcon(new ImageIcon("sprite/nulla.png").getImage().getScaledInstance(spriteSize*2, spriteSize*2, Image.SCALE_DEFAULT));
        
        if(pac.getLives() == 2)
            this.getL3().setIcon(nulla);
        if(pac.getLives() == 1){
            this.getL3().setIcon(nulla);
            this.getL2().setIcon(nulla);
        }
        if(pac.getLives() == 0){
            this.getL3().setIcon(nulla);
            this.getL2().setIcon(nulla);
            this.getL1().setIcon(nulla);
        }
            
    }

    public int getHighPoints() {
        return highPoints;
    }
    
    public void initMap(){
        int i=0, j=0;
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File("lv/lv_base.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (i<ROWS){
            while(j<COLUMNS){
                matMap[i][j] = Integer. parseInt(fileScanner.next());
                j++;
            } 
        j=0;
        i++;
        }  
        
        i=0;
        j=0;
        int x,y;
        ImageIcon imageI = new ImageIcon(new ImageIcon("sprite/1up.gif").getImage().getScaledInstance(spriteSize*3, spriteSize, Image.SCALE_DEFAULT));
                JLabel up = new JLabel();
                up.setIcon(imageI);
                up.setBounds(spriteSize*2, 1, spriteSize*3, spriteSize);
                this.add(up, -1);
                
        ImageIcon imageIm = new ImageIcon(new ImageIcon("sprite/hg.png").getImage().getScaledInstance(spriteSize*10, spriteSize, Image.SCALE_DEFAULT));
                JLabel hg = new JLabel();
                hg.setIcon(imageIm);
                hg.setBounds(spriteSize*9, 1, spriteSize*10, spriteSize);
                this.add(hg, -1);     
                
        ImageIcon life = new ImageIcon(new ImageIcon("sprite/pacman/life.png").getImage().getScaledInstance(spriteSize*2, spriteSize*2, Image.SCALE_DEFAULT));
                l1 = new JLabel();
                l1.setIcon(life);
                l1.setBounds(spriteSize*6, spriteSize*34, spriteSize*2, spriteSize*2);
                this.add(l1, -1);
                
                l2 = new JLabel();
                l2.setIcon(life);
                l2.setBounds(spriteSize*4, spriteSize*34, spriteSize*2, spriteSize*2);
                this.add(l2, -1);
                
                l3 = new JLabel();
                l3.setIcon(life);
                l3.setBounds(spriteSize*2, spriteSize*34, spriteSize*2, spriteSize*2);
                this.add(l3, -1);
        
        ImageIcon cili = new ImageIcon(new ImageIcon("sprite/ciliegie.png").getImage().getScaledInstance(spriteSize*2, spriteSize*2, Image.SCALE_DEFAULT));
                JLabel ci = new JLabel();
                ci.setIcon(cili);
                ci.setBounds(spriteSize*24, spriteSize*34, spriteSize*2, spriteSize*2);
                this.add(ci, -1);
                
                
        score = new JLabel();
        score.setForeground(new Color(222,222,222));
        score.setText(Integer.toString(highPoints));
        score.setFont(new Font("Press Start 2P", Font.BOLD, spriteSize));
        score.setBounds(spriteSize*15, spriteSize, spriteSize*8, spriteSize);
        this.add(score, -1);   
        
        
        scoreg = new JLabel();
        scoreg.setForeground(Color.white); 
        scoreg.setText(Integer.toString(points));
        scoreg.setForeground(new Color(222,222,222));
        scoreg.setFont(new Font("Press Start 2P", Font.BOLD, spriteSize));
        scoreg.setBounds(spriteSize*4, spriteSize, spriteSize*8, spriteSize);
        this.add(scoreg, -1);  
        
        for(y=spriteSize*3;i<31;y=y+spriteSize,i++){
            for(x=0,j=0;j<28;x=x+spriteSize,j++){
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(spriteType(i,j)).getImage().getScaledInstance(spriteSize, spriteSize, Image.SCALE_DEFAULT));
                JLabel lab = new JLabel();
                lab.setIcon(imageIcon);
                lab.setBounds(x, y, spriteSize, spriteSize);
                this.add(lab, -1);
            }
        }
         
    }   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public String spriteType(int y, int x){
        if(matMap[y][x] == 1){
            return "sprite/dot.png";
        }
        if(matMap[y][x] == 2 || matMap[y][x] == 5 || matMap[y][x] == 6|| matMap[y][x] == 7){
            return "sprite/nulla.png";
        }
        if(matMap[y][x] == 3){
            return "sprite/power_pill.gif";
        }
      
        
        if(matMap[y][x] == 0){//non mi convince
            if(x == 0 || y == 0 || x == 27 || y == 30 ){
                if(x==0 && y>0 && y<30 && matMap[y][x+1] == 0 && matMap[y+1][x] == 0 && matMap[y+1][x+1] != 0 && matMap[y-1][x+1] == 0 && matMap[y-1][x] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_2.png";
                }
                if(x==0 && y>0 && y<30 && matMap[y][x+1] == 0 && matMap[y+1][x] == 0 && matMap[y-1][x+1] != 0 && matMap[y+1][x+1] == 0 && matMap[y-1][x] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_1.png";
                }
                
                
                if(x==27 && y>0 && y<30 && matMap[y][x-1] == 0 && matMap[y-1][x] == 0 && matMap[y-1][x-1] != 0 && matMap[y+1][x-1] == 0 && matMap[y+1][x] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_4.png";
                }
                if(x==27 && y>0 && y<30 && matMap[y][x-1] == 0 && matMap[y-1][x] == 0 && matMap[y+1][x-1] != 0 && matMap[y-1][x-1] == 0 && matMap[y+1][x] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_3.png";
                }
                
                
                if(y==0 && x>0 && x<27 && matMap[y][x-1] == 0 && matMap[y+1][x] == 0 && matMap[y+1][x-1] != 0 && matMap[y][x+1] == 0 && matMap[y+1][x+1] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_8.png";
                }
                if(y==0 && x>0 && x<27 && matMap[y][x-1] == 0 && matMap[y+1][x] == 0 && matMap[y+1][x+1] != 0 && matMap[y][x+1] == 0 && matMap[y+1][x-1] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_7.png";
                }
                
                
                if(y==30 && x>0 && x<27 && matMap[y][x-1] == 0 && matMap[y-1][x] == 0 && matMap[y-1][x-1] != 0 && matMap[y][x+1] == 0 && matMap[y-1][x+1] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_6.png";
                }
                if(y==30 && x>0 && x<27 && matMap[y][x-1] == 0 && matMap[y-1][x] == 0 && matMap[y-1][x+1] != 0 && matMap[y][x+1] == 0 && matMap[y-1][x-1] == 0){
                    return "sprite/fine/angolo/fine_curva_interna_5.png";
                }
                
                
                if(x==0 && y>0 && y<31 && matMap[y][x+1] != 0){
                    return "sprite/fine/muro/fine_sinistra.png";
                }
                
                if(x==0 && y>0 && y<=30 && matMap[y][x+1] == 0 && matMap[y-1][x] == 0 && matMap[y-1][x+1] != 0){
                    return "sprite/fine/angolo/fine_curva_esterna_basso_sinistra.png";
                }
                if(x==27 && y>0 && y<=30 && matMap[y][x-1] == 0 && matMap[y-1][x] == 0 && matMap[y-1][x-1] != 0){
                    return "sprite/fine/angolo/fine_curva_esterna_basso_destra.png";
                }
                if(x==27 && y>=0 && y<=30 && matMap[y][x-1] == 0 && matMap[y+1][x] == 0 && matMap[y+1][x-1] != 0){
                    return "sprite/fine/angolo/fine_curva_esterna_alto_destra.png";
                }
                if(x==0 && y>=0 && y<=30 && matMap[y][x+1] == 0 && matMap[y+1][x] == 0 && matMap[y+1][x+1] != 0){
                    return "sprite/fine/angolo/fine_curva_esterna_alto_sinistra.png";
                }
                
                if(x>0 && y==30 && matMap[y-1][x] != 0){
                    return "sprite/fine/muro/fine_basso.png";
                }
                if(x==27 && y>0 && y<31 && matMap[y][x-1] != 0){
                    return "sprite/fine/muro/fine_destra.png";
                }
                if(x>0 && y==0 && matMap[y+1][x] != 0){
                    return "sprite/fine/muro/fine_alto.png";
                }
                
                

            if((x==27 && matMap[y][x-1] == 0) || (matMap[y][x+1]==0 && x==0)){
                if(matMap[y-1][x] == 6 && matMap[y+1][x]!=0 ){ 
                    return "sprite/fine/muro/fine_alto.png";
                }
                if(matMap[y+1][x] == 6 && matMap[y-1][x]!=0 ){
                    return "sprite/fine/muro/fine_basso.png";
                }
            } 
                
                return "sprite/error.png";
            }
            
            //nulla
            if(matMap[y-1][x] == 0 && matMap[y+1][x]==0 && matMap[y][x-1]==0 && matMap[y][x+1]==0 
                    && matMap[y-1][x-1]==0 && matMap[y-1][x+1]==0 && matMap[y+1][x-1]==0 && matMap[y+1][x+1]==0){
                return "sprite/nulla.png";
            }
            //muri verticali 
            if(matMap[y-1][x] == 0 && matMap[y+1][x]==0 ){
                if(matMap[y][x-1] == 0 && matMap[y][x+1]!=0 ){
                        return "sprite/dentro/muro/muro_destra.png";
                }
                if(matMap[y][x+1] == 0 && matMap[y][x-1]!=0 ){
                    return "sprite/dentro/muro/muro_sinistra.png";
                }
            }
            //muri orizontali 
            if(matMap[y][x-1] == 0 && matMap[y][x+1]==0){
                if(matMap[y-1][x] == 0 && matMap[y+1][x]!=0 ){ 
                    return "sprite/dentro/muro/muro_basso.png";
                }
                if(matMap[y+1][x] == 0 && matMap[y-1][x]!=0 ){
                    return "sprite/dentro/muro/muro_alto.png";
                }
            } 
            
            
            
            
            //muri fine
            if(matMap[y-1][x] == 0 && matMap[y+1][x]==0 ){
                if(matMap[y][x-1] == 6 || matMap[y][x-1] == 7 && matMap[y][x+1]!=0 ){
                        return "sprite/fine/muro/fine_sinistra.png";
                }
                if(matMap[y][x+1] == 6 || matMap[y][x+1] == 7 && matMap[y][x-1]!=0 ){
                    return "sprite/fine/muro/fine_destra.png";
                }
            }
            if(matMap[y][x-1] == 0 && matMap[y][x+1]==0){
                if(matMap[y-1][x] == 6 || matMap[y-1][x] == 7 && matMap[y+1][x]!=0 ){ 
                    return "sprite/fine/muro/fine_alto.png";
                }
                if(matMap[y+1][x] == 6 || matMap[y+1][x] == 7 && matMap[y-1][x]!=0 ){
                    return "sprite/fine/muro/fine_basso.png";
                }
            } 
            if(matMap[y][x-1] == 5 && matMap[y][x+1]==0){
                return "sprite/spawn/spawn_destra.png";
            }
            if(matMap[y][x+1] == 5 && matMap[y][x-1]==0){
                return "sprite/spawn/spawn_sinistra.png";
            }
            
            
            if(matMap[y][x+1] == 0 && matMap[y-1][x]==0 && matMap[y-1][x+1]==7){
                return "sprite/spawn/spawn_basso_sinistra.png";
            }
            if(matMap[y][x-1] == 0 && matMap[y-1][x]==0 && matMap[y-1][x-1]==7){
                return "sprite/spawn/spawn_basso_destra.png";
            }
            if(matMap[y][x+1] == 0 && matMap[y+1][x]==0 && matMap[y+1][x+1]==7){
                return "sprite/spawn/spawn_alto_sinistra.png";
            }
            if(matMap[y][x-1] == 0 && matMap[y+1][x]==0 && matMap[y+1][x-1]==7){
                return "sprite/spawn/spawn_alto_destra.png";
            }
            
            
            
            //curva convessa 
            if(matMap[y][x-1] == 0 && matMap[y-1][x]==0 && matMap[y+1][x+1]==0 && matMap[y-1][x-1]!=0){
                return "sprite/dentro/curva/curva_int_basso_destra.png";
            }
            if(matMap[y][x-1] == 0 && matMap[y+1][x]==0 && matMap[y-1][x+1]==0 && matMap[y+1][x-1]!=0){
                return "sprite/dentro/curva/curva_int_alto_destra.png";
            }
            if(matMap[y][x+1] == 0 && matMap[y-1][x]==0 && matMap[y+1][x-1]==0 && matMap[y-1][x+1]!=0){
                return "sprite/dentro/curva/curva_int_basso_sinistra.png";
            }
            if(matMap[y][x+1] == 0 && matMap[y+1][x]==0 && matMap[y-1][x-1]==0 && matMap[y+1][x+1]!=0){
                return "sprite/dentro/curva/curva_int_alto_sinistra.png";
            }
            //curva concava 
            if(matMap[y][x-1] == 0 && matMap[y-1][x]==0){
                return "sprite/dentro/curva/curva_basso_destra.png";
            }
            if(matMap[y][x-1] == 0 && matMap[y+1][x]==0){
                return "sprite/dentro/curva/curva_alto_destra.png";
            }
            if(matMap[y][x+1] == 0 && matMap[y-1][x]==0){
                return "sprite/dentro/curva/curva_basso_sinistra.png";
            }
            if(matMap[y][x+1] == 0 && matMap[y+1][x]==0){
                return "sprite/dentro/curva/curva_alto_sinistra.png";
            }
            if(matMap[y+1][x] == 5){
            return "sprite/spawn/spawn_alto.png";
            }    
        }  
    return "sprite/error.png";
    }
}