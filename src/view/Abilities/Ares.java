package view.Abilities;

import controller.Configs;

public class Ares extends SpecialAbility{
    public static boolean available = true;
    @Override
    public void performAbility() {
        Configs.EXTRA_DAMAGE = 2;
    }
}
