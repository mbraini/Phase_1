package view.Abilities;

import controller.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aceso extends SpecialAbility{
    public static Timer heal;
    public static boolean available = true;
    @Override
    public void performAbility() {
        heal = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameState.hp ++;
                if (GameState.hp == 100){
                    heal.stop();
                }
            }
        });
        heal.start();
    }
}
