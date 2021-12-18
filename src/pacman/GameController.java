/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Dostoevskij
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameController implements KeyListener{
    private final Map map;
    private PacMan pac;
    private boolean pause;
    private Ghost gRed;
    private Ghost gBlue;
    private Ghost gPink;
    private Ghost gG;
    private boolean lev;
    private boolean inGame;
    
    public GameController(Map map){
        inGame = true;
        lev = false;
        this.map = map;
        pause = false;
        pac   = new PacMan(map);
        gRed  = new Ghost(map,'R',pac);
        gBlue = new Ghost(map,'B',pac);
        gPink = new Ghost(map,'P',pac);
        gG    = new Ghost(map,'G',pac);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(lev){
            gRed.setDirection('L');
            gBlue.setDirection('L');
            gPink.setDirection('L');
            gG.setDirection('L');
            Ghost.setSuperStop(false);
            lev = false;
        }
        if(inGame){
            switch( keyCode ) { 
                case KeyEvent.VK_UP:
                    pac.setDirection('U');
                    if(pac.isAlive() && gRed.isAlive()){
                    }
                    else{
                        pac.start();
                        gRed.start();
                        gBlue.start();
                        gPink.start();
                        gG.start();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    pac.setDirection('D');
                    if(pac.isAlive() && gRed.isAlive()){
                    }
                    else{
                        pac.start();
                        gRed.start();
                        gBlue.start();
                        gPink.start();
                        gG.start();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    pac.setDirection('L');
                    if(pac.isAlive() && gRed.isAlive()){
                    }
                    else{
                        pac.start();
                        gRed.start();
                        gBlue.start();
                        gPink.start();
                        gG.start();
                    }
                    break;
                case KeyEvent.VK_RIGHT :
                    pac.setDirection('R');
                    if(pac.isAlive() && gRed.isAlive()){
                    }
                    else{
                        pac.start();
                        gRed.start();
                        gBlue.start();
                        gPink.start();
                        gG.start();
                    }
                    break;
                case KeyEvent.VK_ESCAPE :
                    if(!pause && pac.isAlive() && gRed.isAlive()){
                        pac.resume();
                        gRed.resume();
                        gBlue.resume();
                        gPink.resume();
                        gG.resume();
                        pause = true;
                        break;
                    }
                    if(pause && pac.isAlive() && gRed.isAlive()){
                        pac.suspend();
                        gRed.suspend();
                        gBlue.suspend();
                        gPink.suspend();
                        gG.suspend();
                        pause = false;
                        break;
                    }  
                    break;
            }
        }
        
    }
    
    public void newLevel(){
        lev = true;
        map.removeAll();
        map.initMap();
        map.initLives(pac);
        pac.initPac();
        gRed.initGhost(true);
        gBlue.initGhost(true);
        gPink.initGhost(true);
        gG.initGhost(true);
        map.revalidate();
        map.repaint();
        inGame = true;
    }
    
    public void killAll(){
        pac.stop();
        gRed.stop();
        gBlue.stop();
        gPink.stop();
        gG.stop();
        pac = null;
        gRed = null;
        gBlue = null;
        gPink = null;
        gG = null;
    
    }
    
    public void afterDie(){
        gRed.initGhost(false);
        gBlue.initGhost(false);
        gPink.initGhost(false);
        gG.initGhost(false);
        lev = true;
    }
    
    public void endBlue(){
        if(gRed.isCage()){
            gRed.initGhost(false);
            gRed.setDirection('R');
        }
        if(gBlue.isCage()){
            gBlue.initGhost(false);
            gBlue.setDirection('R');
        }
        if(gPink.isCage()){
            gPink.initGhost(false);
            gPink.setDirection('R');
        }
        if(gG.isCage()){
            gG.initGhost(false);
            gG.setDirection('R');
        } 
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
}
