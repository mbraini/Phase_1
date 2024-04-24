package model.logic;

import controller.Constants;
import controller.animations.DashAnimation;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.EpsilonGravity;
import model.interfaces.ImpactAble;
import model.objectsModel.OIGModel;

public class Impact {
    Vector collisionPoint;

    public Impact(Vector collisionPoint){
        this.collisionPoint = collisionPoint;
    }

    public void MakeImpact(){
        double distance;
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i) instanceof ImpactAble) {
                Vector direction;
                direction = Utils.VectorAdd(Utils.ScalarInVector(-1, collisionPoint), OIGModel.OIGs.get(i).getPosition());
                distance = Utils.VectorSize(direction);
                System.out.println(distance);
                //////////////////todo
                if (distance >= 500) {
                    return;
                }
                //////////////////todo
                new DashAnimation(OIGModel.OIGs.get(i), direction).StartAnimation();
            }
        }
    }

}
