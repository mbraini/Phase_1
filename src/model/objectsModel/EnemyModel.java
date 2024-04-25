package model.objectsModel;

import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.EpsilonGravity;
import model.interfaces.HasVertices;
import model.interfaces.ImpactAble;
import model.interfaces.MoveAble;

public abstract class EnemyModel extends OIGModel implements EpsilonGravity, MoveAble , ImpactAble {
    @Override
    public void epsilonGravity() {
        Vector enemy = this.position;
        Vector epsilon = OIGModel.OIGs.get(0).position;
        Vector way = Utils.VectorAdd(epsilon ,Utils.ScalarInVector(-1 ,enemy));
        way = Utils.VectorWithSize(way ,1);
        this.velocity = Utils.ScalarInVector(Constants.ENEMY_LINEAR_SPEED,way);
    }

    @Override
    public void move() {


        velocity = Utils.VectorAdd(velocity ,Utils.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y) / 2) * Constants.UPS;

        setPosition(position.x + xMoved ,position.y + yMoved);
        theta += omega;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,omega);
    }
}
