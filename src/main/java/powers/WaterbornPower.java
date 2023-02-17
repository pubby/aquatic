package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.AquaticMod;
import aquaticmod.powers.SwimPower;

public class WaterbornPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:WaterbornPower";
    public static final String IMG = "powers/waterborn";

    public WaterbornPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new ApplyPowerAction(owner, owner, new SwimPower(amount), amount));
    }
}

