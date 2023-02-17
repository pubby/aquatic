package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class MopUpAction extends AbstractGameAction {
    private static final float DURATION = 0.1f;
    private DamageInfo info;

    public MopUpAction(AbstractCreature target, DamageInfo info, int amount) {
        
        this.info = info;
        this.setValues(target, info);
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = DURATION;
    }

    @Override
    public void update() {
        if (duration == DURATION && target != null) {
            if(target.currentHealth <= target.maxHealth / 2 || target.isDying) {
                addToTop(new DrawCardAction(AbstractDungeon.player, 1));
                addToTop(new GainEnergyAction(1));
            }

            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            target.damage(info);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        tickDuration();
    }

}


