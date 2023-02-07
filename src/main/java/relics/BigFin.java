package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import aquaticmod.relics.AbstractAquaticRelic;

public class BigFin extends AbstractAquaticRelic {
    public static final String ID = "BigFin";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final String IMG = "relics/bigfin.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    private boolean gainEnergy = false;

    public BigFin() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        if (AbstractDungeon.player != null) {
            return setDescription(AbstractDungeon.player.chosenClass);
        }
        return setDescription(null);
    }

    private String setDescription(AbstractPlayer.PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    @Override
    public void updateDescription(AbstractPlayer.PlayerClass c) {
        this.description = this.setDescription(c);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void atPreBattle() {
        flash();
        gainEnergy = true;

        if (!pulse) {
            beginPulse();
            pulse = true;
        }
    }

    @Override
    public void atTurnStart() {
        if (gainEnergy) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            gainEnergy = false;
            pulse = false;
        }
    }

    @Override
    public void onVictory() {
        pulse = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BigFin();
    }
}

