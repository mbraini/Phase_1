package view.objectsView;

import controller.Constants;
import controller.helper.Vector;

import java.awt.*;

public class CollectiveView extends OIGView{

    public CollectiveView(Vector position ,String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.MAGENTA);
        g2d.fillOval((int) (position.x - Constants.COLLECTIVE_RADIOS) ,(int) (position.y - Constants.COLLECTIVE_RADIOS) , (int) Constants.COLLECTIVE_RADIOS * 2 ,(int) Constants.COLLECTIVE_RADIOS * 2);
    }
}
