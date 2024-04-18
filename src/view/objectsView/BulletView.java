package view.objectsView;

import controller.Constants;
import controller.helper.Vector;

import java.awt.*;

public class BulletView extends OIGView{

    public BulletView(Vector position ,double theta ,String id){
        this.position = position;
        this.theta = theta;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int) position.x ,(int) position.y ,(int) Constants.BULLET_DIAMETER,(int) Constants.BULLET_DIAMETER);
    }
}
