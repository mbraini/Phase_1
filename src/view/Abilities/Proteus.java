package view.Abilities;

import controller.Config.Configs;
import controller.Controller;
import controller.GameState;

public class Proteus extends SpecialAbility{
    static double time = -300;
    @Override
    public void performAbility() {
        if (GameState.time - time >= 300 && GameState.xp >= 100){
            System.out.println("HI");
            Configs.VERTICES += 1;
            Controller.getEpsilon().addVertex();
            GameState.xp -= 100;
            time = GameState.time;
        }
    }
}
