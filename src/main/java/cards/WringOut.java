package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.powers.SwimPower;

public class WringOut extends AbstractAquaticCard {
    public static final String ID = "WringOut";
    public static final String IMG = "cards/wringout.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int DAMAGE = 7;
    private static final int DAMAGE_BONUS = 3;

    public WringOut() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(SwimPower.POWER_ID)) {
            AbstractPower power = p.getPower(SwimPower.POWER_ID);
            power.amount = 0;
        }

        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        addToTop(new RemoveSpecificPowerAction(p, p, SwimPower.POWER_ID));

    }

    public AbstractCard makeCopy() {
        return new WringOut();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
        }
    }

}
