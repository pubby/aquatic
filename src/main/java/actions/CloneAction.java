package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
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

public class CloneAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CopyAction");
    public static final String[] TEXT = uiStrings.TEXT;
    public static final float DURATION = Settings.ACTION_DUR_XFAST;
    private ArrayList<AbstractCard> cannotDuplicate = new ArrayList();
    private int copies;

    public CloneAction(AbstractPlayer p, int copies) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.p = p;
        this.copies = copies;
    }

    private void doClone(AbstractCard c) {
        p.hand.moveToBottomOfDeck(c);
        addToBot(new MakeTempCardInDrawPileAction(c, copies, false, true, true));
    }

    @Override
    public void update() {
        if (duration == DURATION) {
            for (AbstractCard c : p.hand.group) {
                if (canClone(c)) continue;
                cannotDuplicate.add(c);
            }
            if (cannotDuplicate.size() == p.hand.group.size()) {
                isDone = true;
                return;
            }
            if (p.hand.group.size() - cannotDuplicate.size() == 1) {
                for (AbstractCard c : p.hand.group) {
                    if (!canClone(c)) continue;
                    doClone(c);
                    isDone = true;
                    return;
                }
            }

            p.hand.group.removeAll(cannotDuplicate);
            if (p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                tickDuration();
                return;
            }

            if (p.hand.group.size() == 1) {
                doClone(p.hand.getTopCard());
                returnCards();
                isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
            doClone(tmpCard);
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }

    private boolean canClone(AbstractCard card) {
        return true;
        //return card.type.equals((Object)AbstractCard.CardType.SKILL);
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotDuplicate) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }
}


