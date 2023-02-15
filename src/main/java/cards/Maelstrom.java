package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.MaelstromAction;

public class Maelstrom extends AbstractAquaticCard {
    public static final String ID = "Maelstrom";
    public static final String IMG = "cards/maelstrom.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = -1;
    private static final int MAGIC = 3;
    //private static final int MAGIC_BONUS = 3;

    public Maelstrom() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MaelstromAction(p, magicNumber, freeToPlayOnce, energyOnUse + (upgraded ? 1 : 0)));
    }

    public AbstractCard makeCopy() {
        return new Maelstrom();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(MAGIC_BONUS);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
