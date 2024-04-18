package model.objectsModel;

import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;

import java.util.ArrayList;

public class SquarantineModel extends EnemyModel implements HasVertices, IsPolygon {
    ArrayList<Vector> vertices;
    boolean epsilonGravityVisibility = true;
    public SquarantineModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
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
    public void UpdateVertices() {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(vertices.get(i).getX() + velocity.x ,vertices.get(i).getY() + velocity.y));
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
    public void setVisibility(boolean visibility) {
        epsilonGravityVisibility = visibility;
    }
}
