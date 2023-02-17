package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import aquaticmod.fields.ScalesField;
import aquaticmod.fields.FrozenField;

public class IcyScales extends AbstractAquaticCard {
    public static final String ID = "IcyScales";
    public static final String IMG = "cards/icyscales.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int BLOCK = 15;
    private static final int BLOCK_BONUS = 5;
    //private static final int MAGIC = 1;
    //private static final int MAGIC_BONUS = 1;

    public IcyScales() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        //this.magicNumber = this.baseMagicNumber = MAGIC;
        //this.exhaust = true;
        ScalesField.scales.set(this, true);
        FrozenField.startFrozenCard(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new IcyScales();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
            //upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
