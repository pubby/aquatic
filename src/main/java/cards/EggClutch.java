package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.powers.EggClutchPower;
import aquaticmod.fields.DeepField;

public class EggClutch extends AbstractAquaticCard {
    public static final String ID = "EggClutch";
    public static final String IMG = "cards/eggclutch.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int MAGIC_BONUS = 1;

    public EggClutch() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        DeepField.deep.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EggClutchPower(p, magicNumber, 2), 2));
        //addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
        //addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new EggClutch();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
