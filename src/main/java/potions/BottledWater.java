package aquaticmod.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import basemod.abstracts.CustomPotion;
import aquaticmod.powers.SwimPower;

public class BottledWater extends CustomPotion {
    public static final String POTION_ID = "AquaticMod:BottledWater";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("AquaticMod:BottledWater");

    public BottledWater() {
        super(BottledWater.potionStrings.NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, AbstractPotion.PotionSize.BOTTLE, AbstractPotion.PotionColor.WHITE);
        this.isThrown = false;
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(potionStrings.DESCRIPTIONS[2]), GameDictionary.keywords.get(potionStrings.DESCRIPTIONS[2])));
    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new SwimPower(potency), potency));
        }
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 4;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BottledWater();
    }
}


