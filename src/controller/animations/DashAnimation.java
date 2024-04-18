package controller.animations;

import controller.Constants;
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
    Timer timer;
    Vector direction;
    static HashMap<OIGModel ,Timer> dashes = new HashMap<>();

    public DashAnimation(OIGModel oigModel , Vector direction) {
        this.oigModel = oigModel;
        this.direction = Utils.VectorWithSize(direction ,Constants.DASH_ACCELERATION);
    }

    @Override
    public void StartAnimation() {
        if (dashes.containsKey(oigModel)){
            (dashes.get(oigModel)).stop();
            oigModel.setAcceleration(0 ,0);
            dashes.remove(oigModel);
        }
        oigModel.setVelocity(direction);
        oigModel.setAcceleration(direction);
        double delta = Constants.DASH_TIMER_TICK * Constants.DASH_ACCELERATION / (Constants.DASH_TIME);
        direction = Utils.VectorWithSize(direction ,-delta);
        timer = new Timer(Constants.DASH_TIMER_TICK, this);
        dashes.put(oigModel ,timer);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Utils.VectorSize(oigModel.getVelocity()) == 0){
            oigModel.setVelocity(direction);
        }
        if (Utils.VectorSize(oigModel.getAcceleration()) == 0){
            oigModel.setAcceleration(direction);
        }
        oigModel.setAcceleration(Utils.VectorAdd(oigModel.getAcceleration() ,direction));
        oigModel.setVelocity(Utils.VectorWithSize(oigModel.getVelocity() ,Constants.ENEMY_LINEAR_SPEED));
        if (Utils.VectorSize(oigModel.getAcceleration()) >= Constants.DASH_ACCELERATION / 5 && Utils.DotProduct(oigModel.getAcceleration() ,direction) > 0){
            if (oigModel instanceof EpsilonGravity)
                ((EpsilonGravity) oigModel).setVisibility(true);
            oigModel.setAcceleration(0 ,0);
            setVelocities(oigModel);
            timer.stop();
            timer.removeActionListener(this);
        }
    }

    private void setVelocities(OIGModel oigModel) {
        if (oigModel instanceof EpsilonModel){
            oigModel.setVelocity(0 ,0);
        }
    }
}
