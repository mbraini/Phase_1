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
            vertices.set(i , Utils.RotateByTheta(vertices.get(i) ,position ,omega));
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
        super.epsilonGravity();
        ability();
    }

    @Override
    public void setVisibility(boolean visibility) {
        epsilonGravityVisibility = visibility;
    }

    @Override
    public void ability() {
        double distance = Utils.VectorSize(Utils.VectorAdd(Utils.ScalarInVector(-1 ,position) , Controller.getEpsilon().getPosition()));
        /////////////todo
        if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED / 4d);
        }
        if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 50){
            velocity = Utils.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED / 3d);
        }
        ////////////todo
    }
}
