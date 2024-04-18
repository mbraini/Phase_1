package controller;

import model.objectsModel.EnemyModel;
import model.objectsModel.OIGModel;

public class GameManager {

    public static void OIGDeath(OIGModel oigModel){
        if (oigModel instanceof EnemyModel){
            EnemyOnDeath((EnemyModel) oigModel);
        }
        Controller.removeOIG(oigModel.getId());
    }

    public static void EnemyOnDeath(EnemyModel enemy){
        //////////////// Spawn Collectables //////////////// todo

        //////////////////////////////////////////////////// todo
    }

}
