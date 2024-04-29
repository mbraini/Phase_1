package model.objectsModel;

import controller.Constants;
import controller.Controller;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.Ability;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;

import java.util.ArrayList;

public class TrigorathModel extends EnemyModel implements HasVertices, IsPolygon , Ability {
    public ArrayList<Vector> vertices;
    boolean epsilonGravityVisibility = true;
    public TrigorathModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 15;
        omega = Constants.ENEMY_ROTATION_SPEED;
        initVertices();
    }

    void initVertices(){
        vertices = new ArrayList<>();
        vertices.add(new Vector(position.x ,position.y - (Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 3d)));
        vertices.add(new Vector(position.x - Constants.TRIGORATH_DIMENTION.width / 2d ,position.y + (Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 6d)));
        vertices.add(new Vector(position.x + Constants.TRIGORATH_DIMENTION.width / 2d ,position.y + (Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 6d)));
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(vertices.get(i).getX() + xMoved ,vertices.get(i).getY() + yMoved));
            vertices.set(i , Utils.RotateByTheta(vertices.get(i) ,position ,theta));
        }
    }

    @Override
    public void MoveVertices(Vector trans) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,Utils.VectorAdd(vertices.get(i) ,trans));
        }
    }

    @Override
    public ArrayList<Vector> getVertices() {
        return vertices;
    }

    @Override
    public boolean getVisibility() {
        return epsilonGravityVisibility;
    }

    @Override
    public void epsilonGravity() {
        if (getVisibility()) {
            super.epsilonGravity();
            ability();
        }
    }

    @Override
    public void setVisibility(boolean visibility) {
        epsilonGravityVisibility = visibility;
    }

    @Override
    public void ability() {
        double distance = Utils.VectorSize(Utils.VectorAdd(Utils.ScalarInVector(-1 ,position) , Controller.getEpsilon().getPosition()));
        /////////////todo
        if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 160){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.6);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.6;
        }
        if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 280){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.4);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.4;
        }
        else if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 240){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.2);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.2;
        }
        else if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 200){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.1);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.1;
        }
        else if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 160){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED);
            omega = Constants.ENEMY_ROTATION_SPEED;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width * 2 / 3d + Constants.EPSILON_DIMENSION.width / 2d + 40){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.3);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.3;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 80){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.5);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.5;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 120){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.8);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.8;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 160){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.9);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.9;
        }
        ////////////todo
    }
}
