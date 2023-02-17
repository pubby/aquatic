package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import aquaticmod.powers.SwimPower;

public class DarkWaters extends AbstractAquaticCard {
    public static final String ID = "DarkWaters";
    public static final String IMG = "cards/darkwaters.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int MAGIC = 3;
    private static final int MAGIC_BONUS = 1;

    public DarkWaters() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.isInnate = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SwimPower(magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new EntanglePower((AbstractCreature)p)));
    }

    public AbstractCard makeCopy() {
        return new DarkWaters();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
