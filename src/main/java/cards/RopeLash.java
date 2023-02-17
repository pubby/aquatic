package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.fields.DeepField;
import aquaticmod.actions.RopeLashAction;

public class RopeLash extends AbstractAquaticCard {
    public static final String ID = "RopeLash";
    public static final String IMG = "cards/ropelash.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 0;

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int DAMAGE_BONUS = 3;

    public RopeLash() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new RopeLashAction());
    }

    public AbstractCard makeCopy() {
        return new RopeLash();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
        }
    }

}
