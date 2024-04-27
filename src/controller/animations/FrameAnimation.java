package controller.animations;

import controller.Constants;
import controller.GameState;
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
                gameFrame.setUpDownV(gameFrame.getUpDownV().x + upAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE ,gameFrame.getUpDownV().y + downAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE);
                gameFrame.setLeftRightV(gameFrame.getLeftRightV().x + rightAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE ,gameFrame.getLeftRightV().y + leftAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE);

                gameFrame.setUpDownP((2 * gameFrame.getUpDownV().x - upAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2 ,(2 * gameFrame.getUpDownV().y - downAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);
                gameFrame.setLeftRightP((2 * gameFrame.getLeftRightV().x - rightAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2 ,(2 * gameFrame.getLeftRightV().y - leftAcceleration * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);

                gameFrame.UpAddSize((int) gameFrame.getUpDownP().x);
                gameFrame.DownAddSize((int) gameFrame.getUpDownP().y);
                gameFrame.RightAddSize((int) gameFrame.getLeftRightP().x);
                gameFrame.LeftAddSize((int) gameFrame.getLeftRightP().y);
                timeCheck +=  Constants.FRAME_ANIMATION_REFRESH_RATE;
                if (timeCheck >= time){
                    gameFrame.setUpDownV(0 ,0);
                    gameFrame.setLeftRightV(0 ,0);
                    gameFrame.setUpDownP(0 ,0);
                    gameFrame.setLeftRightP(0 ,0);
                    if (GameState.time <= 1000){
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
        timer.start();
    }
}
