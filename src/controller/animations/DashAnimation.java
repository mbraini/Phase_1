package controller.animations;

import controller.Constants;
import controller.GameState;
import controller.actionlisteners.EpsilonMovement;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.EpsilonGravity;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DashAnimation extends Animation implements ActionListener{
    OIGModel oigModel;
    Vector direction;
    int time;
    double distance;
    double theta;
    Timer timer;
    static HashMap<OIGModel ,Timer> dashes = new HashMap<>();

    public DashAnimation(OIGModel oigModel, Vector direction, int time, double distance ,double theta) {
        this.oigModel = oigModel;
        this.direction = Utils.VectorWithSize(direction ,1);
        this.time = time;
        this.distance = distance;
        this.theta = theta;
    }

    @Override
    public void StartAnimation() {
        if (dashes.containsKey(oigModel)){
            StopTimer(dashes.get(oigModel));
            dashes.remove(oigModel);
        }
        /////////////////////////////Calculation
        double a = (2 * distance) / Math.pow(time ,2);
        oigModel.setVelocity(Utils.ScalarInVector(time * a ,direction));
        oigModel.setAcceleration(Utils.ScalarInVector(-a ,direction));

        double alpha = (2 * theta) / Math.pow(time ,2);
        oigModel.setOmega(time * alpha);
        oigModel.setAlpha(-alpha);
        //////////////////////////////
        if (oigModel instanceof EpsilonGravity) {
            ((EpsilonGravity) oigModel).setVisibility(false);
        }
        timer = new Timer(time, this);

        dashes.put(oigModel ,timer);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (GameState.isPause)
            return;
        StopTimer(timer);
    }

    void StopTimer(Timer timer){
        timer.removeActionListener(this);
        timer.stop();
        if (oigModel instanceof EpsilonGravity)
            ((EpsilonGravity) oigModel).setVisibility(true);
        oigModel.setAcceleration(0 ,0);
        oigModel.setVelocity(0 ,0);

        oigModel.setAlpha(0);
        oigModel.setOmega(0);
    }
}
