package view.objectsView;

import controller.Constants;
import controller.helper.Vector;
import model.objectsModel.SquarantineModel;

import java.awt.*;

public class SquarantineView extends EnemyView{
    public SquarantineView(Vector position ,double theta ,String id){
        this.position = position;
        this.theta = theta;
        this.id = id;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(-theta ,position.getX() ,position.getY());
        g2d.drawImage(Constants.squarantineImage ,(int) position.x - Constants.Squarantine_DIMENTION.width / 2  ,(int) position.y - Constants.Squarantine_DIMENTION.height / 2 ,Constants.Squarantine_DIMENTION.width ,Constants.Squarantine_DIMENTION.height ,null);
        g2d.rotate(theta ,position.getX() ,position.getY());
        g2d.setColor(Color.YELLOW);
        g2d.drawString("a" ,(int) position.x ,(int) position.y);
    }
}
