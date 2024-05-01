package model.objectsModel;

import controller.Config.Configs;
import controller.Constants;
import model.interfaces.HasVertices;
import model.interfaces.ImpactAble;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;
import controller.helper.Utils;
import controller.helper.Vector;

import java.util.ArrayList;

public class EpsilonModel extends OIGModel implements MoveAble, IsCircle , ImpactAble , HasVertices {
    public static ArrayList<EpsilonVertex> vertices = new ArrayList<>();
    public EpsilonModel(Vector position , Vector velocity ,String id){
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector(0 ,0);
        this.id =  id;
        this.HP = 100;
        vertices = new ArrayList<>();
    }

    @Override
    public void move() {
        velocity = Utils.VectorAdd(velocity ,Utils.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);
        ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,omega);
        checkMaxSpeed();
    }

    void checkMaxSpeed(){
        double currentSpeed = Math.sqrt(Math.pow(velocity.x ,2) + Math.pow(velocity.y ,2));
        assert currentSpeed != 0;
        if (currentSpeed > Configs.EPSILON_MAX_SPEED){
            setVelocity(getVelocity().x * Configs.EPSILON_MAX_SPEED / currentSpeed ,getVelocity().y * Configs.EPSILON_MAX_SPEED / currentSpeed);
        }
    }

    @Override
    public double getRadios() {
        return Constants.EPSILON_DIMENSION.height / 2d;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public void addVertex(){
        theta = 0;
        vertices = new ArrayList<>();
        double theta = 2 * Math.PI / Configs.VERTICES;
        for (int i = 0; i < Configs.VERTICES ;i++){
            vertices.add(new EpsilonVertex(this ,theta * i));
        }
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.get(i).setTheta(vertices.get(i).getTheta() + theta);
            vertices.get(i).Update();
        }
    }

    @Override
    public void MoveVertices(Vector trans) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.get(i).Update();
        }
    }
    public ArrayList<EpsilonVertex> getVertices(){
        return vertices;
    }
    public void Rotate(double theta){
        UpdateVertices(0 ,0 ,theta - this.theta);
        this.theta = theta;
    }
}
