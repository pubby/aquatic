package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import aquaticmod.powers.BacteriaPower;

public class ToggleBacteriaAction extends AbstractGameAction {
    private boolean enable;

    public ToggleBacteriaAction(AbstractCreature target, boolean enable) {
        this.duration = 0.0f;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.target = target;
        this.enable = enable;
    }

    @Override
    public void update() {
        if (target != null && target.hasPower(BacteriaPower.POWER_ID)) {
            BacteriaPower power = (BacteriaPower)target.getPower(BacteriaPower.POWER_ID);
            power.enable = enable;
        }
        tickDuration();
    }

}


