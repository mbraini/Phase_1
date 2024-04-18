package controller.actionlisteners;

import controller.Constants;
import controller.GameState;
import controller.Spawn;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EpsilonAiming implements MouseListener {

    double timer;
    public EpsilonAiming(){
        timer = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((GameState.time - timer) * 1000 >= Constants.AIMING_PAUSE_TIME){
            timer = GameState.time;
            EpsilonModel epsilon = (EpsilonModel) OIGModel.OIGs.get(0);
            Vector clickedPoint = new Vector(e.getX() ,e.getY());
            if (clickedPoint.Equals(epsilon.getPosition()))
                return;
            Vector direction = Utils.VectorAdd(Utils.ScalarInVector(-1 ,epsilon.getPosition()) ,clickedPoint);
            Vector position = new Vector(epsilon.getPosition().x ,epsilon.getPosition().y);
            position = Utils.VectorAdd(Utils.VectorWithSize(direction ,Constants.BULLET_DIAMETER / 2 + Constants.EPSILON_DIMENSION.width) ,epsilon.getPosition());
            Spawn.SpawnBullet(position ,direction);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
