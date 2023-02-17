package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.powers.AbstractAquaticPower;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction",
        method=SpirePatch.CONSTRUCTOR
)
public class EmptyDeckShufflePatch
{
    public static void Postfix(EmptyDeckShuffleAction __instance)
    {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof AbstractAquaticPower) {
                ((AbstractAquaticPower)p).onShuffle();
            }
        }
    }
}
