package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import aquaticmod.powers.InkCloudPower;

public class InkCloud extends AbstractAquaticCard {
    public static final String ID = "InkCloud";
    public static final String IMG = "cards/inkcloud.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 0;

    private static final int COST = 1;
    private static final int MAGIC = 8;
    //private static final int MAGIC_BONUS = 2;

    public InkCloud() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InkCloudPower(p, magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new InkCloud();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(MAGIC_BONUS);
            isInnate = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
