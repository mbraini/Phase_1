package controller;

import controller.SoundEffects.Sound;
import controller.Spawn.Spawn;
import controller.animations.EpsilonInControlAnimation;
import model.logic.Impact;
import model.objectsModel.BulletModel;
import model.objectsModel.CollectiveModel;
import model.objectsModel.EnemyModel;
import model.objectsModel.OIGModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
        try {
            new Sound(Constants.enemyOnDeathSound).play();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
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


    public static void EpsilonWin() {
        GameState.hasWon = true;
        new EpsilonInControlAnimation(Controller.getEpsilon()).StartAnimation();
    }
}
