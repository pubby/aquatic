package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import aquaticmod.fields.FrozenField;
import java.util.ArrayList;

public class IcicleWallAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AquaticMod:FreezeAction");
    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPlayer p = AbstractDungeon.player;
    private int blockPerCard;
    private int thornsPerCard;
    private ArrayList<AbstractCard> cannotFreeze = new ArrayList();

    //public static final String[] TEXT = uiStrings.TEXT;
    public static final float DURATION = Settings.ACTION_DUR_XFAST;

    public IcicleWallAction(int blockPerCard, int thornsPerCard) {
        this.actionType = AbstractGameAction.ActionType.BLOCK;
        this.duration = DURATION;
        this.blockPerCard = blockPerCard;
        this.thornsPerCard = thornsPerCard;
    }

    @Override
    public void update() {
        if (duration == DURATION) {
            for (AbstractCard c : p.hand.group) {
                if (!FrozenField.get(c).frozen) continue;
                cannotFreeze.add(c);
            }

            if (cannotFreeze.size() == p.hand.group.size()) {
                isDone = true;
                return;
            }

            p.hand.group.removeAll(cannotFreeze);
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                FrozenField.freezeCard(c);
                c.superFlash();
                p.hand.addToTop(c);

                addToBot(new GainBlockAction((AbstractCreature)p, p, blockPerCard));
                addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, thornsPerCard), thornsPerCard));
            }

            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            isDone = true;
        }

        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : cannotFreeze) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }
}


