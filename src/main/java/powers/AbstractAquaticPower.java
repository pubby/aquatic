package aquaticmod.powers;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import aquaticmod.AquaticMod;

import java.util.ArrayList;

public abstract class AbstractAquaticPower extends AbstractPower {
    public static final Logger logger = LogManager.getLogger(AquaticMod.class);
    protected Color renderColor = null;
    protected final String[] DESCRIPTIONS;

    public AbstractAquaticPower(String id, String imagePath) {
        this.ID = id;
        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
        name = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        logger.info("Loading power desc for " + ID + ": " + name + " " + DESCRIPTIONS[0]);

        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(imagePath + "128.png"), 0, 0, 84, 84);
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(imagePath + "48.png"), 0, 0, 32, 32);
    }

    public void onShuffle() {
    }

    /*n
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        if (renderColor == null) {
            sb.setColor(c);
        } else {
            sb.setColor(renderColor);
        }

        if (img != null) {
            sb.draw(img, x - 12.0f, y - 12.0f, 16.0f, 16.0f, 32.0f, 32.0f, Settings.scale * 1.5f, Settings.scale * 1.5f, 0.0f, 0, 0, 32, 32, false, false);
        } else {
            sb.draw(region48, x - (float) region48.packedWidth / 2.0f, y - (float) region48.packedHeight / 2.0f, (float) region48.packedWidth / 2.0f, (float) region48.packedHeight / 2.0f, region48.packedWidth, region48.packedHeight, Settings.scale, Settings.scale, 0.0f);
        }

        /*
        ArrayList<AbstractGameEffect> effectList = ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
        for (AbstractGameEffect e : effectList) {
            e.render(sb, x, y);
        }
    }
        */
}
