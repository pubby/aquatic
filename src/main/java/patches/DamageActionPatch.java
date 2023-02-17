package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.patches.AttackEffectEnum;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.actions.common.DamageAction",
        method="update"
)
public class DamageActionPatch
{
    public static void Postfix(DamageAction __instance)
    {
        if (__instance.isDone) {
            if (__instance.attackEffect == AttackEffectEnum.WATER) {
                __instance.target.tint.color.set(Color.BLUE);
                __instance.target.tint.changeColor(Color.WHITE.cpy());
            }
        }
    }
}
