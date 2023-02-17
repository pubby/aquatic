package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SteelWave extends AbstractAquaticCard {
    public static final String ID = "SteelWave";
    public static final String IMG = "cards/steelwave.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int DAMAGE_BONUS = 3;
    private static final int BLOCK = 8;
    private static final int BLOCK_BONUS = 3;

    public SteelWave() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public AbstractCard makeCopy() {
        return new SteelWave();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
            upgradeBlock(BLOCK_BONUS);
        }
    }

}
