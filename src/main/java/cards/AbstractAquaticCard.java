package aquaticmod.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import aquaticmod.AquaticMod;
import aquaticmod.patches.AbstractCardEnum;

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
}
