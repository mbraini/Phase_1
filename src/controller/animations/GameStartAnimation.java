package controller.animations;

import controller.Application;
import controller.Controller;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStartAnimation extends Animation{
    private Timer epsilonTaker;
    private FrameAnimation frameAnimation;
    EpsilonModel epsilon;
    public GameStartAnimation(){
        epsilon = Controller.getEpsilon();
    }

    @Override
    public void StartAnimation(){
        epsilonTaker = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerEpsilon();
                if (frameAnimation.isDone()){
                    GameFrame.windowKill.startGame();
                    Controller.SpawnEnemyReq();
                    Application.gameFrame.setLeftRightV(0 ,0);
                    Application.gameFrame.setUpDownV(0 ,0);
                    epsilonTaker.stop();
                }
            }
        });
        frameAnimation = new FrameAnimation(Application.gameFrame ,-50 ,-50 ,-50 ,-50 ,1000);
        frameAnimation.StartAnimation();
        epsilonTaker.start();
    }
    public void centerEpsilon(){
        Vector now = epsilon.getPosition();
        Vector center = new Vector(GameFrame.windowKill.getWidth() / 2d ,GameFrame.windowKill.getHeight() / 2d);
        Vector delta = Utils.VectorAdd(Utils.ScalarInVector(-1 ,now) ,center);
        epsilon.setPosition(center);
        epsilon.UpdateVertices(delta.x ,delta.y ,0);
    }
}