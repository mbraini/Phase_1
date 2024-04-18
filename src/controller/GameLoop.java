package controller;

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

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaView = 0;
        double deltaModel = 0;
        while (!GameState.isPause){
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
        EpsilonModel epsilon = (EpsilonModel) OIGModel.OIGs.get(0);
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            if (OIGModel.OIGs.get(i) instanceof MoveAble)
                ((MoveAble) OIGModel.OIGs.get(i)).move();
            if (OIGModel.OIGs.get(i) instanceof EpsilonGravity) {
                if (((EpsilonGravity) OIGModel.OIGs.get(i)).getVisibility()) {
                    ((EpsilonGravity) OIGModel.OIGs.get(i)).epsilonGravity();
                }
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
                    ////////
                    if (OIGModel.OIGs.get(i) instanceof EpsilonGravity){
                        ((EpsilonGravity) OIGModel.OIGs.get(i)).setVisibility(false);
                    }
                    if (OIGModel.OIGs.get(j) instanceof EpsilonGravity){
                        ((EpsilonGravity) OIGModel.OIGs.get(j)).setVisibility(false);
                    }
                    /////////
                    new Collision().CollisionResponse(OIGModel.OIGs.get(i) ,OIGModel.OIGs.get(j));
                    collisions.add(new Pair(i ,j));
                }
            }
        }
        EpsilonBorderCollision();
    }

    private void EpsilonBorderCollision() {
        EpsilonModel epsilon = (EpsilonModel) OIGModel.OIGs.get(0);
        Vector direction = new Vector(epsilon.getVelocity().x ,epsilon.getVelocity().y);
        if (epsilon.getPosition().x <= Constants.EPSILON_DIMENSION.width / 2d){
            epsilon.setPosition(Constants.EPSILON_DIMENSION.width / 2d + 1 ,epsilon.getPosition().getY());
            direction.setX(1);
            EpsilonMovement.StopTimers();
            new Impact(new Vector(Constants.EPSILON_DIMENSION.width / 2d ,epsilon.getPosition().y)).MakeImpact();
        }
        else if (epsilon.getPosition().x >= Application.gameFrame.getWidth() - Constants.EPSILON_DIMENSION.width / 2d){
            epsilon.setPosition(Application.gameFrame.getWidth() - Constants.EPSILON_DIMENSION.width / 2d - 1 ,epsilon.getPosition().getY());
            direction.setX(-1);
            EpsilonMovement.StopTimers();
            new Impact(new Vector(Application.gameFrame.getWidth() - Constants.EPSILON_DIMENSION.width / 2d ,epsilon.getPosition().y)).MakeImpact();
        }
        else if (epsilon.getPosition().y <= Constants.EPSILON_DIMENSION.height / 2d){
            epsilon.setPosition(epsilon.getPosition().getX() ,Constants.EPSILON_DIMENSION.height / 2d + 1);
            direction.setY(1);
            EpsilonMovement.StopTimers();
            new Impact(new Vector(epsilon.getPosition().x ,Constants.EPSILON_DIMENSION.height / 2d)).MakeImpact();
        }
        else if (epsilon.getPosition().y >= Application.gameFrame.getHeight() - Constants.EPSILON_DIMENSION.height / 2d){
            epsilon.setPosition(epsilon.getPosition().getX() ,Application.gameFrame.getHeight() - Constants.EPSILON_DIMENSION.height / 2d - 1);
            direction.setY(-1);
            EpsilonMovement.StopTimers();
            new Impact(new Vector(epsilon.getPosition().x ,Application.gameFrame.getHeight() - Constants.EPSILON_DIMENSION.height / 2d)).MakeImpact();
        }
    }

    private void BulletBorderCollision(BulletModel bullet){
        if (bullet.getPosition().x <= Constants.EPSILON_DIMENSION.width / 2d){
            ///////todo
            new FrameAnimation(Application.gameFrame ,0 ,0 ,0 ,Constants.FRAME_BULLET_RESIZE,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            Controller.removeOIG(bullet.getId());
            ///////todo
        }
        else if (bullet.getPosition().x >= Application.gameFrame.getWidth() - Constants.EPSILON_DIMENSION.width / 2d){
            ///////todo
            new FrameAnimation(Application.gameFrame ,0 ,0 ,Constants.FRAME_BULLET_RESIZE ,0,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            Controller.removeOIG(bullet.getId());
            ///////todo
        }
        else if (bullet.getPosition().y <= Constants.EPSILON_DIMENSION.height / 2d){
            ///////todo
            new FrameAnimation(Application.gameFrame ,Constants.FRAME_BULLET_RESIZE ,0 ,0 ,0,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            Controller.removeOIG(bullet.getId());
            ///////todo
        }
        else if (bullet.getPosition().y >= Application.gameFrame.getHeight() - Constants.EPSILON_DIMENSION.height / 2d){
            ///////todo
            new FrameAnimation(Application.gameFrame ,0 ,Constants.FRAME_BULLET_RESIZE ,0 ,0,Constants.FRAME_BULLET_RESIZE_TIME).StartAnimation();
            Controller.removeOIG(bullet.getId());
            ///////todo
        }
    }

}
