package model.logic;

import controller.Constants;
import controller.Controller;
import controller.GameState;
import controller.animations.DashAnimation;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.HasVertices;
import model.interfaces.IsCircle;
import model.interfaces.IsPolygon;
import model.objectsModel.*;

import java.util.ArrayList;

public class CollisionHandler {
    Vector collisionPoint;
    public CollisionHandler(Vector collisionPoint){
        this.collisionPoint = collisionPoint;
    }
    public void EpsilonEnemy(EpsilonModel epsilon ,EnemyModel enemy){
        for (int i = 0 ;i < ((IsPolygon)enemy).getVertices().size() ;i++){
            if (Collision.IsInCircle(epsilon ,((IsPolygon)enemy).getVertices().get(i))){
                GameState.hp -= Constants.MELEI_ATTACK;
                break;
            }
        }
        for (int i = 0 ;i < Controller.getEpsilonVertices().size() ;i++){
            if (Collision.IsColliding(Controller.getEpsilonVertices().get(i) ,enemy)){
                enemy.setHP(enemy.getHP() - Constants.MELEI_ATTACK);
                break;
            }
        }
        PullOut(epsilon ,enemy);
        new Impact(collisionPoint).MakeImpact();
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
        bullet.setHP(-1);
        enemy.setHP(enemy.getHP() - Constants.EPSILON_DAMAGE);
    }


    private void PullOut(OIGModel attacker, OIGModel defender) {
        if (attacker instanceof IsPolygon && defender instanceof IsPolygon) {
            Vector attackerP = Utils.VectorAdd(Utils.ScalarInVector(-1, collisionPoint), attacker.getPosition());
            attackerP = Utils.VectorWithSize(attackerP, 1);
            while (Collision.IsColliding(attacker, defender)) {
                attacker.setPosition(Utils.VectorAdd(attackerP, attacker.getPosition()));
                collisionPoint = Utils.VectorAdd(collisionPoint ,attackerP);
                ((HasVertices) attacker).MoveVertices(attackerP);
            }
        }
        else if (attacker instanceof IsCircle && defender instanceof IsPolygon){
            Vector attackerP = Utils.VectorAdd(Utils.ScalarInVector(-1, collisionPoint), attacker.getPosition());
            attackerP = Utils.VectorWithSize(attackerP, 1);
            while (Collision.IsColliding(attacker, defender)){
                attacker.setPosition(Utils.VectorAdd(attacker.getPosition() ,attackerP));
                collisionPoint = Utils.VectorAdd(collisionPoint ,attackerP);
                if (attacker instanceof EpsilonModel)
                    ((EpsilonModel) attacker).UpdateVertices(attackerP.x ,attackerP.y ,0);
            }
        }
    }

    public void EpsilonCollective(EpsilonModel epsilon, CollectiveModel collective) {
        collective.setHP(-1);
    }
}
