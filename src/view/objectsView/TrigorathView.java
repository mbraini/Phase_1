package view.objectsView;

import controller.Constants;
import controller.helper.Vector;
import model.objectsModel.TrigorathModel;

import java.awt.*;

public class TrigorathView extends EnemyView{
    public TrigorathView(Vector position , double theta ,String id){
        this.position = position;
        this.theta = theta;
        this.id = id;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(-theta ,position.getX() ,position.getY());
        g2d.drawImage(Constants.trigorathImage ,(int) position.x - Constants.TRIGORATH_DIMENTION.width / 2 ,(int) position.y - (Constants.TRIGORATH_DIMENTION.height * 2) / 3 ,Constants.TRIGORATH_DIMENTION.width ,Constants.TRIGORATH_DIMENTION.height ,null);
        g2d.rotate(theta ,position.getX() ,position.getY());
    }
}
