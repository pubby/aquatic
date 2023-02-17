package aquaticmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.actions.ToggleBacteriaAction;
import aquaticmod.AquaticMod;

public class BacteriaPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:BacteriaPower";
    public static final String IMG = "powers/bacteria";

    public boolean enable = true;

    public BacteriaPower(int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        if (amount <= 1) {
            description = DESCRIPTIONS[0];
        }
        else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (enable && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new ToggleBacteriaAction(owner, false));
            addToBot(new ExhaustAction(amount, false, true, true));
            addToBot(new ToggleBacteriaAction(owner, true));
        }
    }
}

