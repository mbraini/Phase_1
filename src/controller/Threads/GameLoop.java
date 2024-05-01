package controller.Threads;

import controller.Constants;
import controller.Controller;
import controller.GameManager;
import controller.GameState;
import controller.animations.FrameAnimation;
import controller.helper.Pair;
import controller.helper.Vector;
import controller.actionlisteners.EpsilonMovement;
import model.interfaces.EpsilonGravity;
import model.interfaces.MoveAble;
import model.logic.Collision;
import model.logic.Impact;
import model.objectsModel.BulletModel;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;
import view.game.GameFrame;
import view.objectsView.OIGView;

import java.util.ArrayList;

public class GameLoop extends Thread{
    GameFrame gameFrame;

    public GameLoop (GameFrame gameFrame){
        super();
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaView = 0;
        double deltaModel = 0;
        while (!GameState.isPause && !GameState.isOver){
            long now = System.nanoTime();
            deltaView += (now - lastTime) / ns;
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.UPS){
                UpdateModel();
                deltaModel = 0;
            }
            if (deltaView >= Constants.FPS){
                UpdateView();
                deltaView  = 0;
            }
        }
    }

    void UpdateView(){
        assert OIGModel.OIGs != null;
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            OIGView.OIGs.get(i).setPosition(new Vector(OIGModel.OIGs.get(i).getPosition().getX() ,OIGModel.OIGs.get(i).getPosition().getY()));
            OIGView.OIGs.get(i).setTheta(OIGModel.OIGs.get(i).getTheta());
        }
        GameFrame.windowKill.repaint();
    }

    void UpdateModel(){
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i) instanceof MoveAble)
                ((MoveAble) OIGModel.OIGs.get(i)).move();
            if (OIGModel.OIGs.get(i) instanceof EpsilonGravity) {
                ((EpsilonGravity) OIGModel.OIGs.get(i)).epsilonGravity();
            }
            if (OIGModel.OIGs.get(i) instanceof BulletModel){
                BulletBorderCollision((BulletModel) OIGModel.OIGs.get(i));
            }
        }


        /////
        ArrayList<Pair> collisions = new ArrayList<>();
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            for (int j = 0 ;j < OIGModel.OIGs.size() ;j++){
                if (i == j)
                    continue;
                Pair pair = new Pair(i ,j);
                if (Pair.Contains(collisions ,pair))
                    continue;
                if (Collision.IsColliding(OIGModel.OIGs.get(i) ,OIGModel.OIGs.get(j))){
                    new Collision().CollisionResponse(OIGModel.OIGs.get(i) ,OIGModel.OIGs.get(j));
                    collisions.add(new Pair(i ,j));
                }
            }
        }
        CheckObjectDeath();
        EpsilonBorderCollision();
        /////reqs
        CheckRequests();
        /////
    }

    private void CheckRequests() {
        if (GameState.time >= 3)
            GameManager.CheckNewWave();
        Controller.CheckRegularAbilities();
        Controller.CheckSpecialAbilities();
        Controller.CheckAddOrRemoveObjectRequest();
    }

    private void CheckObjectDeath() {
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i).getHP() <= 0){
                GameManager.OIGDeath(OIGModel.OIGs.get(i));
                return;
            }
        }
    }

    private void EpsilonBorderCollision() {
        EpsilonModel epsilon = (EpsilonModel) OIGModel.OIGs.get(0);
        Vector direction = new Vector(epsilon.getVelocity().x ,epsilon.getVelocity().y);
        if (epsilon.getPosition().x <= Constants.EPSILON_DIMENSION.width / 2d){
            epsilon.setPosition(Constants.EPSILON_DIMENSION.width / 2d + 1 ,epsilon.getPosition().getY());
            direction.setX(1);
            new Impact(new Vector(Constants.EPSILON_DIMENSION.width / 2d ,epsilon.getPosition().y)).MakeImpact();
        }
        else if (epsilon.getPosition().x >= GameFrame.windowKill.getWidth() - Constants.EPSILON_DIMENSION.width / 2d){
            epsilon.setPosition(GameFrame.windowKill.getWidth() - Constants.EPSILON_DIMENSION.width / 2d - 1 ,epsilon.getPosition().getY());
            direction.setX(-1);
            new Impact(new Vector(GameFrame.windowKill.getWidth() - Constants.EPSILON_DIMENSION.width / 2d ,epsilon.getPosition().y)).MakeImpact();
        }
        else if (epsilon.getPosition().y <= Constants.EPSILON_DIMENSION.height / 2d){
            epsilon.setPosition(epsilon.getPosition().getX() ,Constants.EPSILON_DIMENSION.height / 2d + 1);
            direction.setY(1);
            new Impact(new Vector(epsilon.getPosition().x ,Constants.EPSILON_DIMENSION.height / 2d)).MakeImpact();
        }
        else if (epsilon.getPosition().y >= GameFrame.windowKill.getHeight() - Constants.EPSILON_DIMENSION.height / 2d){
            epsilon.setPosition(epsilon.getPosition().getX() ,GameFrame.windowKill.getHeight() - Constants.EPSILON_DIMENSION.height / 2d - 1);
            direction.setY(-1);
            new Impact(new Vector(epsilon.getPosition().x ,GameFrame.windowKill.getHeight() - Constants.EPSILON_DIMENSION.height / 2d)).MakeImpact();
        }
    }

    private void BulletBorderCollision(BulletModel bullet){
        if (bullet.getPosition().x <= Constants.EPSILON_DIMENSION.width / 2d){
            ///////todo
            FrameAnimation frameAnimation = new FrameAnimation(gameFrame ,0 ,0 ,0 ,Constants.FRAME_BULLET_RESIZE,Constants.FRAME_BULLET_RESIZE_TIME);
            bullet.setHP(-1);
            frameAnimation.StartAnimation();
            ///////todo
        }
        else if (bullet.getPosition().x >= gameFrame.getWidth() - Constants.EPSILON_DIMENSION.width / 2d){
            ///////todo
            new FrameAnimation(gameFrame ,0 ,0 ,Constants.FRAME_BULLET_RESIZE ,0,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            bullet.setHP(-1);
            ///////todo
        }
        else if (bullet.getPosition().y <= Constants.EPSILON_DIMENSION.height / 2d){
            ///////todo
            new FrameAnimation(gameFrame ,Constants.FRAME_BULLET_RESIZE ,0 ,0 ,0,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            bullet.setHP(-1);
            ///////todo
        }
        else if (bullet.getPosition().y >= gameFrame.getHeight() - Constants.EPSILON_DIMENSION.height / 2d){
            ///////todo
            new FrameAnimation(gameFrame ,0 ,Constants.FRAME_BULLET_RESIZE ,0 ,0,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            bullet.setHP(-1);
            ///////todo
        }
    }

}