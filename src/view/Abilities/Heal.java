package view.Abilities;

import controller.GameState;

public class Heal extends RegularAbility{
    @Override
    public void performAbility() {
        GameState.hp = GameState.hp + 10;
        if (GameState.hp >= 100){
            GameState.hp = 100;
        }
    }
}
