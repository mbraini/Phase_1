package view.Abilities;

import controller.Configs;

public class Proteus extends SpecialAbility{
    public static boolean available = true;
    @Override
    public void performAbility() {
        Configs.VERTICES++;
    }
}
