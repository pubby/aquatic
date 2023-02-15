package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.actions.ShrimpyAction;

public class ShrimpyStrike extends AbstractAquaticCard {
    public static final String ID = "ShrimpyStrike";
    public static final String IMG = "cards/shrimpystrike.png";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int MAGIC = 1;
    private static final int MAGIC_BONUS = 2;

    public ShrimpyStrike() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.tags.add(CardTags.STRIKE);
        this.startFrozen();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            for (int i = 0; i < magicNumber; ++i) {
                addToBot(new DamageAction((AbstractCreature)m, new DamageInfo(p, damage, damageTypeForTurn), 
                        i % 2 == 0 ? AbstractGameAction.AttackEffect.SLASH_VERTICAL : AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

            }
        }
        else {
            addToBot(new ShrimpyAction(this, (AbstractCreature)m, new DamageInfo(p, damage, damageTypeForTurn)));
        }
    }

    public AbstractCard makeCopy() {
        return new ShrimpyStrike();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
            unstartFrozen();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
