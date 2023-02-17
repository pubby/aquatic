package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import aquaticmod.fields.FrozenField;

public class FreezeCardAction extends AbstractGameAction {
    private AbstractCard c = null;

    public FreezeCardAction(AbstractCard c) {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
        this.c = c;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            FrozenField.freezeCard(c);
            c.applyPowers();
        }

        tickDuration();
    }

}


