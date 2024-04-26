package view.Abilities;

import controller.Constants;
import controller.Controller;
import controller.animations.DashAnimation;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.ImpactAble;
import model.objectsModel.OIGModel;

public class Banish extends RegularAbility{
    @Override
    public void performAbility() {
        Vector collisionPoint = Controller.getEpsilon().getPosition();
        for (int i = 0; i < OIGModel.OIGs.size() ; i++){
            if (OIGModel.OIGs.get(i) instanceof ImpactAble) {
                Vector direction;
                direction = Utils.VectorAdd(Utils.ScalarInVector(-1, collisionPoint), OIGModel.OIGs.get(i).getPosition());
                double distance = Utils.VectorSize(direction);
                if (distance == 0 || distance>= 800)
                    continue;
                new DashAnimation(OIGModel.OIGs.get(i), direction , Constants.DASH_TIME * 3 ,400).StartAnimation();
            }
        }
    }
}
