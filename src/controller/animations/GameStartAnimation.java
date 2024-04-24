package controller.animations;

import controller.Application;
import controller.Constants;
import controller.Spawn;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStartAnimation extends Animation{
    static Timer gameStartAnimation;

    @Override
    public void StartAnimation(){
        gameStartAnimation = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ////////////////////////////todo
                for (int i = 0; i < OIGModel.OIGs.size() ; i++){
                    if (OIGModel.OIGs.get(i) instanceof EpsilonModel){
                        ((EpsilonModel) OIGModel.OIGs.get(i)).setPosition(((EpsilonModel) OIGModel.OIGs.get(i)).getPosition().x - 2 ,((EpsilonModel) OIGModel.OIGs.get(i)).getPosition().y - 2);
                    }
                }
                ////////////////////////////todo
                Application.gameFrame.RightAddSize(-2);
                Application.gameFrame.DownAddSize(-2);
                Application.gameFrame.LeftAddSize(-2);
                Application.gameFrame.UpAddSize(-2);
                if (Application.gameFrame.getWidth() - Constants.barD.width == 200){
                    OIGModel.OIGs.get(0).setVelocity(0 ,0);
                    gameStartAnimation.removeActionListener(this);
                }
            }
        });
        gameStartAnimation.start();
    }
}
