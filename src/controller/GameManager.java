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
        CheckNewWave();
        //////////////// Spawn Collectables //////////////// todo

        //////////////////////////////////////////////////// todo
    }

    public static void BulletOnDeath(BulletModel bullet){
        new Impact(bullet.getPosition()).MakeImpact();
    }


    static void CheckNewWave(){
        int count = 0;
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i) instanceof EnemyModel){
                count++;
            }
        }
        if (count == 1)
            Spawn.SpawnEnemy();
    }


}
