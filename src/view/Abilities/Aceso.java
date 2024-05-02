package view.Abilities;

import controller.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aceso extends SpecialAbility{
    public static Timer heal;
    public static int isAvailable = 0;
    static double time = -300;
    @Override
    public void performAbility() {
        if ( GameState.time - time >= 300 && GameState.xp >= 100 && isAvailable > 0) {
            isAvailable --;
            heal = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (GameState.isPause)
                        return;
                    if (GameState.isOver){
                        heal.stop();
                    }
                    GameState.hp++;
                    if (GameState.hp >= 100){
                        GameState.hp = 100;
                    }
                }
            });
            heal.start();
            GameState.xp -= 100;
            time = GameState.time;
        }
    }
}
