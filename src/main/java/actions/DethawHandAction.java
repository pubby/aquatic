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
import aquaticmod.fields.FrozenField;
import java.util.ArrayList;

public class DethawHandAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AquaticMod:UnfreezeAction");
    public static final String[] TEXT = uiStrings.TEXT;
    public static final float DURATION = Settings.ACTION_DUR_XFAST;
    private ArrayList<AbstractCard> cannotUnfreeze = new ArrayList();
    private int num = -1;

    public DethawHandAction(int num) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.num = num;
    }

    private void unfreeze(AbstractCard c) {
        FrozenField.unfreezeCard(c);
        c.superFlash();
    }

    @Override
    public void update() {
        if (duration == DURATION) {
            if (num < 0) {
                for (AbstractCard c : p.hand.group) {
                    if (FrozenField.get(c).frozen) {
                        unfreeze(c);
                    }
                }
                isDone = true;
                return;
            }
            else {
                for (AbstractCard c : p.hand.group) {
                    if (FrozenField.get(c).frozen) continue;
                    cannotUnfreeze.add(c);
                }

                if (cannotUnfreeze.size() == p.hand.group.size()) {
                    isDone = true;
                    return;
                }

                if (p.hand.group.size() - cannotUnfreeze.size() == 1) {
                    for (AbstractCard c : p.hand.group) {
                        if (!FrozenField.get(c).frozen) continue;
                        unfreeze(c);
                        isDone = true;
                        return;
                    }
                }

                p.hand.group.removeAll(cannotUnfreeze);
                if (p.hand.group.size() > 1) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                    tickDuration();
                    return;
                }

                if (p.hand.group.size() == 1) {
                    unfreeze(p.hand.getTopCard());
                    returnCards();
                    isDone = true;
                    return;
                }
            }
        }
        else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                unfreeze(c);
                p.hand.addToTop(c);
            }

            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            isDone = true;
        }

        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : cannotUnfreeze) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }

}


