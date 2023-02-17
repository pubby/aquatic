package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import aquaticmod.AquaticMod;
import aquaticmod.powers.SwimPower;

public class EggClutchPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:EggClutchPower";
    public static final String IMG = "powers/eggclutch";
    private static int idOffset;

    private int boost;

    public EggClutchPower(AbstractCreature owner, int boost, int turns) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.ID = POWER_ID + idOffset;
        ++idOffset;
        this.owner = owner;
        this.boost = boost;
        this.amount = turns;
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if(amount <= 1) {
            description = DESCRIPTIONS[0] + boost + DESCRIPTIONS[1];
        }
        else {
            description = DESCRIPTIONS[2] + amount + DESCRIPTIONS[3] + boost + DESCRIPTIONS[4];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
            if (amount == 1) {
                addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, boost), boost));
                addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, boost), boost));
            }
        }
    }
}

