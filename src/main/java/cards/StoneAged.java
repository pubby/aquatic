package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import aquaticmod.fields.FrozenField;

public class StoneAged extends AbstractAquaticCard {
    public static final String ID = "StoneAged";
    public static final String IMG = "cards/stoneaged.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int MAGIC = 8;
    private static final int MAGIC_BONUS = 2;

    public StoneAged() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        FrozenField.startFrozenCard(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void triggerWhenDrawn() {
        for (AbstractCard c : GetAllInBattleInstances.get(uuid)) {
            c.baseDamage += magicNumber;
            if (c.baseDamage < 0) {
                c.baseDamage = 0;
            }
        }

        applyPowers();
    }

    public AbstractCard makeCopy() {
        return new StoneAged();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }

}
