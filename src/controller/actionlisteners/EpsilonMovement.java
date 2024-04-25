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
import java.util.HashSet;
import java.util.Set;

public class EpsilonMovement implements KeyListener {
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
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
        if (e.getKeyCode() == 37){
            left = true;
            direction.setX(-1);
        }
        if (e.getKeyCode() == 38){
            up = true;
            direction.setY(-1);
        }
        if (e.getKeyCode() == 39){
            right = true;
            direction.setX(1);
        }
        if (e.getKeyCode() == 40){
            down = true;
            direction.setY(1);
        }
        if (direction.Equals(new Vector(0 ,0))) {
            epsilon.setAcceleration(0 ,0);
            return;
        }
        epsilon.setAcceleration(Utils.VectorWithSize(direction ,Constants.EPSILON_ACCELERATION));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37){
            left = false;
            direction.setX(0);
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
            up = false;
            direction.setY(0);
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
            System.out.println("HI");
            right = false;
            direction.setX(0);
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
            down = false;
            direction.setY(0);
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
