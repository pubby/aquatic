package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.TyphoonAction;

public class Typhoon extends AbstractAquaticCard {
    public static final String ID = "Typhoon";
    public static final String IMG = "cards/typhoon.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = -1;
    private static final int DAMAGE = 8;
    //private static final int DAMAGE_UPGRADE = 2;

    public Typhoon() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.exhaust = true;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TyphoonAction(p, multiDamage, damageTypeForTurn, freeToPlayOnce, energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new Typhoon();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeDamage(DAMAGE_UPGRADE);
            isInnate = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

