package view.Abilities;

import controller.Configs;
import controller.GameState;

public class Ares extends SpecialAbility{
    static double time = -300;
    @Override
    public void performAbility() {
        if (GameState.time - time >= 300 && GameState.xp >= 100){
            Configs.EXTRA_DAMAGE = 2;
            GameState.xp -= 100;
            time = GameState.time;
        }
    }
}
