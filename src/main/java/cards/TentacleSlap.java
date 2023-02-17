package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import aquaticmod.powers.SwimPower;
import aquaticmod.patches.AttackEffectEnum;

public class TentacleSlap extends AbstractAquaticCard {
    public static final String ID = "TentacleSlap";
    public static final String IMG = "cards/tentacleslap.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int DAMAGE = 3;
    private static final int DAMAGE_BONUS = 2;
    private static final int MAGIC = 1;

    public TentacleSlap() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.cardsToPreview = new Hook();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffectEnum.SLAP));
        addToBot(new ApplyPowerAction(p, p, new EntanglePower((AbstractCreature)p)));
        addToBot(new MakeTempCardInHandAction((AbstractCard)new Hook(), magicNumber, false));
    }

    public AbstractCard makeCopy() {
        return new TentacleSlap();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
        }
    }

}
