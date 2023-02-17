package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class Sabotage extends AbstractAquaticCard {
    public static final String ID = "Sabotage";
    public static final String IMG = "cards/sabotage.png";

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AquaticMod:Over50");
    public static final String[] TEXT = uiStrings.TEXT;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int MAGIC = 6;
    private static final int MAGIC_BONUS = 3;

    public Sabotage() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.currentHealth <= m.maxHealth / 2 || m.isDying) {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
            if (m != null && !m.hasPower("Artifact")) {
                addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, magicNumber), magicNumber));
            }
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0f, TEXT[0], true));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glow50();
    }

    public AbstractCard makeCopy() {
        return new Sabotage();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_BONUS);
        }
    }
}
