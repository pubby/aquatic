package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.CardStrings;

public class Wrap extends AbstractAquaticCard {
    public static final String ID = "Wrap";
    public static final String IMG = "cards/wrap.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 0;
    private static final int MAGIC = 2;
    private static final int MAGIC_BONUS = 1;

    public Wrap() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        baseDamage = calcAmount();
        calculateCardDamage(m);
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();

        for (int i = 0; i < magicNumber; ++i) {
            addToBot(new DamageAction((AbstractCreature)m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        }
    }

    public int calcAmount() {
        if(AbstractDungeon.player.hasPower(ThornsPower.POWER_ID)) {
            AbstractPower power = AbstractDungeon.player.getPower(ThornsPower.POWER_ID);
            return power.amount;
        }
        return 0;
    }

    @Override
    public void applyPowers() {
        baseDamage = calcAmount();
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }


    public AbstractCard makeCopy() {
        return new Wrap();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }

}
