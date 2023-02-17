package aquaticmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import aquaticmod.powers.IcebergPower;
import aquaticmod.fields.FrozenField;

public class Iceberg extends AbstractAquaticCard {
    public static final String ID = "Iceberg";
    public static final String IMG = "cards/iceberg.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public Iceberg() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        FrozenField.startFrozenCard(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new IcebergPower(p)));
    }

    public AbstractCard makeCopy() {
        return new Iceberg();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
