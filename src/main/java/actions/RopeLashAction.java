package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;

public class RopeLashAction extends AbstractGameAction {
    public RopeLashAction() {
        this.duration = 0.0f;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    @Override
    public void update() {
        if (duration == 0.0f) {
            ArrayList<AbstractCard> groupCopy = new ArrayList<AbstractCard>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce && c.type == AbstractCard.CardType.ATTACK) {
                    groupCopy.add(c);
                    continue;
                }
            }

            for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
                if (i.card == null) continue;
                groupCopy.remove(i.card);
            }

            if (!groupCopy.isEmpty()) {
                AbstractCard c = (AbstractCard)groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                c.freeToPlayOnce = true;
            }
        }

        tickDuration();
    }

}


