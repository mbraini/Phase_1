package view.Abilities;

import controller.Config.Configs;
import controller.Controller;
import controller.GameState;

public class Proteus extends SpecialAbility{
    public static int isAvailable = 0;
    static double time = -300;
    @Override
    public void performAbility() {
        if (GameState.time - time >= 20 && GameState.xp >= 100 && isAvailable > 0){
            isAvailable --;
            Configs.VERTICES += 1;
            Controller.getEpsilon().addVertex();
            GameState.xp -= 100;
            time = GameState.time;
        }
    }
}
