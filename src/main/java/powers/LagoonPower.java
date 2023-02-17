package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.AquaticMod;

public class LagoonPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:LagoonPower";
    public static final String IMG = "powers/lagoon";

    public LagoonPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        //this.img = new Texture(AquaticMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            addToBot(new GainBlockAction(owner, owner, amount));
        }
    }
}

