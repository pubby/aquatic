package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import aquaticmod.relics.AbstractAquaticRelic;
import aquaticmod.cards.Hook;

public class MagicRod extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:MagicRod";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final String IMG = "relics/magicrod.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public MagicRod() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void atBattleStartPreDraw() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new MakeTempCardInHandAction((AbstractCard)new Hook(), 1, false));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MagicRod();
    }
}

