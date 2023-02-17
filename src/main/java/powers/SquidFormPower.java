package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;;
import aquaticmod.AquaticMod;

public class SquidFormPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:SquidFormPower";
    public static final String IMG = "powers/squid";

    private int turnLimit = 0;

    public SquidFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = this.turnLimit = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        turnLimit += stackAmount;
        amount += stackAmount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            amount = turnLimit;
            updateDescription();
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            card.modifyCostForCombat(-9);
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type != AbstractCard.CardType.ATTACK) return;

        if (amount > 0) {
            flash();
            --amount;
        }

        if (amount == 0 && !owner.hasPower(EntanglePower.POWER_ID)) {
            addToBot(new ApplyPowerAction(owner, owner, new EntanglePower((AbstractCreature)owner)));
        }

        updateDescription();
    }
}

