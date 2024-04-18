package controller;

import model.logic.Impact;
import model.objectsModel.BulletModel;
import model.objectsModel.EnemyModel;
import model.objectsModel.OIGModel;

public class GameManager {

    public static void OIGDeath(OIGModel oigModel){
        if (oigModel instanceof EnemyModel){
            EnemyOnDeath((EnemyModel) oigModel);
        }
        if (oigModel instanceof BulletModel){
            BulletOnDeath((BulletModel) oigModel);
        }
        Controller.removeOIG(oigModel.getId());
    }

    public static void EnemyOnDeath(EnemyModel enemy){
        //////////////// Spawn Collectables //////////////// todo

        //////////////////////////////////////////////////// todo
    }

    public static void BulletOnDeath(BulletModel bullet){
        new Impact(bullet.getPosition()).MakeImpact();
    }

}
