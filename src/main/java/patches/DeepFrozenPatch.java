package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import aquaticmod.fields.DeepField;
import aquaticmod.fields.FrozenField;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.CardGroup",
        method="initializeDeck"
)
public class DeepFrozenPatch
{
    public static void Postfix(CardGroup __instance, CardGroup masterDeck)
    {
        ArrayList<AbstractCard> moveToBottom = new ArrayList<>();
        for (AbstractCard c : __instance.group) {
            if (DeepField.deep.get(c) && !(c.inBottleFlame || c.inBottleLightning || c.inBottleTornado)) {
                moveToBottom.add(c);
            }
        }

        for (AbstractCard c : moveToBottom) {
            //copy.removeCard(c);
            __instance.moveToBottomOfDeck(c);
        }
    }
}
