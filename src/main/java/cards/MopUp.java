package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.MopUpAction;
import aquaticmod.fields.DeepField;

public class MopUp extends AbstractAquaticCard {
    public static final String ID = "MopUp";
    public static final String IMG = "cards/mopup.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 10;
    private static final int DAMAGE_BONUS = 3;

    public MopUp() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        DeepField.deep.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MopUpAction(m, new DamageInfo(p, damage, damageTypeForTurn), damage));
    }

    @Override
    public void triggerOnGlowCheck() {
        glow50();
    }

    public AbstractCard makeCopy() {
        return new MopUp();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
        }
    }

}
