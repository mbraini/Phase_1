package model.objectsModel;

import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;

import java.util.ArrayList;

public class TrigorathModel extends EnemyModel implements HasVertices, IsPolygon {
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
