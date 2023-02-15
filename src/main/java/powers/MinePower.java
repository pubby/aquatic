package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.powers.AbstractPower;
import aquaticmod.powers.NuclearPower;
import aquaticmod.relics.OvenMitt;
import aquaticmod.fields.DetonateField;

import aquaticmod.AquaticMod;

public class MinePower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:MinePower";
    //public static final String IMG = "powers/mine.png";
    private AbstractCreature source;
    private boolean processed = false;

    public MinePower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath("powers/mine"));

        this.owner = owner;
        this.source = source;
        this.amount = amount;
        if (this.amount >= 9999) {
            this.amount = 9999;
        }
        this.updateDescription();
        //this.img = new Texture(AquaticMod.getResourcePath(IMG));
        this.type = PowerType.DEBUFF;
    }

    private int backfireAmount() { 
        return amount / 5;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + backfireAmount() + DESCRIPTIONS[2];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != owner && !processed) {
            flash();

            addToTop(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE, true));

            if (!DetonateField.detonates.get(info)) {
                if (AbstractDungeon.player.hasRelic(OvenMitt.ID)) {
                    AbstractDungeon.player.getRelic(OvenMitt.ID).flash();
                }
                else {
                    addToTop(new DamageAction(info.owner, new DamageInfo(info.owner, backfireAmount(), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE, true));
                }
            }

            if (owner.hasPower(NuclearPower.POWER_ID)) {
                AbstractPower power = owner.getPower(NuclearPower.POWER_ID);

                power.flash();

                if (power.amount <= 1) {
                    addToTop(new RemoveSpecificPowerAction(owner, owner, NuclearPower.POWER_ID));
                } else {
                    addToTop(new ReducePowerAction(owner, owner, NuclearPower.POWER_ID, 1));
                }
            }
            else {
                amount = 0;
                addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            }

            processed = true;
            CardCrawlGame.sound.playA("AquaticMod:EXPLODE_MINE", -0.3f);
        }
        return damageAmount;
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        processed = false;
    }

}

