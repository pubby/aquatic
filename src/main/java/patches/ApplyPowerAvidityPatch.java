package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import aquaticmod.powers.AbstractAquaticPower;
import aquaticmod.powers.SwimPower;
import aquaticmod.powers.SwiftSwimPower;
import aquaticmod.powers.AvidityPower;

@SpirePatch(
        cls="com.megacrit.cardcrawl.actions.common.ApplyPowerAction",
        method="update"
)
public class ApplyPowerAvidityPatch
{
    public static SpireReturn<Void> Prefix(ApplyPowerAction __instance, AbstractCreature ___target, AbstractPower ___powerToApply,
            float ___duration, float ___startingDuration) {
        if (___target != null && (___powerToApply instanceof SwimPower) && ___target.hasPower(AvidityPower.POWER_ID)) {
            if (___duration == ___startingDuration) {
                ___target.getPower(AvidityPower.POWER_ID).flash();
                SwimPower sp = (SwimPower)___powerToApply;
                sp.trigger(sp.amount);
            }

            __instance.isDone = true;
            return SpireReturn.Return(null);
        }

        return SpireReturn.Continue();
    }
}
