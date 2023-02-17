package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.powers.SwimPower;
import aquaticmod.fields.ScalesField;

public class BrightScales extends AbstractAquaticCard {
    public static final String ID = "BrightScales";
    public static final String IMG = "cards/brightscales.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int BLOCK_BONUS = 3;

    public BrightScales() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        //DeepField.deep.set(this, true);
        ScalesField.scales.set(this, true);
    }

    public int calcAmount() {
        if(AbstractDungeon.player.hasPower(SwimPower.POWER_ID)) {
            AbstractPower power = AbstractDungeon.player.getPower(SwimPower.POWER_ID);
            return power.amount;
        }
        return 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = calcAmount();
        if(amount > 0) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
    }

    public AbstractCard makeCopy() {
        return new BrightScales();
    }

    @Override
    public void applyPowers() {
        baseBlock = calcAmount() + BLOCK;
        if (upgraded) 
            baseBlock += BLOCK_BONUS;
        super.applyPowers();
        rawDescription = !upgraded ? cardStrings.DESCRIPTION : cardStrings.UPGRADE_DESCRIPTION;
        rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
