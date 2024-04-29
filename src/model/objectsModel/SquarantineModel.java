package model.objectsModel;

import controller.Constants;
import controller.Controller;
import controller.animations.DashAnimation;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.Ability;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;

import java.util.ArrayList;

public class SquarantineModel extends EnemyModel implements HasVertices, IsPolygon , Ability {
    ArrayList<Vector> vertices;
    boolean epsilonGravityVisibility = true;
    public SquarantineModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 10;
        omega = Constants.ENEMY_ROTATION_SPEED;
        initVertices();
    }

    void initVertices(){
        vertices = new ArrayList<>();
        vertices.add(new Vector(position.x + (Constants.Squarantine_DIMENTION.width / 2d) ,position.y + (Constants.Squarantine_DIMENTION.height / 2d)));
        vertices.add(new Vector(position.x + (Constants.Squarantine_DIMENTION.width / 2d) ,position.y - (Constants.Squarantine_DIMENTION.height / 2d)));
        vertices.add(new Vector(position.x - (Constants.Squarantine_DIMENTION.width / 2d) ,position.y - (Constants.Squarantine_DIMENTION.height / 2d)));
        vertices.add(new Vector(position.x - (Constants.Squarantine_DIMENTION.width / 2d) ,position.y + (Constants.Squarantine_DIMENTION.height / 2d)));
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
    public void setVisibility(boolean visibility) {
        epsilonGravityVisibility = visibility;
    }

    @Override
    public void epsilonGravity() {
        if (getVisibility())
            ability();
    }

    @Override
    public void ability() {
        new DashAnimation(this ,Utils.VectorAdd(Utils.ScalarInVector(-1 ,position) , Controller.getEpsilon().getPosition()) ,600 ,100 ,Math.PI).StartAnimation();
    }
}
