package aquaticmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.CorruptionPower;
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
    public static SpireReturn<?> Prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse)
    {
        FrozenField.FrozenDetails d = FrozenField.get(c);

        if (d.frozen) {
            if ((monster == null) &&
                (d.target == AbstractCard.CardTarget.ENEMY || d.target == AbstractCard.CardTarget.ALL_ENEMY)) {
                monster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            }

            c.calculateCardDamage(monster);
            if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
                c.energyOnUse = EnergyPanel.totalCount;
            }

            if (c.cost == -1 && c.isInAutoplay) {
                c.freeToPlayOnce = true;
            }


            AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
            if (!c.dontTriggerOnUseCard) {
                __instance.hand.triggerOnOtherCardPlayed(c);
            }

            __instance.hand.removeCard(c);
            __instance.cardInUse = c;

            c.target_x = Settings.WIDTH / 2f;
            c.target_y = Settings.HEIGHT / 2f;

            if (!(c.costForTurn <= 0 || c.freeToPlay() || c.isInAutoplay || __instance.hasPower(CorruptionPower.POWER_ID) && c.type == AbstractCard.CardType.SKILL)) {
                __instance.energy.use(c.costForTurn);
            }
            if (!__instance.hand.canUseAnyCard() && !__instance.endTurnQueued) {
                AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
            }

            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
