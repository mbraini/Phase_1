package controller.actionlisteners;

import controller.Constants;
import controller.Controller;
import controller.GameState;
import controller.Spawn;
import controller.helper.Helper;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class EpsilonAiming implements MouseListener {

    double timer;
    static public double extraAim;
    public EpsilonAiming(){
        timer = 0;
        extraAim = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ((GameState.time - timer) * 1000 >= Constants.AIMING_PAUSE_TIME){
            timer = GameState.time;
            EpsilonModel epsilon = Controller.getEpsilon();
            Vector clickedPoint = new Vector(e.getX() ,e.getY());
            if (clickedPoint.Equals(epsilon.getPosition()))
                return;
            Vector direction = Utils.VectorAdd(Utils.ScalarInVector(-1 ,epsilon.getPosition()) ,clickedPoint);
            Vector position = Utils.VectorAdd(Utils.VectorWithSize(direction ,Constants.BULLET_DIAMETER / 2 + Constants.EPSILON_DIMENSION.width) ,epsilon.getPosition());
            int constant = -1;
            Spawn.SpawnBullet(position ,direction);
            for (int i = 0; i < extraAim ;i++) {
                constant = constant * (-1);
                Vector direction2 = Utils.RotateByTheta(direction, new Vector(0 ,0), Math.PI / 12 * constant);
                if (i / 2 == 1)
                    constant -= 1;
                Spawn.SpawnBullet(position ,direction2);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
