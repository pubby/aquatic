package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.fields.DeepField;

public class FeedingFrenzy extends AbstractAquaticCard {
    public static final String ID = "FeedingFrenzy";
    public static final String IMG = "cards/feedingfrenzy.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int DAMAGE = 12;
    private static final int DAMAGE_BONUS = 4;
    private static final int MAGIC = 4;
    private static final int MAGIC_BONUS = -1;

    public FeedingFrenzy() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;
        this.tags.add(AbstractCard.CardTags.HEALING);
        DeepField.deep.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amountToGain = AbstractDungeon.player.masterDeck.size() / magicNumber;

        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new HealAction(p, p, amountToGain));
    }

    public AbstractCard makeCopy() {
        return new FeedingFrenzy();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_BONUS);
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }

}
