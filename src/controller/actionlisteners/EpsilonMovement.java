package controller.actionlisteners;

import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EpsilonMovement implements KeyListener {
    HashSet<Integer> keys = new HashSet<>();
    Timer upTimer;
    Timer downTimer;
    Timer leftTimer;
    Timer rightTimer;
    EpsilonModel epsilon;
    Vector direction = new Vector(0 ,0);

    public EpsilonMovement(EpsilonModel epsilon){
        this.epsilon = epsilon;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
        direction.setX(0);
        direction.setY(0);
        if (keys.contains(37)){
            direction.setX(-1 + direction.getX());
        }
        if (keys.contains(38)){
            direction.setY(-1 + direction.getY());
        }
        if (keys.contains(39)){
            direction.setX(1 + direction.getX());
        }
        if (keys.contains(40)){
            direction.setY(1 + direction.getY());
        }
        if (direction.Equals(new Vector(0 ,0))) {
            return;
        }
        epsilon.setAcceleration(Utils.VectorWithSize(direction ,Constants.EPSILON_ACCELERATION));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
        if (e.getKeyCode() == 37){
            epsilon.getAcceleration().setX(Constants.EPSILON_DECELERATION);
            leftTimer = new Timer((int)(-epsilon.getVelocity().x / Constants.EPSILON_DECELERATION), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    epsilon.getAcceleration().setX(0);
                    epsilon.getVelocity().setX(0);
                    leftTimer.stop();
                }
            });
            leftTimer.start();
        }
        if (e.getKeyCode() == 38){
            epsilon.getAcceleration().setY(Constants.EPSILON_DECELERATION);
            upTimer = new Timer((int)(-epsilon.getVelocity().y / Constants.EPSILON_DECELERATION), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    epsilon.getAcceleration().setY(0);
                    epsilon.getVelocity().setY(0);
                    upTimer.stop();
                }
            });
            upTimer.start();
        }
        if (e.getKeyCode() == 39){
            epsilon.getAcceleration().setX(-Constants.EPSILON_DECELERATION);
            rightTimer = new Timer((int)(epsilon.getVelocity().x / Constants.EPSILON_DECELERATION), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    epsilon.getAcceleration().setX(0);
                    epsilon.getVelocity().setX(0);
                    rightTimer.stop();
                }
            });
            rightTimer.start();
        }
        if (e.getKeyCode() == 40){
            epsilon.getAcceleration().setY(-Constants.EPSILON_DECELERATION);
            downTimer = new Timer((int)(epsilon.getVelocity().y / Constants.EPSILON_DECELERATION), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    epsilon.getAcceleration().setY(0);
                    epsilon.getVelocity().setY(0);
                    downTimer.stop();
                }
            });
            downTimer.start();
        }
    }
}
