package controller.animations;

import controller.Application;
import controller.Constants;
import controller.Controller;
import controller.Spawn;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;
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
                    epsilonTaker.stop();
                }
            }
        });
        epsilonTaker.start();
        frameAnimation = new FrameAnimation(Application.gameFrame ,-250 ,-250 ,-250 ,-250 ,1000);
        frameAnimation.StartAnimation();
    }
    public void centerEpsilon(){
        Vector now = epsilon.getPosition();
        Vector center = new Vector(GameFrame.windowKill.getWidth() / 2d ,GameFrame.windowKill.getHeight() / 2d);
        Vector delta = Utils.VectorAdd(Utils.ScalarInVector(-1 ,now) ,center);
        epsilon.setPosition(center);
        epsilon.UpdateVertices(delta.x ,delta.y ,0);
    }
}