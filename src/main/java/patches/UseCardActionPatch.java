package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import aquaticmod.fields.FrozenField;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.actions.utility.UseCardAction",
        method="update"
)
public class UseCardActionPatch
{
    public static SpireReturn<Void> Prefix(UseCardAction __instance, AbstractCard ___targetCard, boolean ___reboundCard, float ___duration)
    {
        if (___duration == 0.15f && FrozenField.frozen.get(___targetCard)) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (___targetCard.dontTriggerOnUseCard) continue;
                p.onAfterUseCard(___targetCard, __instance);
            }

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                for (AbstractPower p : m.powers) {
                    if (___targetCard.dontTriggerOnUseCard) continue;
                    p.onAfterUseCard(___targetCard, __instance);
                }
            }

            FrozenField.unfreezeCard(___targetCard);
            CardCrawlGame.sound.playA("AquaticMod:ICE_BREAK", 0.0f);

            ___targetCard.freeToPlayOnce = false;
            ___targetCard.isInAutoplay = false;

            if (___targetCard.purgeOnUse) {
                AbstractDungeon.actionManager.addToTop(new ShowCardAndPoofAction(___targetCard));
                __instance.isDone = true;
                AbstractDungeon.player.cardInUse = null;
                return SpireReturn.Return(null);
            }

            AbstractDungeon.actionManager.addToBottom(new ShowCardAction(___targetCard));
            AbstractDungeon.player.cardInUse = null;
            
            if (___reboundCard) {
                AbstractDungeon.player.hand.moveToDeck(___targetCard, false);
            }
            else {
                AbstractDungeon.player.hand.moveToDiscardPile(___targetCard);
            }

            ___targetCard.exhaustOnUseOnce = false;
            ___targetCard.dontTriggerOnUseCard = false;
            AbstractDungeon.actionManager.addToBottom(new HandCheckAction());

            __instance.isDone = true;
            return SpireReturn.Return(null);
        }
        else if (___duration == 0.15f && FrozenField.toFreeze.get(___targetCard)) {
            FrozenField.freezeCard(___targetCard);
        }

        return SpireReturn.Continue();
    }
}
