package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.BrineBlastAction;;

public class BrineBlast extends AbstractAquaticCard {
    public static final String ID = "BrineBlast";
    public static final String IMG = "cards/brineblast.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int DAMAGE = 14;
    private static final int DAMAGE_BONUS = 4;
    private static final int MAGIC = 2;
    //private static final int MAGIC_BONUS = 1;

    public BrineBlast() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BrineBlastAction(m, new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
    }

    @Override
    public void triggerOnGlowCheck() {
        glow50();
    }

    public AbstractCard makeCopy() {
        return new BrineBlast();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
            //upgradeMagicNumber(MAGIC_BONUS);
        }
    }

}
