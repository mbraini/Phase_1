package controller.animations;

import controller.Application;
import controller.Constants;
import controller.Spawn;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStartAnimation extends Animation{
    static Timer gameStartAnimation;

    @Override
    public void StartAnimation(){
        new FrameAnimation(Application.gameFrame ,-250 ,-250 ,-250 ,-250 ,1000).StartAnimation();
    }
}
