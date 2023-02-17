package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import aquaticmod.actions.HookAction;

public class Hook extends AbstractAquaticCard {
    public static final String ID = "Hook";
    public static final String IMG = "cards/hook.png";

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 0;

    private static final int COST = 0;
    //private static final int UPGRADE_COST = 0;

    private static final int MAGIC = 1;
    //private static final int MAGIC_BONUS = 1;


    public Hook() {
        this(false);
    }

    public Hook(boolean doUpgrade) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
        this.selfRetain = true;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        if(doUpgrade) upgrade();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HookAction(upgraded));
    }

    public AbstractCard makeCopy() {
        return new Hook();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADE_COST);
            //upgradeMagicNumber(MAGIC_BONUS);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
