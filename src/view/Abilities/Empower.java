package view.Abilities;

import controller.actionlisteners.EpsilonAiming;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empower extends RegularAbility{
    static Timer timer = new Timer(10000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EpsilonAiming.extraAim = 0;
            timer.stop();
        }
    });
    @Override
    public void performAbility() {
        if (EpsilonAiming.extraAim == 2){
            timer.stop();
        }
        EpsilonAiming.extraAim = 2;
        timer.start();
    }
}
