package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import aquaticmod.AquaticMod;

public class AvidityPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:AvidityPower";
    public static final String IMG = "powers/avidity";

    public AvidityPower(AbstractCreature owner) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

}

