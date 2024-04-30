package model.logic;

import controller.Constants;
import controller.animations.DashAnimation;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.EpsilonGravity;
import model.interfaces.ImpactAble;
import model.objectsModel.EpsilonModel;
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
                //////////////////todo
                if (distance >= Constants.IMPACT_AREA) {
                    return;
                }
                //////////////////todo
                if (distance == 0)
                    return;
                if (!(OIGModel.OIGs.get(i) instanceof EpsilonModel))
                    new DashAnimation(OIGModel.OIGs.get(i), direction ,Constants.DASH_TIME  ,100 ,Math.PI).StartAnimation();
                else
                    new DashAnimation(OIGModel.OIGs.get(i), direction ,Constants.DASH_TIME ,100 ,0).StartAnimation();
            }
        }
    }

}
