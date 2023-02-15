package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import aquaticmod.fields.DeepField;

public class Cloister extends AbstractAquaticCard {
    public static final String ID = "Cloister";
    public static final String IMG = "cards/cloister.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int BLOCK_BONUS = 3;
    private static final int MAGIC = 3;
    private static final int MAGIC_BONUS = 1;

    public Cloister() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (p.hasPower(EntanglePower.POWER_ID)) {
            addToBot(new GainBlockAction(p, p, magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        return new Cloister();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
