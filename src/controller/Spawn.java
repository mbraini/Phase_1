package controller;

import controller.Application;
import controller.Constants;
import controller.Controller;
import controller.helper.Helper;
import controller.helper.Vector;
import jdk.jshell.execution.Util;
import model.objectsModel.*;
import view.objectsView.OIGView;

import java.util.Random;

public class Spawn {
    static final Random random = new Random();
    public static int enemyCount;

    public static void SpawnEpsilon(){
        Vector position = new Vector(Constants.GAME_WIDTH / 2d   ,Constants.GAME_HEIGHT / 2d);
        Vector velocity = new Vector(-.5 ,-.5);
        EpsilonModel epsilon = new EpsilonModel(position ,velocity , Helper.RandomStringGenerator(Constants.ID_SIZE));
        Controller.addOIG(epsilon);
    }

    public static void SpawnEnemy(){
        if (Constants.GAME_DIFFICULTY.equals("HARD")){
            enemyCount = (int) 1;
        }
        for (int i = 0 ;i < enemyCount ;i++){
            int rand = random.nextInt(2);
            Vector position = randomPosition();
            if (rand == 0){
                TrigorathModel trigorath = new TrigorathModel(position ,Helper.RandomStringGenerator(Constants.ID_SIZE));
                Controller.addOIG(trigorath);
            }
            else {
                SquarantineModel squarantine = new SquarantineModel(position ,Helper.RandomStringGenerator(Constants.ID_SIZE));
                Controller.addOIG(squarantine);
            }
        }
        GameState.wave++;
    }

    public static void SpawnBullet(Vector position ,Vector direction){
        BulletModel bullet = new BulletModel(position ,direction ,Helper.RandomStringGenerator(Constants.ID_SIZE));
        Controller.addOIG(bullet);
    }

    public static Vector randomPosition(){
        int x = random.nextInt(-Constants.Squarantine_DIMENTION.width ,Application.gameFrame.getWidth() + Constants.TRIGORATH_DIMENTION.width);
        int y = 0;
        if (x < 0 || x > Application.gameFrame.getWidth()){
            y = random.nextInt(-Constants.Squarantine_DIMENTION.height ,Application.gameFrame.getHeight() + Constants.TRIGORATH_DIMENTION.height);
        }
        else {
            int rand = random.nextInt(2);
            if (rand == 0){
                y = random.nextInt(-Constants.Squarantine_DIMENTION.height ,0);
            }
            else {
                y = random.nextInt(Application.gameFrame.getHeight() ,Application.gameFrame.getHeight() + Constants.TRIGORATH_DIMENTION.height);
            }
        }
        return new Vector(x ,y);
    }

}
