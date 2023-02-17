package aquaticmod.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import aquaticmod.AquaticMod;
import aquaticmod.patches.AbstractCardEnum;
import aquaticmod.fields.FrozenField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractAquaticCard extends CustomCard {
    //public boolean reshuffleOnUse = false; //if true -> don't discard on next use, has to be reset in the "use" method
    //public boolean reshuffleOnDiscardFromHand = false; //if true -> reshuffle in draw pile if discarded while in hand, not used atm

    protected AbstractCard cardPreviewTooltip;
    protected String[] EXTENDED_DESCRIPTION;
    protected CardStrings cardStrings;

    public AbstractAquaticCard(final String id,
                              String img,
                              final int cost,
                              final CardType type,
                              final CardRarity rarity,
                              final CardTarget target) {
        this("AquaticMod:"+id, languagePack.getCardStrings("AquaticMod:"+id), img, cost, type, rarity, target, AbstractCardEnum.AQUATIC);
    }

    public AbstractAquaticCard(final String id,
                              String img,
                              final int cost,
                              final CardType type,
                              final CardRarity rarity,
                              final CardTarget target,
                              final CardColor color) {
        this("AquaticMod:"+id, languagePack.getCardStrings("AquaticMod:"+id), img, cost, type, rarity, target, color);
    }

    private AbstractAquaticCard(String id,
                                CardStrings cardStrings,
                                String img,
                                int cost,
                                CardType type,
                                CardRarity rarity,
                                CardTarget target,
                                CardColor color) {
        super(id, cardStrings.NAME, AquaticMod.getResourcePath(img), cost, cardStrings.DESCRIPTION, type, color, rarity, target);
        this.cardStrings = cardStrings;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

        initializeTitle();
        initializeDescription();
    }

    protected void glow50() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if((m.currentHealth <= m.maxHealth / 2) && !m.isDead && !m.isDying) {
                glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void applyPowers() {
        if (FrozenField.startFrozen.get(this)) {
            FrozenField.startFrozen.set(this, false);
            FrozenField.freezeCard(this);
        }
        super.applyPowers();
    }
}
