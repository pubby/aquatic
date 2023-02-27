package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.powers.ThornsPower;
import aquaticmod.relics.AbstractAquaticRelic;

public class CenozoicTooth extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:CenozoicTooth";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final String IMG = "relics/cenozoictooth.png";
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int THORNS = 2;

    public CenozoicTooth() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER) {
            flash();
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, THORNS), THORNS));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CenozoicTooth();
    }
}

