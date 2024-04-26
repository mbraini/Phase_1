package model.objectsModel;

import controller.Constants;
import controller.Controller;
import controller.GameState;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.Ability;
import model.interfaces.EpsilonGravity;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;

public class CollectiveModel extends OIGModel implements IsCircle , Ability , EpsilonGravity , MoveAble {
    int value;
    double time;
    boolean epsilonVisibility = false;

    public CollectiveModel(Vector position , String id ,int value ,double time){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 1;
        this.value = value;
        this.time = time;
    }

    @Override
    public double getRadios() {
        return Constants.COLLECTIVE_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void ability() {
        if ((GameState.time - time) * 1000 >= Constants.COLLECTIVE_FADE){
            Controller.removeRequest(this);
        }
    }

    @Override
    public void epsilonGravity() {
        Vector distance = Utils.VectorAdd(Controller.getEpsilon().position ,Utils.ScalarInVector(-1 ,position));
        if (Utils.VectorSize(distance) <= Constants.COLLECTIVE_ABILITY_ACTIVATION_RADIOS){
            this.setVelocity(Utils.VectorWithSize(distance ,Constants.COLLECTIVE_VELOCITY));
        }
        ability();
    }

    @Override
    public boolean getVisibility() {
        return epsilonVisibility;
    }

    @Override
    public void setVisibility(boolean visibility) {
        epsilonVisibility = visibility;
    }

    @Override
    public void move() {
        velocity = Utils.VectorAdd(velocity ,Utils.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);
    }
}
