package model.objectsModel;

import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;

public class BulletModel extends OIGModel implements IsCircle, MoveAble {

    public BulletModel(Vector position , Vector direction ,String id){
        this.position = position;
        this.velocity = Utils.VectorWithSize(direction , Constants.BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 1;
    }

    @Override
    public void move() {
        velocity = Utils.VectorAdd(velocity ,acceleration);
        position = Utils.VectorAdd(position ,velocity);
    }

    @Override
    public double getRadios() {
        return Constants.BULLET_DIAMETER / 2;
    }
}