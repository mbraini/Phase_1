package view.Abilities;

import controller.Controller;
import controller.RegularAbilitiesEnum;
import controller.SpecialAbilitiesEnum;

public abstract class SpecialAbility extends Ability{

    public static void sendRequest(SpecialAbilitiesEnum specialAbility){
        switch (specialAbility){
            case ares:
                Controller.specialAbilityRequest(SpecialAbilitiesEnum.ares);
                break;
            case aceso:
                Controller.specialAbilityRequest(SpecialAbilitiesEnum.aceso);
                break;
            case proteus:
                Controller.specialAbilityRequest(SpecialAbilitiesEnum.proteus);
                break;
        }
    }

}
