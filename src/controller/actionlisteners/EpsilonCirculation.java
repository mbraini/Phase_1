package controller.actionlisteners;

import controller.Controller;
import controller.helper.Utils;
import controller.helper.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EpsilonCirculation implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vector mousePosition = new Vector(e.getX() ,e.getY());
        Vector epsilonPosition = Controller.getEpsilon().getPosition();
        if (mousePosition.Equals(epsilonPosition))
            return;
        Vector direction = Utils.VectorAdd(Utils.ScalarInVector(-1 ,epsilonPosition) ,mousePosition);
        double cosTheta = Utils.DotProduct(direction ,new Vector(0 ,-1)) / (Utils.VectorSize(direction));
        double theta = Math.acos(cosTheta);
        if ((direction.getX() >= 0 && direction.getY() >= 0) || (direction.getX() >= 0 && direction.getY() <= 0))
            theta *= -1;
        Controller.getEpsilon().Rotate(theta);
    }
}
