package aquaticmod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.cards.DepthCharge;
import aquaticmod.AquaticMod;

public class BombsAwayPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:BombsAwayPower";
    public static final String IMG = "powers/bombsaway";

    public BombsAwayPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
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
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            addToBot(new MakeTempCardInDrawPileAction((AbstractCard)new DepthCharge(true), amount, false, true, true));
        }
    }
}

