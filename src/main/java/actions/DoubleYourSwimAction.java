package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import aquaticmod.powers.SwimPower;

public class DoubleYourSwimAction extends AbstractGameAction {
    public DoubleYourSwimAction(AbstractCreature target) {
        this.duration = 0.0f;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.target = target;
    }

    @Override
    public void update() {
        if (target != null && target.hasPower(SwimPower.POWER_ID)) {
            AbstractDungeon.effectsQueue.add(new LightningEffect(target.hb.cX, target.hb.cY));
            AbstractPower power = target.getPower(SwimPower.POWER_ID);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, target, new SwimPower(power.amount), power.amount));
        }
        tickDuration();
    }

}


