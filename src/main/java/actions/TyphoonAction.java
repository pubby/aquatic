package aquaticmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class TyphoonAction
extends AbstractGameAction {
    private boolean freeToPlayOnce;
    //private int damage;
    public int[] multiDamage;
    private AbstractPlayer p;
    private DamageInfo.DamageType damageTypeForTurn;
    private int energyOnUse;

    public TyphoonAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.multiDamage = multiDamage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
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
        if (effect > 0) {
            for (int i = 0; i < effect; ++i) {
                int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
                for (int j = 0; j < temp; ++j) {
                    AbstractCreature rm = (AbstractCreature)AbstractDungeon.getCurrRoom().monsters.monsters.get(j);

                    if (rm == null || rm.isDead || rm.isDying) continue;

                    //addToBot(new DamageEnemyAction(p, multiDamage[j], damageType, AbstractGameAction.AttackEffect.NONE, true));

                    addToTop(new ApplyPowerAction(rm, AbstractDungeon.player, new WeakPower(rm, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
                    addToTop(new DamageAction(rm, new DamageInfo(p, multiDamage[j], damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                    addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1f));
                    addToTop(new VFXAction(new LightningEffect(rm.hb.cX, rm.hb.cY)));
                }
            }

            addToTop(new VFXAction(new WhirlwindEffect(), 0.0f));

            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        isDone = true;
    }
}


