package view.Abilities;

import controller.Controller;
import controller.RegularAbilitiesEnum;

public abstract class RegularAbility extends Ability{

    public static void sendRequest(RegularAbilitiesEnum regularAbility){
        switch (regularAbility){
            case heal:
                Controller.regularAbilityRequest(RegularAbilitiesEnum.heal);
                break;
            case banish:
                Controller.regularAbilityRequest(RegularAbilitiesEnum.banish);
                break;
            case empower:
                Controller.regularAbilityRequest(RegularAbilitiesEnum.empower);
                break;
        }
    }

}
