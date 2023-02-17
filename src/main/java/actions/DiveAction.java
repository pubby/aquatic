package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import aquaticmod.fields.DeepField;

public class DiveAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p = AbstractDungeon.player;

    public DiveAction(int amount) {
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : p.drawPile.group) {
                if (DeepField.deep.get(c)) {
                    tmp.addToRandomSpot(c);
                }
            }

            if (tmp.size() == 0) {
                isDone = true;
                return;
            }

            if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                if (p.hand.size() == 10) {
                    p.drawPile.moveToDiscardPile(card);
                    p.createHandIsFullDialog();
                } else {
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
                return;
            }

            if (tmp.size() <= amount) {
                for (int i = 0; i < tmp.size(); ++i) {
                    AbstractCard card = tmp.getNCardFromTop(i);
                    if (p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                        continue;
                    }
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
                this.isDone = true;
                return;
            }

            if (amount == 1) {
                AbstractDungeon.gridSelectScreen.open(tmp, amount, TEXT[0], false);
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, amount, TEXT[1], false);
            }

            tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                if (p.hand.size() == 10) {
                    p.drawPile.moveToDiscardPile(c);
                    p.createHandIsFullDialog();
                } else {
                    p.drawPile.removeCard(c);
                    p.hand.addToTop(c);
                }
                p.hand.refreshHandLayout();
                p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            p.hand.refreshHandLayout();
        }

        tickDuration();
    }
}

