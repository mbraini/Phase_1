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
        velocity = Utils.VectorAdd(velocity ,acceleration);
        position = Utils.VectorAdd(position ,velocity);
        theta += omega;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices();
    }
}
