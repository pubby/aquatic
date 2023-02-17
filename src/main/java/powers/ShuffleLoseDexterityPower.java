package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import aquaticmod.AquaticMod;
import aquaticmod.powers.SwimPower;

public class ShuffleLoseDexterityPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:ShuffleLoseDexterityPower";
    public static final String IMG = "powers/momentary";

    public ShuffleLoseDexterityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onShuffle() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -amount), -amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}

