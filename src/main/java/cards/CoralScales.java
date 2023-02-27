package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import aquaticmod.fields.ScalesField;

public class CoralScales extends AbstractAquaticCard {
    public static final String ID = "CoralScales";
    public static final String IMG = "cards/coralscales.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int BLOCK = 10;
    private static final int BLOCK_BONUS = 3;

    public CoralScales() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        ScalesField.scales.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber), magicNumber));
    }

    public static int countCards() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!ScalesField.scales.get(c)) continue;
            ++count;
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (!ScalesField.scales.get(c)) continue;
            ++count;
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (!ScalesField.scales.get(c)) continue;
            ++count;
        }
        return count;
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber = countCards();
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }


    public AbstractCard makeCopy() {
        return new CoralScales();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
        }
    }
}
