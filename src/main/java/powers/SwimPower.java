package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.powers.SwiftSwimPower;
import aquaticmod.powers.AvidityPower;
import aquaticmod.cards.Surf;
import aquaticmod.AquaticMod;
import aquaticmod.fields.FrozenField;;

public class SwimPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:SwimPower";
    public static final String IMG = "powers/swim";

    public SwimPower(int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = AbstractDungeon.player;

        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void stackPower(int stackAmount) {
        if (owner.hasPower(AvidityPower.POWER_ID)) {
            trigger(stackAmount);
        }
        else {
            super.stackPower(stackAmount);
        }
    }

    public void trigger(int numTriggers) {
        int drawMultiplier = 1;

        if (owner.hasPower(SwiftSwimPower.POWER_ID)) {
            AbstractPower power = owner.getPower(SwiftSwimPower.POWER_ID);
            drawMultiplier += power.amount;
            if(power.amount > 0) {
                power.flash();
            }
        }

        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, drawMultiplier * numTriggers));
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.ATTACK && amount > 0) {
            int numTriggers = (usedCard instanceof Surf && !FrozenField.get(usedCard).frozen) ? amount : 1;

            trigger(numTriggers);
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, numTriggers));
        }
    }
}

