package controller.actionlisteners;

import controller.Constants;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class EpsilonMovement implements KeyListener {
    EpsilonModel epsilon;
    static Timer UpTimer;
    static Timer DownTimer;
    static Timer RightTimer;
    static Timer LeftTimer;
    static Set<Integer> pressedKeys;
    static boolean block = false;
    public EpsilonMovement(){
        epsilon =(EpsilonModel) OIGModel.OIGs.get(0);
        pressedKeys = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!block) {
            instantMovement(e.getKeyCode());
            pressedKeys.add(e.getKeyCode());
        }
        if ((pressedKeys.contains(37) && pressedKeys.contains(39)) || (pressedKeys.contains(38) && pressedKeys.contains(40))) {
            pressedKeys = new HashSet<>();
            epsilon.setAcceleration(0 ,0);
            epsilon.setVelocity(0 ,0);
            block = true;
            return;
        }
        if (pressedKeys.contains(38)){
            epsilon.setAcceleration(epsilon.getAcceleration().x ,epsilon.getAcceleration().y - Constants.EPSILON_ACCELERATION);
        }
        if (pressedKeys.contains(40)){
            epsilon.setAcceleration(epsilon.getAcceleration().x ,epsilon.getAcceleration().y + Constants.EPSILON_ACCELERATION);
        }
        if (pressedKeys.contains(37)){
            epsilon.setAcceleration(epsilon.getAcceleration().x - Constants.EPSILON_ACCELERATION ,epsilon.getAcceleration().y);
        }
        if (pressedKeys.contains(39)){
            epsilon.setAcceleration(epsilon.getAcceleration().x + Constants.EPSILON_ACCELERATION ,epsilon.getAcceleration().y);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        block = false;
        pressedKeys.remove(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if (UpTimer != null){
                if (UpTimer.isRunning()){
                    UpTimer.stop();
                }
            }
            epsilon.setAcceleration(epsilon.getAcceleration().x ,Constants.EPSILON_DECELERATION);
            UpTimer = new Timer(Constants.EPSILON_DECELERATION_TIME , new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (epsilon.getVelocity().y > 0){
                        epsilon.setAcceleration(epsilon.getAcceleration().x ,0);
                        epsilon.setVelocity(epsilon.getVelocity().x ,0);
                        UpTimer.stop();
                        UpTimer.removeActionListener(this);
                    }
                }
            });
            UpTimer.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if (DownTimer != null){
                if (DownTimer.isRunning()){
                    DownTimer.stop();
                }
            }
            epsilon.setAcceleration(epsilon.getAcceleration().x ,-Constants.EPSILON_DECELERATION);
            DownTimer = new Timer(Constants.EPSILON_DECELERATION_TIME , new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (epsilon.getVelocity().y < 0){
                        epsilon.setAcceleration(epsilon.getAcceleration().x ,0);
                        epsilon.setVelocity(epsilon.getVelocity().x ,0);
                        DownTimer.stop();
                        DownTimer.removeActionListener(this);
                    }
                }
            });
            DownTimer.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (LeftTimer != null){
                if (LeftTimer.isRunning()){
                    LeftTimer.stop();
                }
            }
            epsilon.setAcceleration(Constants.EPSILON_DECELERATION ,epsilon.getAcceleration().y);
            LeftTimer = new Timer(Constants.EPSILON_DECELERATION_TIME , new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (epsilon.getVelocity().x > 0){
                        epsilon.setAcceleration(0 ,epsilon.getAcceleration().y);
                        epsilon.setVelocity(0 ,epsilon.getVelocity().y);
                        LeftTimer.stop();
                        LeftTimer.removeActionListener(this);
                    }
                }
            });
            LeftTimer.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (RightTimer != null){
                if (RightTimer.isRunning()){
                    RightTimer.stop();
                }
            }
            epsilon.setAcceleration(-Constants.EPSILON_DECELERATION ,epsilon.getAcceleration().y);
            RightTimer = new Timer(Constants.EPSILON_DECELERATION_TIME , new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (epsilon.getVelocity().x < 0){
                        epsilon.setAcceleration(0 ,epsilon.getAcceleration().y);
                        epsilon.setVelocity(0 ,epsilon.getVelocity().y);
                        RightTimer.stop();
                        RightTimer.removeActionListener(this);
                    }
                }
            });
            RightTimer.start();
        }
    }

    void instantMovement(int keyCode){
        if (keyCode <= 36 || keyCode >= 41){
            return;
        }
        double x = 0;
        double y = 0;
        if (keyCode==37 && !pressedKeys.contains(keyCode)){
            x = -Constants.EPSILON_MAX_SPEED;
        }
        else if (keyCode == 38 && !pressedKeys.contains(keyCode)){
            y = -Constants.EPSILON_MAX_SPEED;
        }
        else if (keyCode==39 && !pressedKeys.contains(keyCode)){
            x = Constants.EPSILON_MAX_SPEED;
        }
        else if (keyCode == 40 && !pressedKeys.contains(keyCode)){
            y = Constants.EPSILON_MAX_SPEED;
        }
        epsilon.setPosition(epsilon.getPosition().x + x ,epsilon.getPosition().y + y);
    }

    public static void StopTimers(){
        UpTimer.stop();
        DownTimer.stop();
        LeftTimer.stop();
        RightTimer.stop();
    }
}
