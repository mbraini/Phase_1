package controller.actionlisteners;

import controller.Configs;
import controller.Constants;
import controller.Controller;
import controller.actionlisteners.EpsilonMovementALs.xStopperAL;
import controller.actionlisteners.EpsilonMovementALs.yStopperAL;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class EpsilonMovement implements KeyListener {
    HashSet<Integer> keys = new HashSet<>();
    EpsilonModel epsilon;
    Vector direction = new Vector(0 ,0);
    public static Timer xStopper;
    public static Timer yStopper;

    public EpsilonMovement(EpsilonModel epsilon){
        this.epsilon = epsilon;
        xStopper = new Timer(Configs.EPSILON_DECELERATION_TIME, new xStopperAL(Controller.getEpsilon()));
        yStopper = new Timer(Configs.EPSILON_DECELERATION_TIME, new yStopperAL(Controller.getEpsilon()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
        CalculateDirection();
        changeDirection();
    }

    private void CalculateDirection() {
        Vector newDirection = new Vector(0 ,0);
        if (keys.contains(37)){
            newDirection.setX(newDirection.getX() - 1);
        }
        if (keys.contains(38)){
            newDirection.setY(newDirection.getY() - 1);
        }
        if (keys.contains(39)){
            newDirection.setX(newDirection.getX() + 1);
        }
        if (keys.contains(40)){
            newDirection.setY(newDirection.getY() + 1);
        }
        direction = new Vector(newDirection.x ,newDirection.y);
    }

    private void changeDirection() {
        if (direction.Equals(new Vector(0 ,0))) {
            epsilon.setAcceleration(0 ,0);
            return;
        }
        epsilon.setAcceleration(Utils.VectorWithSize(direction , Configs.EPSILON_ACCELERATION));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
        CalculateDirection();
        double xVelocity = epsilon.getVelocity().x;
        double yVelocity = epsilon.getVelocity().y;
        if (xVelocity != 0 && direction.x == 0){
            epsilon.setAcceleration(-xVelocity / Configs.EPSILON_DECELERATION_TIME ,epsilon.getAcceleration().y);
            xStopper.start();
        }
        if (yVelocity != 0 && direction.y == 0){
            epsilon.setAcceleration(epsilon.getAcceleration().x ,-yVelocity / Configs.EPSILON_DECELERATION_TIME);
            yStopper.start();
        }
    }
}