package model.objectsModel;

import controller.Constants;
import controller.helper.Vector;
import model.interfaces.IsCircle;

public class CollectiveModel extends OIGModel implements IsCircle {
    int value;

    public CollectiveModel(Vector position , String id ,int value){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 1;
        this.value = value;
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
}
