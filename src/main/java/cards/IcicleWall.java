package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import aquaticmod.actions.IcicleWallAction;

public class IcicleWall extends AbstractAquaticCard {
    public static final String ID = "IcicleWall";
    public static final String IMG = "cards/iciclewall.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int BLOCK = 6;
    private static final int BLOCK_BONUS = 2;
    private static final int MAGIC = 1;
    //private static final int MAGIC_BONUS = 1;

    public IcicleWall() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        //this.exhaust = true;
        //startFrozen();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IcicleWallAction(block, magicNumber));
        //AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber), magicNumber));
        //toFreeze();
    }

    public AbstractCard makeCopy() {
        return new IcicleWall();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
            //upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
