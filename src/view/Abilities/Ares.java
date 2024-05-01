package view.Abilities;

import controller.Configs;
import controller.GameState;

public class Ares extends SpecialAbility{
    static double time = -300;
    public static boolean available = true;
    @Override
    public void performAbility() {
        if (GameState.time - time >= 300){
            Configs.EXTRA_DAMAGE = 2;
        }
    }
}
