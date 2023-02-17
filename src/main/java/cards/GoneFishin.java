package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class GoneFishin extends AbstractAquaticCard {
    public static final String ID = "GoneFishin";
    public static final String IMG = "cards/gonefishin.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 0;

    private static final int COST = -2;

    private static final int MAGIC = 1;
    //private static final int MAGIC_BONUS = 1;

    public GoneFishin() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.cardsToPreview = new Hook(false);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new MakeTempCardInHandAction((AbstractCard)new Hook(upgraded), 1, false));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public AbstractCard makeCopy() {
        return new GoneFishin();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(MAGIC_BONUS);
            this.cardsToPreview = new Hook(true);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
