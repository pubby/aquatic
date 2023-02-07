package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;

import aquaticmod.AquaticMod;

public class SwimPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:SwimPower";
    public static final String IMG = "powers/mine.png";

    public SwimPower(int amount) {
        super(POWER_ID, AquaticMod.getResourcePath("powers/mine"));
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.updateDescription();
        //this.img = new Texture(AquaticMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));

            if (amount <= 1) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));
            }
        }
    }
}

