package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HarpoonShot extends AbstractAquaticCard {
    public static final String ID = "HarpoonShot";
    public static final String IMG = "cards/harpoonshot.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int DAMAGE_BONUS = 3;
    private static final int MAGIC = 1;
    //private static final int MAGIC_BONUS = 1;

    public HarpoonShot() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new DrawPileToHandAction(magicNumber, AbstractCard.CardType.ATTACK));
    }

    public AbstractCard makeCopy() {
        return new HarpoonShot();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
            //upgradeMagicNumber(MAGIC_BONUS);
            //rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //initializeDescription();
        }
    }

}
