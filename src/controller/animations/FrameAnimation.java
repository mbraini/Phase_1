package controller.animations;

import controller.Constants;
import controller.GameState;
import controller.helper.Vector;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAnimation extends Animation{
    GameFrame gameFrame;
    double time;
    double upAcceleration;
    double downAcceleration;
    double rightAcceleration;
    double leftAcceleration;
    Timer timer;
    double timeCheck;

    public FrameAnimation(GameFrame gameFrame , double up ,double down ,double right ,double left ,double time){
        this.gameFrame = gameFrame;
        this.time = time;

        upAcceleration = -2 * up / (Math.pow(this.time ,2));
        downAcceleration = -2 * down / (Math.pow(this.time ,2));
        rightAcceleration = -2 * right / (Math.pow(this.time ,2));
        leftAcceleration = -2 * left / (Math.pow(this.time ,2));
        this.gameFrame.setUpDownV(-this.time * upAcceleration ,-this.time * downAcceleration);
        this.gameFrame.setLeftRightV(-this.time * rightAcceleration ,-this.time * leftAcceleration);
        timeCheck = 0;
        timer = new Timer(Constants.FRAME_ANIMATION_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.move();
                gameFrame.Update();
                timeCheck +=  Constants.FRAME_ANIMATION_REFRESH_RATE;
                if (timeCheck >= time){
                    gameFrame.setUpDownA(0 ,0);
                    gameFrame.setLeftRightA(0 ,0);
                    gameFrame.setUpDownV(0 ,0);
                    gameFrame.setLeftRightV(0 ,0);
                    if (GameState.time <= 1){
                        GameFrame.windowKill.startGame();
                    }
                    timer.stop();
                    timer.removeActionListener(this);
                }
            }
        });
    }


    @Override
    public void StartAnimation() {
        gameFrame.setUpDownA(new Vector(upAcceleration ,downAcceleration));
        gameFrame.setLeftRightA(new Vector(rightAcceleration ,leftAcceleration));
        timer.start();
    }
}
