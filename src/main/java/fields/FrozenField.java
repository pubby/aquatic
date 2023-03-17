package aquaticmod.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import java.util.HashMap;
import java.util.UUID;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method=SpirePatch.CLASS
)
public class FrozenField
{
    public static class FrozenDetails
    {
        public boolean frozen = false;
        public boolean toFreeze = false;
        public AbstractCard.CardTarget target = AbstractCard.CardTarget.NONE;
    }

    public static SpireField<Boolean> cachedFrozen = new SpireField<>(() -> false);
    public static SpireField<Boolean> startFrozen = new SpireField<>(() -> false);
    public static HashMap<UUID, FrozenDetails> map = new HashMap<UUID, FrozenDetails>();

    static public FrozenDetails get(AbstractCard c) {
        FrozenDetails r = map.get(c.uuid);
        if (r == null) {
            r = new FrozenDetails();
            map.put(c.uuid, r);
        }
        return r;
    }

    static public void reset() { map.clear(); }

    static public void startFrozenCard(AbstractCard c) {
        startFrozen.set(c, true);
    }

    static public void unstartFrozenCard(AbstractCard c) {
        startFrozen.set(c, false);
    }

    static public void toFreezeCard(AbstractCard c) {
        get(c).toFreeze = true;
    }

    static public void freezeCard(AbstractCard c) {
        FrozenDetails d = get(c);

        if (d.frozen) return;
        d.target = c.target;
        d.toFreeze = false;
        d.frozen = true;
        c.target = AbstractCard.CardTarget.NONE;
        cachedFrozen.set(c, true);
        c.applyPowers();
    }
    
    static public void unfreezeCard(AbstractCard c) {
        FrozenDetails d = get(c);

        if (!d.frozen) return;
        c.target = d.target;
        d.toFreeze = false;
        d.frozen = false;
        cachedFrozen.set(c, false);
        c.applyPowers();
    }
}
