package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.DethawHandAction;

public class FieryFish extends AbstractAquaticCard {
    public static final String ID = "FieryFish";
    public static final String IMG = "cards/fieryfish.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int DAMAGE = 7;
    //private static final int DAMAGE_BONUS = 1;
    private static final int MAGIC = 2;

    public FieryFish() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            addToTop(new DamageAllEnemiesAction((AbstractCreature)p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        }
        addToTop(new DethawHandAction(upgraded ? -1 : 1));
    }

    public AbstractCard makeCopy() {
        return new FieryFish();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeDamage(DAMAGE_BONUS);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
