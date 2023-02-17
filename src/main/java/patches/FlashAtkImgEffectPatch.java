package aquaticmod.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.DamageHeartEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import aquaticmod.AquaticMod;
import aquaticmod.patches.AttackEffectEnum;

//import static Astrologer.AstrologerMod.assetPath;
//import static Astrologer.AstrologerMod.logger;

public class FlashAtkImgEffectPatch {
    private static final String waterTexturePath = AquaticMod.getResourcePath("vfx/water.png");
    private static final String tentacleTexturePath = AquaticMod.getResourcePath("vfx/tentacle.png");

    private static Texture waterTexture = ImageMaster.loadImage(waterTexturePath);
    private static Texture tentacleTexture = ImageMaster.loadImage(tentacleTexturePath);

    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "loadImage"
    )
    @SpirePatch(
            clz = DamageHeartEffect.class,
            method = "loadImage"
    )
    public static class loadImagePatch
    {
        @SpirePrefixPatch
        public static SpireReturn<TextureAtlas.AtlasRegion> tryLoadImage(AbstractGameEffect __instance, AbstractGameAction.AttackEffect ___effect)
        {
            try {
                if (___effect == AttackEffectEnum.WATER) {
                    return SpireReturn.Return(new TextureAtlas.AtlasRegion(waterTexture, 0, 0, waterTexture.getWidth(), waterTexture.getHeight()));
                }
                else if (___effect == AttackEffectEnum.SLAP) {
                    ReflectionHacks.setPrivate(__instance, AbstractGameEffect.class, "rotation", MathUtils.random(360.0f));
                    return SpireReturn.Return(new TextureAtlas.AtlasRegion(tentacleTexture, 0, 0, tentacleTexture.getWidth(), tentacleTexture.getHeight()));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "playSound"
    )
    @SpirePatch(
            clz = DamageHeartEffect.class,
            method = "playSound"
    )
    public static class playSoundPatch
    {
        @SpirePrefixPatch
        public static SpireReturn tryPlaySound(AbstractGameEffect __instance, AbstractGameAction.AttackEffect effect)
        {
            if (effect == AttackEffectEnum.WATER) {
                CardCrawlGame.sound.playA("AquaticMod:WATER", 0.0f);
            }
            else if (effect == AttackEffectEnum.SLAP) {
                CardCrawlGame.sound.playA("AquaticMod:SLAP", 0.0f);
            }
            return SpireReturn.Continue();
        }
    }
}
