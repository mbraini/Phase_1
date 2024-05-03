package controller;
import controller.Config.Configs;
import controller.SoundEffects.Sound;
import controller.Spawn.Spawn;
import controller.animations.GameStartAnimation;
import model.objectsModel.*;
import view.Abilities.*;
import view.game.GameFrame;
import view.objectsView.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
public abstract class Controller {
    private static ArrayList<OIGModel> addedObjects = new ArrayList<>();
    private static ArrayList<OIGModel> removedObjects = new ArrayList<>();
    public static ArrayList<RegularAbilitiesEnum> regularAbility = new ArrayList<>();
    private static ArrayList<SpecialAbilitiesEnum> specialAbility = new ArrayList<>();
    public static void startGame() {
        Controller.ResetModel();
        Controller.ResetView();
        Controller.ResetController();
        /////////////
        SpecialAbility.reset();
        /////////////
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
        Spawn.SpawnWave();
    }

    public static void EndTheGame() {
        try {
            new Sound(Constants.endSound).play();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        EndController();
        updateConfigs();
        GameFrame.windowKill.end();
        GameFrame.endGame.start();
    }

    public static void updateConfigs() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/controller/Config/Configs.txt"));
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println("XP: " + (int) GameState.xp);
            printStream.println("Ares: " + Ares.isAvailable);
            printStream.println("Aceso: " + Aceso.isAvailable);
            printStream.println("Proteus: " + Proteus.isAvailable);
            Configs.XP = (int) GameState.xp;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void EndController() {
        GameState.isOver = true;
    }

    private static void ResetController() {
        GameState.isOver = false;
        GameState.hasWon = false;
        Constants.EPSILON_DIMENSION = new Dimension(Constants.EPSILON_FINAL_DIMENSION.width ,Constants.EPSILON_FINAL_DIMENSION.height);
        Configs.VERTICES = 0;
        Configs.EXTRA_DAMAGE = 0;
        if (Configs.DIFFICULTY == 3){
            Constants.TRIGORATH_DIMENTION = new Dimension(50 ,50);
            Constants.Squarantine_DIMENTION = new Dimension(50 ,50);
        }
        else if (Configs.DIFFICULTY == 2){
            Constants.TRIGORATH_DIMENTION = new Dimension(70 ,70);
            Constants.Squarantine_DIMENTION = new Dimension(70 ,70);
        }
        else if (Configs.DIFFICULTY == 1){
            Constants.TRIGORATH_DIMENTION = new Dimension(90 ,90);
            Constants.Squarantine_DIMENTION = new Dimension(90 ,90);
        }
    }

    private static void ResetView() {
        OIGView.OIGs = new ArrayList<>();
    }

    private static void ResetModel() {
        OIGModel.OIGs = new ArrayList<>();
        EpsilonModel.vertices = new ArrayList<>();
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
                    continue;
                case empower:
                    new Empower().performAbility();
                    continue;
                case heal:
                    new Heal().performAbility();
            }
        }
        regularAbility = new ArrayList<>();
    }

    public static void CheckSpecialAbilities(){
        if (specialAbility.isEmpty())
            return;
        for (int i = 0 ;i < specialAbility.size() ;i++) {
            switch (specialAbility.get(i)) {
                case ares:
                    new Ares().performAbility();
                    continue;
                case aceso:
                    new Aceso().performAbility();
                    continue;
                case proteus:
                    new Proteus().performAbility();
            }
        }
        specialAbility = new ArrayList<>();
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
    public static void specialAbilityRequest(SpecialAbilitiesEnum specialAbilitiesEnum) {
        specialAbility.add(specialAbilitiesEnum);
    }

    public static ArrayList<EpsilonVertex> getEpsilonVertices() {
        return getEpsilon().getVertices();
    }

}
