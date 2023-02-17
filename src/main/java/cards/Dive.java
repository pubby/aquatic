package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.DiveAction;

public class Dive extends AbstractAquaticCard {
    public static final String ID = "Dive";
    public static final String IMG = "cards/dive.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public Dive() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        isInnate = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiveAction(1));
    }

    public AbstractCard makeCopy() {
        return new Dive();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
