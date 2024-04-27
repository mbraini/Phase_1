package controller;
import controller.animations.GameStartAnimation;
import controller.helper.Vector;
import model.objectsModel.*;
import view.Abilities.*;
import view.game.GameFrame;
import view.objectsView.*;

import java.util.ArrayList;
public abstract class Controller {
    private static ArrayList<OIGModel> addedObjects = new ArrayList<>();
    private static ArrayList<OIGModel> removedObjects = new ArrayList<>();
    public static ArrayList<RegularAbilitiesEnum> regularAbility = new ArrayList<>();
    public static void startGame() {
        Controller.ResetModel();
        Controller.ResetView();
        Controller.ResetController();
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
        else if (oigModel instanceof CollectiveModel){
            OIGView.OIGs.add(new CollectiveView(oigModel.getPosition() ,oigModel.getId()));
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

    public static void EndTheGame() {
        EndController();
        GameFrame.windowKill.end();
        GameFrame.endGame.setVisible(true);
    }

    private static void EndController() {
        GameState.isOver = true;
    }

    private static void ResetController() {
        GameState.isOver = false;
    }

    private static void ResetView() {
        OIGView.OIGs = new ArrayList<>();
    }

    private static void ResetModel() {
        OIGModel.OIGs = new ArrayList<>();
    }

    public static EpsilonModel getEpsilon() {
        return (EpsilonModel)OIGModel.OIGs.get(0);
    }
    public static void CheckRegularAbilities(){
        if (regularAbility.isEmpty())
            return;
        for (int i = 0 ;i < regularAbility.size() ;i++) {
            switch (regularAbility.get(i)) {
                case banish:
                    new Banish().performAbility();
                    break;
                case empower:
                    new Empower().performAbility();
                    break;
                case heal:
                    new Heal().performAbility();
                    break;
            }
        }
        regularAbility = new ArrayList<>();
    }

    public static void CheckAddOrRemoveObjectRequest(){
        if (!addedObjects.isEmpty()){
            for (int i = 0 ;i < addedObjects.size() ;i++){
                addOIG(addedObjects.get(i));
            }
            addedObjects = new ArrayList<>();
        }
        if (!removedObjects.isEmpty()){
            for (int i = 0 ;i < removedObjects.size() ;i++){
                removeOIG(removedObjects.get(i).getId());
            }
            removedObjects = new ArrayList<>();
        }
    }

    public static void addRequest(OIGModel oigModel){
        addedObjects.add(oigModel);
    }

    public static void removeRequest(OIGModel oigModel){
        removedObjects.add(oigModel);
    }
    public static void regularAbilityRequest(RegularAbilitiesEnum regularAbilitiesEnum){
        regularAbility.add(regularAbilitiesEnum);
    }

    public static ArrayList<EpsilonVertex> getEpsilonVertices() {
        return getEpsilon().getVertices();
    }
}
