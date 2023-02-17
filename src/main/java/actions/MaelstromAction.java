package aquaticmod.actions;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import aquaticmod.powers.SwimPower;

public class MaelstromAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int energyOnUse;
    private AbstractPlayer p;
    private int magic;

    public MaelstromAction(AbstractPlayer p, int magic, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.magic = magic;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if (effect > 0 && magic > 0) {
            addToBot(new ApplyPowerAction(p, p, new SwimPower(effect * magic), effect * magic));
            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        isDone = true;
    }
}


