package model.objectsModel;

import controller.Constants;
import model.interfaces.ImpactAble;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;
import controller.helper.Utils;
import controller.helper.Vector;

public class EpsilonModel extends OIGModel implements MoveAble, IsCircle , ImpactAble {
    public EpsilonModel(Vector position , Vector velocity ,String id){
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector(0 ,0);
        this.id =  id;
        this.HP = 100;
    }

    @Override
    public void move() {
        velocity = Utils.VectorAdd(velocity ,acceleration);
        position = Utils.VectorAdd(position ,velocity);
        checkMaxSpeed();
    }

    void checkMaxSpeed(){
        double currentSpeed = Math.sqrt(Math.pow(velocity.x ,2) + Math.pow(velocity.y ,2));
        assert currentSpeed != 0;
        if (currentSpeed > Constants.EPSILON_MAX_SPEED){
            setVelocity(getVelocity().x * Constants.EPSILON_MAX_SPEED / currentSpeed ,getVelocity().y * Constants.EPSILON_MAX_SPEED / currentSpeed);
        }
    }

    @Override
    public double getRadios() {
        return Constants.EPSILON_DIMENSION.height / 2d;
    }
}
