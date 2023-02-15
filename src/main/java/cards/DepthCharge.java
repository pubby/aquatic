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

public class DepthCharge extends AbstractAquaticCard {
    public static final String ID = "DepthCharge";
    public static final String IMG = "cards/depthcharge.png";

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;

    private static final int POOL = 0;

    private static final int COST = 0;
    private static final int DAMAGE = 10;
    private static final int DAMAGE_BONUS = 3;

    public DepthCharge() {
        this(false);
    }

    public DepthCharge(boolean doUpgrade) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, COLOR);
        this.baseDamage = DAMAGE;
        this.exhaust = true;
        DeepField.deep.set(this, true);
        if (doUpgrade) upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractCard makeCopy() {
        return new DepthCharge();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
        }
    }

}
