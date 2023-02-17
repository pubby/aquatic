package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.powers.MinePower;

public class Demolish extends AbstractAquaticCard {
    public static final String ID = "Demolish";
    public static final String IMG = "cards/demolish.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int DAMAGE = 16;
    private static final int DAMAGE_BONUS = 6;

    public Demolish() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(MinePower.POWER_ID)) {
            AbstractPower power = m.getPower(MinePower.POWER_ID);
            addToBot(new ApplyPowerAction(m, p, new MinePower(m, p, power.amount), power.amount, true, AbstractGameAction.AttackEffect.NONE));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new Demolish();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
        }
    }
}
