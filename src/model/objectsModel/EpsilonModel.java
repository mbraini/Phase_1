package model.objectsModel;

import controller.Configs;
import controller.Constants;
import controller.helper.Helper;
import model.interfaces.HasVertices;
import model.interfaces.ImpactAble;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;
import controller.helper.Utils;
import controller.helper.Vector;

import java.util.ArrayList;

public class EpsilonModel extends OIGModel implements MoveAble, IsCircle , ImpactAble , HasVertices {
    public static ArrayList<Vector> vertices = new ArrayList<>();
    public EpsilonModel(Vector position , Vector velocity ,String id){
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector(0 ,0);
        this.id =  id;
        this.HP = 100;
        initVertices();
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
        if (currentSpeed > Constants.EPSILON_MAX_SPEED){
            setVelocity(getVelocity().x * Constants.EPSILON_MAX_SPEED / currentSpeed ,getVelocity().y * Constants.EPSILON_MAX_SPEED / currentSpeed);
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

    void initVertices(){
        if (Configs.VERTICES == 0)
            return;
        double theta = 2 * Math.PI / Configs.VERTICES;
        Vector origin = new Vector(getCenter().x ,getPosition().y - getRadios() - Constants.EPSILON_VERTICES_RADIOS);
        for (int i = 0; i < Configs.VERTICES ;i++){
            vertices.add(Utils.RotateByTheta(origin ,getCenter(),theta * i));
        }
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(vertices.get(i).getX() + xMoved ,vertices.get(i).getY() + yMoved));
            vertices.set(i , Utils.RotateByTheta(vertices.get(i) ,position ,omega));
        }
    }

    @Override
    public void MoveVertices(Vector trans) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,Utils.VectorAdd(vertices.get(i) ,trans));
        }
    }
    public ArrayList<Vector> getVertices(){
        return vertices;
    }
}
