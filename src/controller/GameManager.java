package controller;

import controller.Spawn.Spawn;
import model.logic.Impact;
import model.objectsModel.BulletModel;
import model.objectsModel.CollectiveModel;
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
        if (oigModel instanceof CollectiveModel){
            CollectiveOnDeath((CollectiveModel) oigModel);
        }
        Controller.removeRequest(oigModel);
    }

    public static void EnemyOnDeath(EnemyModel enemy){
        //////////////// Spawn Collectables //////////////// todo
        Spawn.SpawnCollective(enemy);
        //////////////////////////////////////////////////// todo
    }

    public static void BulletOnDeath(BulletModel bullet){
        new Impact(bullet.getPosition()).MakeImpact();
    }

    public static void CollectiveOnDeath(CollectiveModel collective){
        GameState.xp += collective.getValue();
    }

    public static void CheckNewWave(){
        int count = 0;
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i) instanceof EnemyModel){
                count++;
            }
        }
        if (count <= 0)
            Controller.SpawnEnemyReq();
    }


}
