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
        this.omega = Constants.ENEMY_ROTATION_SPEED;
    }

    @Override
    public void move() {


        velocity = Utils.VectorAdd(velocity ,Utils.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);

        omega += alpha * Constants.UPS;
        double thetaMoved = ((2 * omega - alpha * Constants.UPS) / 2) * Constants.UPS;
        theta = theta + thetaMoved;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,thetaMoved);
    }
}
