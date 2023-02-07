package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import aquaticmod.fields.DeepField;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.CardGroup",
        method="initializeDeck"
)
public class DeepPatch
{
    @SpireInsertPatch(
            rloc=4,
            localvars={"copy"}
    )
    public static void Insert(CardGroup __instance, CardGroup masterDeck, CardGroup copy)
    {
        ArrayList<AbstractCard> moveToBottom = new ArrayList<>();
        for (AbstractCard c : copy.group) {
            if (DeepField.deep.get(c)) {
                moveToBottom.add(c);
            }
        }

        for (AbstractCard c : moveToBottom) {
            copy.removeCard(c);
            copy.moveToBottomOfDeck(c);
        }
    }
}
