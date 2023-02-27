package aquaticmod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import aquaticmod.relics.AbstractAquaticRelic;

public class Caviar extends AbstractAquaticRelic {
    public static final String ID = "AquaticMod:Caviar";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final String IMG = "relics/caviar.png";
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int THORNS = 2;

    public Caviar() {
        super(ID, IMG, TIER, SOUND);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        for (RewardItem reward : AbstractDungeon.combatRewardScreen.rewards) {
            if (reward.cards == null) continue;
            for (AbstractCard c : reward.cards) {
                onPreviewObtainCard(c);
            }
        }
    }

    @Override
    public void onPreviewObtainCard(AbstractCard c) {
        onObtainCard(c);
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.rarity == AbstractCard.CardRarity.COMMON && c.canUpgrade() && !c.upgraded) {
            c.upgrade();
        }
    }

    @Override
    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Caviar();
    }

}

