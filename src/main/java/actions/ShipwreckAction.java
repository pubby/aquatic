package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.cards.CardGroup;
import java.util.ArrayList;

public class ShipwreckAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    public static final float DURATION = Settings.ACTION_DUR_XFAST;

    public ShipwreckAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }

    @Override
    public void update() {
        if (duration == DURATION) {

            if (p.drawPile.isEmpty()) {
                isDone = true;
                return;
            }

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : p.drawPile.group) {
                if (c.cost != 0 && !c.freeToPlayOnce) continue;
                tmp.addToRandomSpot(c);
            }

            while (!tmp.isEmpty() && p.hand.size() < 10) {
                tmp.shuffle();
                AbstractCard card = tmp.getBottomCard();
                tmp.removeCard(card);
                card.unhover();
                card.lighten(true);
                card.setAngle(0.0f);
                card.drawScale = 0.12f;
                card.targetDrawScale = 0.75f;
                card.current_x = CardGroup.DRAW_PILE_X;
                card.current_y = CardGroup.DRAW_PILE_Y;
                p.drawPile.removeCard(card);
                AbstractDungeon.player.hand.addToTop(card);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }

            isDone = true;
        }

        tickDuration();
    }

}


