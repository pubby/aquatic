package aquaticmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;


import aquaticmod.AquaticMod;
import aquaticmod.cards.Hook;
import aquaticmod.fields.DeepField;

public class DrowningPoolPower extends AbstractAquaticPower {
    public static final String POWER_ID = "AquaticMod:DrowningPoolPower";
    public static final String IMG = "powers/drowningpool";

    public DrowningPoolPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AquaticMod.getResourcePath(IMG));
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (!DeepField.deep.get(card)) return;

        flash();
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new CleaveEffect()));
        } else {
            addToBot(new VFXAction(owner, new CleaveEffect(), 0.2f));
        }

        addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
    }
}

