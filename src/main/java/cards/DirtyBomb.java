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
import aquaticmod.powers.NuclearPower;

public class DirtyBomb extends AbstractAquaticCard {
    public static final String ID = "DirtyBomb";
    public static final String IMG = "cards/dirtybomb.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int MINES = 10;
    private static final int MAGIC = 1;
    private static final int MAGIC_BONUS = 1;

    public DirtyBomb() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new MinePower(m, p, MINES), MINES, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new NuclearPower(m, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        if (m != null && !m.hasPower("Artifact")) {
            CardCrawlGame.sound.playA("AquaticMod:PLANT_MINE", 0.0f);
        }
    }

    public AbstractCard makeCopy() {
        return new DirtyBomb();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
