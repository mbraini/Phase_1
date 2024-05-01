package controller.Spawn;

import controller.*;
import controller.Config.Configs;
import controller.SoundEffects.Sound;
import controller.helper.Helper;
import controller.helper.Vector;
import model.objectsModel.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Spawn {
    static final Random random = new Random();
    public static int enemyMaxCount;
    private static int enemySpawned = 0;
    private static Timer enemySpawner;

    public static void SpawnEpsilon(){
        Vector position = new Vector(Constants.GAME_WIDTH / 2d   ,Constants.GAME_HEIGHT / 2d);
        Vector velocity = new Vector(0 ,0);
        EpsilonModel epsilon = new EpsilonModel(position ,velocity , Helper.RandomStringGenerator(Constants.ID_SIZE));
//        Controller.addRequest(epsilon);
        Controller.addOIG(epsilon);
    }

    public static void SpawnWave(){
        if (GameState.wave == 2){
            //////
            GameManager.EpsilonWin();
            //////
//            Controller.EndTheGame();
            return;
        }
        try {
            Sound sound = new Sound(Constants.waveSpawnSound);
            sound.play();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        setCounts();
        SpawnEnemy();
        enemySpawner = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpawnEnemy();
                enemySpawned++;
                if (enemySpawned >= enemyMaxCount){
                    enemySpawner.stop();
                    enemySpawned = 0;
                }
            }
        });
        enemySpawner.start();
        GameState.wave++;
    }

    private static void setCounts() {
        if (Configs.DIFFICULTY == 1){
            enemyMaxCount = (int) (2 * GameState.wave);
        }
        else if (Configs.DIFFICULTY == 2){
            enemyMaxCount =(int) (2.5 * GameState.wave);
        }
        else {
            enemyMaxCount = (int) (3 * GameState.wave);
        }
    }

    private static void SpawnEnemy(){
        int rand = random.nextInt(2);
        Vector position = randomPosition();
        if (rand == 0){
            TrigorathModel trigorath = new TrigorathModel(position ,Helper.RandomStringGenerator(Constants.ID_SIZE));
            Controller.addRequest(trigorath);
        }
        else {
            SquarantineModel squarantine = new SquarantineModel(position ,Helper.RandomStringGenerator(Constants.ID_SIZE));
            Controller.addRequest(squarantine);
        }
    }

    public static void SpawnBullet(Vector position ,Vector direction){
        BulletModel bullet = new BulletModel(position ,direction ,Helper.RandomStringGenerator(Constants.ID_SIZE));
        Controller.addRequest(bullet);
    }

    public static Vector randomPosition(){
        int x = random.nextInt(-Constants.ENEMY_SPAWN_MARGIN , Application.gameFrame.getWidth() + Constants.ENEMY_SPAWN_MARGIN);
        int y = 0;
        if (x < -Constants.ENEMY_SPAWN_MARGIN || x > Application.gameFrame.getWidth() + Constants.ENEMY_SPAWN_MARGIN){
            y = random.nextInt(-Constants.ENEMY_SPAWN_MARGIN ,Application.gameFrame.getHeight() + Constants.ENEMY_SPAWN_MARGIN);
        }
        else {
            int rand = random.nextInt(2);
            if (rand == 0){
                y = random.nextInt(-Constants.ENEMY_SPAWN_MARGIN ,0);
            }
            else {
                y = random.nextInt(Application.gameFrame.getHeight() + Constants.ENEMY_SPAWN_MARGIN ,Application.gameFrame.getHeight() + Constants.ENEMY_SPAWN_MARGIN + Constants.TRIGORATH_DIMENTION.height);
            }
        }
        return new Vector(x ,y);
    }

    public static void SpawnCollective(EnemyModel enemy) {
        int count = 0;
        int value = 0;
        if (enemy instanceof TrigorathModel) {
            count = 2;
            value = 5;
        }
        else if (enemy instanceof SquarantineModel) {
            count = 1;
            value = 5;
        }
        for (int i = 0 ;i < count ;i++){
            double rx = random.nextInt((int) enemy.getPosition().x - Constants.COLLECTIVE_BOX_DIMENSION.width / 2 ,(int) enemy.getPosition().x + Constants.COLLECTIVE_BOX_DIMENSION.width / 2);
            double ry = random.nextInt((int) enemy.getPosition().y - Constants.COLLECTIVE_BOX_DIMENSION.height / 2 ,(int) enemy.getPosition().y + Constants.COLLECTIVE_BOX_DIMENSION.height / 2);
            CollectiveModel collective = new CollectiveModel(new Vector(rx ,ry) ,Helper.RandomStringGenerator(Constants.ID_SIZE) ,value ,GameState.time);
            Controller.addRequest(collective);
        }
    }
}
