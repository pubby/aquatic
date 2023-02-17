package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import aquaticmod.relics.AbstractAquaticRelic;
import aquaticmod.powers.SwimPower;

public class Lifebuoy extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:Lifebuoy";
    private static final RelicTier TIER = RelicTier.COMMON;
    private static final String IMG = "relics/lifebuoy.png";
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int BLOCK_AMT = 3;

    public Lifebuoy() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        counter = 3;
        grayscale = false;
    }

    @Override
    public void atTurnStart() {
        if (counter > 0) {
            --counter;

            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainBlockAction((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMT));

            if (counter == 0) {
                grayscale = true;
                counter = -1;
            }
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
        grayscale = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Lifebuoy();
    }
}

