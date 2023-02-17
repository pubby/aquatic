package aquaticmod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import aquaticmod.AquaticMod;
import aquaticmod.fields.FrozenField;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method="renderTint"
)
public class RenderFrozenPatch
{
    public static void Postfix(AbstractCard __instance, SpriteBatch sb)
    {
        if (!Settings.hideCards && FrozenField.frozen.get(__instance)) {
            AquaticMod.loadFrozenTexture();
            sb.setColor(Color.WHITE.cpy());
            sb.draw(AquaticMod.frozenTexture, __instance.current_x - 256.0f, __instance.current_y - 256.0f, 
                    256.0f, 256.0f, 512.0f, 512.0f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, 
                    __instance.angle, 0, 0, 512, 512, false, false);

        }
    }
}
