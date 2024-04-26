package view.Abilities;

import controller.GameState;

public class Heal extends RegularAbility{
    @Override
    public void performAbility() {
        GameState.hp = GameState.hp + 10;
    }
}
