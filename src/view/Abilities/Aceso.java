package view.Abilities;

import controller.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aceso extends SpecialAbility{
    public static Timer heal;
    static double time = -300;
    @Override
    public void performAbility() {
        if ( GameState.time - time >= 300 && GameState.xp >= 100) {
            heal = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameState.hp++;
                }
            });
            heal.start();
            GameState.xp -= 100;
            time = GameState.time;
        }
    }
}
