package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import aquaticmod.AquaticMod;

public class IcebergPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:IcebergPower";
    public static final String IMG = "powers/iceberg";

    public IcebergPower(AbstractCreature owner) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = -1;
        //this.loadRegion("retain");
        //this.img = new Texture(AquaticMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.isEthereal || (c.type != AbstractCard.CardType.ATTACK)) continue;
                c.retain = true;
            }
        }
    }
}

