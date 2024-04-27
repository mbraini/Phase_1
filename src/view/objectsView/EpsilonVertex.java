package view.objectsView;

import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.IsCircle;
import model.objectsModel.EpsilonModel;

import java.awt.*;

public class EpsilonVertex implements IsCircle {
    EpsilonModel epsilon;
    double theta;
    Vector position;
    public EpsilonVertex(EpsilonModel epsilon ,double theta){
        this.epsilon = epsilon;
        this.theta = theta;
        this.position = new Vector();
    }
    @Override
    public double getRadios() {
        return Constants.EPSILON_VERTICES_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return null;
    }
    public void draw(Graphics2D g2d){
        g2d.fillOval((int) position.x - Constants.EPSILON_VERTICES_RADIOS ,(int) position.y - Constants.EPSILON_VERTICES_RADIOS ,Constants.EPSILON_VERTICES_RADIOS * 2 ,Constants.EPSILON_VERTICES_RADIOS * 2);
    }

    public void Update() {
        Vector origin = new Vector(epsilon.getPosition().x ,epsilon.getPosition().y - epsilon.getRadios() - getRadios());
        position = Utils.RotateByTheta(origin ,epsilon.getPosition() ,theta);
    }

}
