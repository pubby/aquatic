package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.cards.status.Slimed;
import aquaticmod.relics.AbstractAquaticRelic;

public class Amoeba extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:Amoeba";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final String IMG = "relics/amoeba.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public Amoeba() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void atTurnStart() {
        addToBot(new MakeTempCardInHandAction((AbstractCard)new Slimed(), 1));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Amoeba();
    }
}

