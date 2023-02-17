package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.EntanglePower;

public class NetCast extends AbstractAquaticCard {
    public static final String ID = "NetCast";
    public static final String IMG = "cards/netcast.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int MAGIC = 3;
    //private static final int MAGIC_BONUS = 2;

    public NetCast() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EntanglePower((AbstractCreature)p)));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy() {
        return new NetCast();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(MAGIC_BONUS);
            upgradeBaseCost(UPGRADE_COST);
            //rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //initializeDescription();
        }
    }
}
