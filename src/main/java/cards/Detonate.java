package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.fields.DeepField;
import aquaticmod.fields.DetonateField;

public class Detonate extends AbstractAquaticCard {
    public static final String ID = "Detonate";
    public static final String IMG = "cards/detonate.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 8;

    public Detonate() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        //this.exhaust = true;
        //DeepField.deep.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, damageTypeForTurn);
        DetonateField.detonates.set(info, true);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, info, AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractCard makeCopy() {
        return new Detonate();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
