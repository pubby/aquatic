package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.powers.SquidFormPower;
import aquaticmod.fields.DeepField;
import aquaticmod.cards.Hook;

public class SquidForm extends AbstractAquaticCard {
    public static final String ID = "SquidForm";
    public static final String IMG = "cards/squidform.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 3;
    private static final int MAGIC = 4;
    private static final int MAGIC_BONUS = 1;

    public SquidForm() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        //DeepField.deep.set(this, true);
        this.cardsToPreview = new Hook(true);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        //this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SquidFormPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SquidForm();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //DeepField.deep.set(this, false);
            //isEthereal = false;
            upgradeMagicNumber(MAGIC_BONUS);
            //rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //initializeDescription();
        }
    }
}
