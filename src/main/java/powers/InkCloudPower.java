package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import aquaticmod.AquaticMod;

public class InkCloudPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:InkCloudPower";
    public static final String IMG = "powers/inkcloud";

    public InkCloudPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m == null || m.isDead || m.isDying) continue;

                if (m.getIntentBaseDmg() >= 0) {
                    addToBot(new GainBlockAction(owner, owner, amount));
                    return;
                }
            }

            addToBot(new ApplyPowerAction(owner, owner, new EntanglePower((AbstractCreature)owner)));
        }
    }
}

