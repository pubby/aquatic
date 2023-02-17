package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import aquaticmod.powers.SquidFormPower;
import aquaticmod.powers.SwimPower;
import aquaticmod.powers.AvidityPower;

@SpirePatch(
        cls="com.megacrit.cardcrawl.actions.common.ApplyPowerAction",
        method=SpirePatch.CONSTRUCTOR,
        paramtypez={
            AbstractCreature.class, 
            AbstractCreature.class, 
            AbstractPower.class, 
            int.class, 
            boolean.class, 
            AbstractGameAction.AttackEffect.class
        }
)
public class ApplyPowerPatch
{
    public static void Prefix(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
        if (powerToApply instanceof SquidFormPower) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type != AbstractCard.CardType.ATTACK) continue;
                c.modifyCostForCombat(-9);
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.type != AbstractCard.CardType.ATTACK) continue;
                c.modifyCostForCombat(-9);
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.type != AbstractCard.CardType.ATTACK) continue;
                c.modifyCostForCombat(-9);
            }
            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c.type != AbstractCard.CardType.ATTACK) continue;
                c.modifyCostForCombat(-9);
            }
        }

    }
}
