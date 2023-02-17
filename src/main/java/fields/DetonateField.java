package aquaticmod.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.DamageInfo",
        method=SpirePatch.CLASS
)
public class DetonateField
{
    public static SpireField<Boolean> detonates = new SpireField<>(() -> false);
}
