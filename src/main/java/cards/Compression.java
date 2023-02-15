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

public class Compression extends AbstractAquaticCard {
    public static final String ID = "Compression";
    public static final String IMG = "cards/compression.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int MAGIC_BONUS = 1;

    public Compression() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.shuffleBackIntoDrawPile = true;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    @Override
    public void applyPowers() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c == this) continue;
            ++count;
        }
        this.baseDamage = count * magicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public AbstractCard makeCopy() {
        return new Compression();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }

}
