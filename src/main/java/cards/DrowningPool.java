package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.powers.DrowningPoolPower;

public class DrowningPool extends AbstractAquaticCard {
    public static final String ID = "DrowningPool";
    public static final String IMG = "cards/drowningpool.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int MAGIC = 6;
    private static final int MAGIC_BONUS = 3;

    public DrowningPool() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DrowningPoolPower(p, magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new DrowningPool();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
