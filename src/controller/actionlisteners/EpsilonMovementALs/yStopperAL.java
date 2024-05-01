package controller.actionlisteners.EpsilonMovementALs;

import controller.actionlisteners.EpsilonMovement;
import model.objectsModel.EpsilonModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class yStopperAL implements ActionListener {
    EpsilonModel epsilon;
    public yStopperAL(EpsilonModel epsilon){
        this.epsilon = epsilon;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        epsilon.setAcceleration(epsilon.getAcceleration().x ,0);
        epsilon.setVelocity(epsilon.getVelocity().x ,0);
        EpsilonMovement.yStopper.stop();
    }
}
