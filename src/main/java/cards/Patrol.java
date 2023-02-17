package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.cards.DepthCharge;

public class Patrol extends AbstractAquaticCard {
    public static final String ID = "Patrol";
    public static final String IMG = "cards/patrol.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int BLOCK_BONUS = 2;
    private static final int MAGIC = 1;


    public Patrol() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.cardsToPreview = new DepthCharge();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        addToBot(new MakeTempCardInDrawPileAction((AbstractCard)new DepthCharge(upgraded), magicNumber, false, true, true));
    }

    public AbstractCard makeCopy() {
        return new Patrol();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
            cardsToPreview = new DepthCharge(true);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
