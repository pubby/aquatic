package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.powers.MinePower;

public class Deploy extends AbstractAquaticCard {
    public static final String ID = "Deploy";
    public static final String IMG = "cards/deploy.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int BLOCK_BONUS = 2;
    private static final int MAGIC = 10;
    private static final int MAGIC_BONUS = 5;

    public Deploy() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));

        boolean planted = false;

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo == null || mo.isDeadOrEscaped()) continue;
            addToBot(new ApplyPowerAction(mo, p, new MinePower(mo, p, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            planted |= (mo != null && !mo.hasPower("Artifact"));
        }
        if (planted) {
            CardCrawlGame.sound.playA("AquaticMod:PLANT_MINE", 0.0f);
        }
    }

    public AbstractCard makeCopy() {
        return new Deploy();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_BONUS);
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
