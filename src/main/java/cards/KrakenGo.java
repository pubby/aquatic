package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.fields.DeepField;
import aquaticmod.fields.FrozenField;
import aquaticmod.powers.SwimPower;

public class KrakenGo extends AbstractAquaticCard {
    public static final String ID = "KrakenGo";
    public static final String IMG = "cards/thekraken.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int DAMAGE = 22;
    private static final int DAMAGE_BONUS = 6;
    //private static final int MAGIC = 1;
    //private static final int MAGIC_BONUS = 1;

    public KrakenGo() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        //this.magicNumber = this.baseMagicNumber = MAGIC;
        DeepField.deep.set(this, true);
        FrozenField.startFrozenCard(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new GainEnergyAction(2));
        //addToBot(new ApplyPowerAction(p, p, new SwimPower(magicNumber), magicNumber));
        //addToBot(new MakeTempCardInHandAction((AbstractCard)new Hook(), magicNumber, false));
        //toFreeze();
    }

    /*
    public void triggerOnEndOfPlayerTurn() {
        updateCost(COST);
        addToBot(new ReduceCostAction(this));
    }
    */

    public AbstractCard makeCopy() {
        return new KrakenGo();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
            //upgradeMagicNumber(MAGIC_BONUS);
            //upgradeBaseCost(UPGRADE_COST);
        }
    }

}
