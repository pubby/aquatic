package aquaticmod.actions;

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
import java.util.ArrayList;

public class RisingTidesAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ForethoughtAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private boolean chooseAny;

    public RisingTidesAction(boolean upgraded) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.chooseAny = upgraded;
    }

    private void drawCards(int n) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, n));
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                isDone = true;
                return;
            }

            if (p.hand.size() == 1 && !chooseAny) {
                AbstractCard c = p.hand.getTopCard();
                p.hand.moveToBottomOfDeck(c);
                AbstractDungeon.player.hand.refreshHandLayout();
                drawCards(1);
                isDone = true;
                return;
            }

            if (!chooseAny) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
            }

            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            int n = 0;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.moveToBottomOfDeck(c);
                n += 1;
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            drawCards(n);
        }

        tickDuration();
    }


}


