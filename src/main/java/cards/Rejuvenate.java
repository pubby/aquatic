package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rejuvenate extends AbstractAquaticCard {
    public static final String ID = "Rejuvenate";
    public static final String IMG = "cards/rejuvenation.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 2;

    public Rejuvenate() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.currentBlock > 0) {
            addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, p.currentBlock), p.currentBlock));
        }
    }

    public AbstractCard makeCopy() {
        return new Rejuvenate();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
