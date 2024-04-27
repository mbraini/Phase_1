package view.objectsView;

import controller.Constants;
import controller.Controller;
import controller.helper.Vector;

import java.awt.*;
import java.util.ArrayList;

public class EpsilonView extends OIGView {
    public EpsilonView(Vector position ,double theta ,String id) {
        this.position = position;
        this.theta = theta;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(Constants.epsilonImage ,(int) position.getX() - Constants.EPSILON_DIMENSION.width / 2 ,(int) position.getY() - Constants.EPSILON_DIMENSION.height / 2,Constants.EPSILON_DIMENSION.width ,Constants.EPSILON_DIMENSION.height ,null);
        ArrayList<EpsilonVertex> vertices = Controller.getEpsilonVertices();
        g2d.setColor(Color.WHITE);
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.get(i).draw(g2d);
        }
    }
}
