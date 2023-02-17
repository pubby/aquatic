package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class SonarSweep extends AbstractAquaticCard {
    public static final String ID = "SonarSweep";
    public static final String IMG = "cards/sonarsweep.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int MAGIC = 2;
    private static final int MAGIC_BONUS = 1;

    public SonarSweep() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;
        //this.shuffleBackIntoDrawPile = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));

        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }

        CardCrawlGame.sound.playA("AquaticMod:SONAR", 0.0f);
    }

    public AbstractCard makeCopy() {
        return new SonarSweep();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBlock(BLOCK_BONUS);
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
