package view.Abilities;

import controller.Config.Configs;
import controller.GameState;

public class Ares extends SpecialAbility{
    public static boolean isAvailable;
    static double time = -300;
    @Override
    public void performAbility() {
        if (GameState.time - time >= 300 && GameState.xp >= 100 && isAvailable){
            Configs.EXTRA_DAMAGE = 2;
            GameState.xp -= 100;
            time = GameState.time;
        }
    }
}
