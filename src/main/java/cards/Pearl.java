package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import aquaticmod.fields.DeepField;

public class Pearl extends AbstractAquaticCard {
    public static final String ID = "Pearl";
    public static final String IMG = "cards/pearl.png";

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 0;

    private static final int COST = -2;
    private static final int MAGIC = 1;

    public Pearl() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        DeepField.deep.set(this, true);
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public AbstractCard makeCopy() {
        return new Pearl();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            DeepField.deep.set(this, false);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
