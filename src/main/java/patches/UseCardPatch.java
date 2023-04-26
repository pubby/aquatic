package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
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
import aquaticmod.fields.FrozenField;
import aquaticmod.actions.FreezeCardAction;

import java.util.ArrayList;

@SpirePatch(
        cls="com.megacrit.cardcrawl.characters.AbstractPlayer",
        method="useCard"
)
public class UseCardPatch
{
    public static void Prefix(AbstractPlayer __instance, AbstractCard c, @ByRef AbstractMonster[] monster, int energyOnUse)
    {
        FrozenField.FrozenDetails d = FrozenField.get(c);

        if ((monster[0] == null) && 
            (d.target == AbstractCard.CardTarget.ENEMY || d.target == AbstractCard.CardTarget.ALL_ENEMY)) {
            monster[0] = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        }
    }
}
