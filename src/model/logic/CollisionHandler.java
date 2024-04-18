package model.logic;

import controller.Constants;
import controller.Controller;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.HasVertices;
import model.interfaces.IsCircle;
import model.interfaces.IsPolygon;
import model.objectsModel.BulletModel;
import model.objectsModel.EnemyModel;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

public class CollisionHandler {
    Vector collisionPoint;
    public CollisionHandler(Vector collisionPoint){
        this.collisionPoint = collisionPoint;
    }
    public void EpsilonEnemy(EpsilonModel epsilon ,EnemyModel enemy){

    }

    public void EnemyEnemy(EnemyModel a, EnemyModel b) {
        OIGModel attacker = a, defender = b;
        for (int i = 0; i < ((IsPolygon) b).getVertices().size(); i++) {
            if (collisionPoint.Equals(((IsPolygon) b).getVertices().get(i))) {
                attacker = b;
                defender = a;
            }
        }
        PullOut(attacker, defender);
        new Impact(collisionPoint).MakeImpact();
    }

    public void EnemyBullet(EnemyModel enemy, BulletModel bullet) {
        collisionPoint = bullet.getPosition();
        Controller.removeOIG(bullet.getId());
        enemy.setHP(enemy.getHP() - Constants.EPSILON_DAMAGE);
        new Impact(collisionPoint).MakeImpact();
    }


    private void PullOut(OIGModel attacker, OIGModel defender) {
        if (attacker instanceof IsPolygon && defender instanceof IsPolygon) {
            Vector attackerP = Utils.VectorAdd(Utils.ScalarInVector(-1, collisionPoint), attacker.getPosition());
            attackerP = Utils.VectorWithSize(attackerP, 1);
            while (Collision.IsColliding(attacker, defender)) {
                attacker.setPosition(Utils.VectorAdd(attackerP, attacker.getPosition()));
                ((HasVertices) attacker).MoveVertices(attackerP);
            }
        }
        else if (attacker instanceof IsCircle && defender instanceof IsPolygon){
            Vector attackerP = Utils.VectorAdd(Utils.ScalarInVector(-1, collisionPoint), attacker.getPosition());
            attackerP = Utils.VectorWithSize(attackerP, 1);
            while (Collision.IsColliding(attacker, defender)) {
                attacker.setPosition(Utils.VectorAdd(attacker.getPosition() ,attackerP));
            }
        }
    }
}
