package aquaticmod.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method=SpirePatch.CLASS
)
public class FrozenField
{
    public static SpireField<Boolean> frozen = new SpireField<>(() -> false);
    public static SpireField<Boolean> startFrozen = new SpireField<>(() -> false);
    public static SpireField<Boolean> toFreeze = new SpireField<>(() -> false);
    public static SpireField<AbstractCard.CardTarget> target = new SpireField<>(() -> AbstractCard.CardTarget.NONE);


    static public void startFrozenCard(AbstractCard c) {
        FrozenField.startFrozen.set(c, true);
    }

    static public void unstartFrozenCard(AbstractCard c) {
        FrozenField.startFrozen.set(c, false);
    }

    static public void toFreezeCard(AbstractCard c) {
        FrozenField.toFreeze.set(c, true);
    }

    static public void freezeCard(AbstractCard c) {
        if (FrozenField.frozen.get(c)) return;
        FrozenField.target.set(c, c.target);
        FrozenField.toFreeze.set(c, false);
        FrozenField.frozen.set(c, true);
        c.target = AbstractCard.CardTarget.NONE;
        c.applyPowers();
    }
    
    static public void unfreezeCard(AbstractCard c) {
        if (!FrozenField.frozen.get(c)) return;
        c.target = FrozenField.target.get(c);
        FrozenField.toFreeze.set(c, false);
        FrozenField.frozen.set(c, false);
        c.applyPowers();
    }
}
