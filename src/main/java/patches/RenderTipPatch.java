package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import aquaticmod.AquaticMod;
import aquaticmod.powers.MinePower;
import aquaticmod.relics.OvenMitt;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.monsters.AbstractMonster",
        method="renderDamageRange"
)
public class RenderTipPatch
{
    private static Texture intentImg = new Texture(AquaticMod.getResourcePath("vfx/explosionicon.png"));

    public static void Postfix(AbstractMonster __instance, SpriteBatch sb, Hitbox ___intentHb, BobEffect ___bobEffect)
    {
        if (__instance.hasPower(MinePower.POWER_ID) && !AbstractDungeon.player.hasRelic(OvenMitt.ID)) {
            sb.setColor(Color.WHITE.cpy());

            AbstractPower power = __instance.getPower(MinePower.POWER_ID);

            int damage = power.amount / 5;

            if(AbstractDungeon.player.hasPower("IntangiblePlayer"))
                damage = 1;

            sb.draw(intentImg, 
                    ___intentHb.cX - 64.0f, 
                    ___intentHb.cY - 64.0f + ___bobEffect.y - 96.0f * Settings.scale,
                    64.0f, 64.0f, 128.0f, 128.0f, 
                    Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);

            FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont,
                Integer.toString(damage),
                ___intentHb.cX - 30.0f * Settings.scale,  
                ___intentHb.cY +  ___bobEffect.y - 96.0f * Settings.scale, 
                new Color(1.0f, 0.8f, 0.7f, 1.0f));

        }
    }
}
