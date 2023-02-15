package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import aquaticmod.cards.ShrimpyStrike;

public class ShrimpyAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCard c = null;

    public ShrimpyAction(AbstractCard card, AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.c = card;
        this.setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        boolean killed = false;

        if (duration == Settings.ACTION_DUR_FAST && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            target.damage(info);

            AbstractCard deckCard = null;
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if ((c instanceof ShrimpyStrike) && c.canUpgrade()) {
                    deckCard = c;
                    break;
                }
            }

            if (deckCard != null && !(!((AbstractMonster)target).isDying && target.currentHealth > 0 || target.halfDead || target.hasPower("Minion"))) {
                killed = true;
                c.upgrade();
                deckCard.upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(deckCard);
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        tickDuration();

        if (isDone && killed && c != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0f, (float)Settings.HEIGHT / 2.0f));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }

    }
}


