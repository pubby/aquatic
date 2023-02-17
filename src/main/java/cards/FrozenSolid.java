package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import aquaticmod.fields.FrozenField;

public class FrozenSolid extends AbstractAquaticCard {
    public static final String ID = "FrozenSolid";
    public static final String IMG = "cards/frozensolid.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int MAGIC_BONUS = 1;

    public FrozenSolid() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        //FrozenField.startFrozen.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        /*
        if (p.hasPower(PlatedArmorPower.POWER_ID)) {
            AbstractPower power = p.getPower(PlatedArmorPower.POWER_ID);
            p.addBlock(power.amount);
            addToBot(new RemoveSpecificPowerAction(p, p, PlatedArmorPower.POWER_ID));
        }
        */

        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));
        FrozenField.toFreezeCard(this);
    }

    public AbstractCard makeCopy() {
        return new FrozenSolid();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
