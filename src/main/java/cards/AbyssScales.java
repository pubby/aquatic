package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.fields.DeepField;
import aquaticmod.fields.ScalesField;

public class AbyssScales extends AbstractAquaticCard {
    public static final String ID = "AbyssScales";
    public static final String IMG = "cards/abyssscales.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int BLOCK = 10;
    private static final int BLOCK_BONUS = 3;

    public AbyssScales() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        DeepField.deep.set(this, true);
        ScalesField.scales.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    public AbstractCard makeCopy() {
        return new AbyssScales();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
        }
    }
}
