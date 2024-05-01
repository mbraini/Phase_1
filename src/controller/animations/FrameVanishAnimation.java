package controller.animations;

import controller.Constants;
import controller.Controller;
import model.logic.Collision;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameVanishAnimation extends Animation{
    GameFrame gameFrame;
    Timer timer;
    public FrameVanishAnimation(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setSize(gameFrame.getSize().width - 3 ,gameFrame.getSize().height - 3);
                if (gameFrame.getSize().width <= 140 && gameFrame.getSize().height <= 40){
                    Controller.EndTheGame();
                    timer.stop();
                }
            }
        });
    }
    @Override
    public void StartAnimation() {
        timer.start();
    }
}
