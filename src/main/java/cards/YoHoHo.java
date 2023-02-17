package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import aquaticmod.powers.ShuffleLoseDexterityPower;

public class YoHoHo extends AbstractAquaticCard {
    public static final String ID = "YoHoHo";
    public static final String IMG = "cards/yohoho.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int MAGIC = 2;
    //private static final int MAGIC_BONUS = 1;

    public YoHoHo() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ShuffleLoseDexterityPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new YoHoHo();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(MAGIC_BONUS);
            upgradeBaseCost(UPGRADE_COST);
        }
    }

}
