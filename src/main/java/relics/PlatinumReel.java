package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import aquaticmod.relics.AbstractAquaticRelic;
import aquaticmod.relics.MagicRod;
import aquaticmod.cards.Hook;

public class PlatinumReel extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:PlatinumReel";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final String IMG = "relics/platinumreel.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public PlatinumReel() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(MagicRod.ID);
    }

    @Override
    public void atBattleStartPreDraw() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new MakeTempCardInHandAction((AbstractCard)new Hook(true), 2, false));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PlatinumReel();
    }

    @Override
    public void obtain() {
        // Replace the starter reli
        if (AbstractDungeon.player.hasRelic(MagicRod.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(MagicRod.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
}

