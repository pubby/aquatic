package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.cards.status.Slimed;

public class AlgaeBloom extends AbstractAquaticCard {
    public static final String ID = "AlgaeBloom";
    public static final String IMG = "cards/algaebloom.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 0;

    private static final int COST = 0;
    private static final int MAGIC = 1;
    private static final int SLIMED = 1;

    public AlgaeBloom() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.selfRetain = true;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.cardsToPreview = new Slimed();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        if (upgraded) {
            addToBot(new MakeTempCardInHandAction((AbstractCard)new Slimed(), SLIMED));
        }
        else {
            addToBot(new MakeTempCardInDrawPileAction((AbstractCard)new Slimed(), SLIMED, false, true, true));
        }
    }

    public AbstractCard makeCopy() {
        return new AlgaeBloom();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
