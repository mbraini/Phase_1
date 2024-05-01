package controller.animations;

import controller.Application;
import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpsilonInControlAnimation extends Animation{
    EpsilonModel epsilon;
    Timer timer;
    double deltaX;
    double deltaY;
    double epsilonW;
    double epsilonH;
    public EpsilonInControlAnimation(EpsilonModel epsilon){
        this.epsilon = epsilon;
        deltaX = (Application.gameFrame.getWidth() - Constants.EPSILON_DIMENSION.width);
        deltaY = (Application.gameFrame.getHeight() - Constants.EPSILON_DIMENSION.height);
        ///////////////////////
        deltaX = deltaX * Constants.WIN_ANIMATION_REFRESHRATE / Constants.WIN_ANIMATION_TIME;
        deltaY = deltaY * Constants.WIN_ANIMATION_REFRESHRATE / Constants.WIN_ANIMATION_TIME;
        Application.gameFrame.setUpDownV(0 ,0);
        Application.gameFrame.setLeftRightV(0 ,0);
        //////////////////////
        timer = new Timer(Constants.WIN_ANIMATION_REFRESHRATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilonW += deltaX;
                epsilonH += deltaY;
                Constants.EPSILON_DIMENSION = new Dimension((int) epsilonW ,(int) epsilonH);
                if (Constants.EPSILON_DIMENSION.width >= Application.gameFrame.getWidth()){
                    timer.stop();
                    new FrameVanishAnimation(Application.gameFrame).StartAnimation();
                }
            }
        });
    }

    @Override
    public void StartAnimation() {
        centerEpsilon();
        epsilonW = Constants.EPSILON_DIMENSION.width;
        epsilonH = Constants.EPSILON_DIMENSION.height;
        timer.start();
    }

    public void centerEpsilon(){
        Vector now = epsilon.getPosition();
        Vector center = new Vector(GameFrame.windowKill.getWidth() / 2d ,GameFrame.windowKill.getHeight() / 2d);
        Vector delta = Utils.VectorAdd(Utils.ScalarInVector(-1 ,now) ,center);
        epsilon.setPosition(center);
        epsilon.UpdateVertices(delta.x ,delta.y ,0);
    }
}
