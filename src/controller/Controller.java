package controller;

import controller.animations.GameStartAnimation;
import model.objectsModel.*;
import view.objectsView.*;

public abstract class Controller {


    public static void startGame() {
        Spawn.SpawnEpsilon();
    }

    public static void addOIG(OIGModel oigModel){
        addOIGModel(oigModel);
        addOIGView(oigModel);
    }

    public static void addOIGView(OIGModel oigModel){
        if (oigModel instanceof EpsilonModel){
            EpsilonView epsilon = new EpsilonView(oigModel.getPosition() ,oigModel.getTheta() ,oigModel.getId());
            OIGView.OIGs.add(epsilon);
        }
        else if (oigModel instanceof TrigorathModel){
            OIGView.OIGs.add(new TrigorathView(oigModel.getPosition() ,oigModel.getTheta() ,oigModel.getId()));
        }
        else if (oigModel instanceof SquarantineModel){
            OIGView.OIGs.add(new SquarantineView(oigModel.getPosition() ,oigModel.getTheta() ,oigModel.getId()));
        }
        else if (oigModel instanceof BulletModel){
            OIGView.OIGs.add(new BulletView(oigModel.getPosition() ,oigModel.getTheta() ,oigModel.getId()));
        }
    }

    public static void addOIGModel(OIGModel oigModel){
        OIGModel.OIGs.add(oigModel);
    }

    public static void removeOIG(String id){
        removeOIGModel(id);
        removeOIGView(id);
    }

    public static void removeOIGModel(String id){
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i).getId().equals(id)){
                OIGModel.OIGs.remove(i);
                return;
            }
        }
    }

    public static void removeOIGView(String id){
        for (int i = 0 ;i < OIGView.OIGs.size() ;i++){
            if (OIGView.OIGs.get(i).getId().equals(id)){
                OIGView.OIGs.remove(i);
                return;
            }
        }
    }

    public static void gameStartAnimation() {
        (new GameStartAnimation()).StartAnimation();
    }

    public static void SpawnEnemyReq(){
        Spawn.SpawnEnemy();
    }
}
