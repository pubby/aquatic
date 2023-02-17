package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import aquaticmod.fields.FrozenField;
import aquaticmod.actions.FreezeCardAction;
import aquaticmod.powers.SquidFormPower;

import java.util.ArrayList;

public class SquidFormPatch
{
    @SpirePatch(
            clz=ShowCardAndAddToHandEffect.class,
            paramtypez={ AbstractCard.class },
            method=SpirePatch.CONSTRUCTOR
    )
    public static class P1 {
        public static void PostFix(ShowCardAndAddToHandEffect __instance, AbstractCard card) {
            if (AbstractDungeon.player.hasPower(SquidFormPower.POWER_ID) && card.type == AbstractCard.CardType.ATTACK) {
                card.modifyCostForCombat(-9);
            }
        }
    }

    @SpirePatch(
            clz=ShowCardAndAddToHandEffect.class,
            paramtypez={ AbstractCard.class, float.class, float.class },
            method=SpirePatch.CONSTRUCTOR
    )
    public static class P2 {
        public static void PostFix(ShowCardAndAddToHandEffect __instance, AbstractCard card, float offx, float offy) {
            if (AbstractDungeon.player.hasPower(SquidFormPower.POWER_ID) && card.type == AbstractCard.CardType.ATTACK) {
                card.modifyCostForCombat(-9);
            }
        }
    }

    @SpirePatch(
            clz=AbstractPlayer.class,
            paramtypez={},
            method="onCardDrawOrDiscard"
    )
    public static class P3 {
        public static void PostFix(AbstractPlayer __instance, AbstractCard card, float offx, float offy) {
            if (AbstractDungeon.player.hasPower(SquidFormPower.POWER_ID)) {
                for (AbstractCard c : __instance.hand.group) {
                    if (c.type != AbstractCard.CardType.ATTACK || c.costForTurn == 0) continue;
                    c.modifyCostForCombat(-9);
                }
                __instance.hand.applyPowers();
                __instance.hand.glowCheck();
            }
        }
    }
}
