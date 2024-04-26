package view.Abilities;

import controller.Controller;
import controller.RegularAbilities;

public abstract class RegularAbility extends Ability{

    public static void sendRequest(RegularAbilities regularAbility){
        switch (regularAbility){
            case heal:
                Controller.regularAbility = RegularAbilities.heal;
                break;
            case banish:
                Controller.regularAbility = RegularAbilities.banish;
                break;
            case empower:
                Controller.regularAbility = RegularAbilities.empower;
                break;
        }
    }

}
