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
        if (Utils.VectorSize(way) == 0)
            return;
        way = Utils.VectorWithSize(way ,1);
        this.velocity = Utils.ScalarInVector(Constants.ENEMY_LINEAR_SPEED,way);
    }

    @Override
    public void move() {
        System.out.println("hi");
        velocity = Utils.VectorAdd(velocity ,Utils.ScalarInVector(Constants.UPS ,acceleration));
        double xP = (position.x + velocity.x * 2 - acceleration.x * Constants.UPS) * Constants.UPS / 2;
        double yP = (position.y + velocity.y * 2 - acceleration.y * Constants.UPS) * Constants.UPS / 2;
        setPosition(xP ,yP);
        theta += omega;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices();
    }
}
