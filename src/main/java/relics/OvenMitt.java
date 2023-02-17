package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import aquaticmod.relics.AbstractAquaticRelic;
import aquaticmod.powers.SwimPower;

public class OvenMitt extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:OvenMitt";
    private static final RelicTier TIER = RelicTier.SHOP;
    private static final String IMG = "relics/ovenmitt.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public OvenMitt() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new OvenMitt();
    }

}

