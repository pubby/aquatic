package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import aquaticmod.powers.SquidFormPower;
import java.util.ArrayList;

public class HookAction extends AbstractGameAction {
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString((String)"BetterToHandAction").TEXT;
    private AbstractPlayer player;
    private boolean upgrade;

    public HookAction(boolean upgrade) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.upgrade = upgrade;
    }

    private void move(AbstractCard c) {
        if (player.hand.size() < 10) {
            player.hand.addToHand(c);
            player.discardPile.removeCard(c);
            if (AbstractDungeon.player.hasPower(SquidFormPower.POWER_ID) && c.type == AbstractCard.CardType.ATTACK) {
                c.modifyCostForCombat(-9);
            }
        }

        c.lighten(false);

        if (upgrade && !c.upgraded) {
            c.upgrade();
            c.superFlash();
        }

        c.applyPowers();
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            if (player.discardPile.isEmpty()) {
                isDone = true;
                return;
            }

            if (player.discardPile.size() == 1) {
                AbstractCard c = player.discardPile.getBottomCard();
                move(c);
                isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(player.discardPile, 1, TEXT[0], false);

            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                move(c);
                c.unhover();
                c.applyPowers();
            }

            for (AbstractCard c : player.discardPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0f;
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }

        tickDuration();

        if (isDone) {
            for (AbstractCard c : player.hand.group) {
                c.applyPowers();
            }
        }
    }
}


