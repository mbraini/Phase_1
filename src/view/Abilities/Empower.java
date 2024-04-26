package view.Abilities;

import controller.actionlisteners.EpsilonAiming;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empower extends RegularAbility{
    Timer timer;
    @Override
    public void performAbility() {
        EpsilonAiming.extraAim = 2;
        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EpsilonAiming.extraAim = 0;
                timer.stop();
            }
        });
        timer.start();
    }
}
