package aquaticmod.characters;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.ScreenShake;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import org.lwjgl.Sys;
import aquaticmod.AquaticMod;
import aquaticmod.cards.AbstractAquaticCard;
import aquaticmod.cards.Pearl;
import aquaticmod.patches.AbstractCardEnum;
import aquaticmod.patches.AquaticEnum;

public class AquaticCharacter extends CustomPlayer{
    
    public static final int ENERGY_PER_TURN = 3;

    public static final String[] orbTextures = {
            AquaticMod.getResourcePath("char/orb/layer1.png"),
            AquaticMod.getResourcePath("char/orb/layer2.png"),
            AquaticMod.getResourcePath("char/orb/layer3.png"),
            AquaticMod.getResourcePath("char/orb/layer4.png"),
            AquaticMod.getResourcePath("char/orb/layer5.png"),
            AquaticMod.getResourcePath("char/orb/layer6.png"),
            AquaticMod.getResourcePath("char/orb/layer1d.png"),
            AquaticMod.getResourcePath("char/orb/layer2d.png"),
            AquaticMod.getResourcePath("char/orb/layer3d.png"),
            AquaticMod.getResourcePath("char/orb/layer4d.png"),
            AquaticMod.getResourcePath("char/orb/layer5d.png")
    };


    
    public AquaticCharacter(String name) {
        super(name, AquaticEnum.AQUATIC, orbTextures, AquaticMod.getResourcePath("char/orb/vfx.png"), null, new SpriterAnimation(AquaticMod.getResourcePath("char/spriter/aquatic.scml")));
        dialogX = drawX + 0.0f * Settings.scale;
        dialogY = drawY + 220.0f * Settings.scale;
        
        initializeClass(null, AquaticMod.getResourcePath(AquaticMod.CHAR_SHOULDER_2),
                AquaticMod.getResourcePath(AquaticMod.CHAR_SHOULDER_1),
                AquaticMod.getResourcePath(AquaticMod.CHAR_CORPSE), 
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
    }
    
    @Override
    public void applyEndOfTurnTriggers() {
        for (AbstractPower p : powers) {
            p.atEndOfTurn(true);
        }
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("AquaticMod:Strike_Aquatic");
        retVal.add("AquaticMod:Strike_Aquatic");
        retVal.add("AquaticMod:Strike_Aquatic");
        retVal.add("AquaticMod:Strike_Aquatic");
        retVal.add("AquaticMod:Strike_Aquatic");
        retVal.add("AquaticMod:Defend_Aquatic");
        retVal.add("AquaticMod:Defend_Aquatic");
        retVal.add("AquaticMod:Defend_Aquatic");
        retVal.add("AquaticMod:Defend_Aquatic");
        retVal.add("AquaticMod:Pearl");
        return retVal;
        //return getAllCards();
    }
    
    //for debug
    private static ArrayList<String> getAllCards() {
        ArrayList<String> out = new ArrayList<String>();
        for (AbstractCard card : BaseMod.getCustomCardsToAdd()) {
            out.add(card.cardID);
        }
        return out;
    }
    
    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("AquaticMod:MagicRod");
        UnlockTracker.markRelicAsSeen("AquaticMod:MagicCaster");
        return retVal;
    }
    
    
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Aquatic", "An archaic sea creature mobilized in a bowl. NL Overwhelms enemies with its swimming prowess.",
                75, 75, 0, 99, 5,
            this, getStartingRelics(), getStartingDeck(), false);
    }
    
    @Override
    public String getTitle(PlayerClass var1) {
        return "The Aquatic";
    }

    @Override
    public CardColor getCardColor() {
        return AbstractCardEnum.AQUATIC;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Pearl();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.CYAN;
    }

    @Override
    public String getLeaderboardCharacterName() {
        return "The Aquatic";
    }

    @Override
    public String getPortraitImageName() {
        return null;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("AquaticMod:INTRO", 0);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "BYRD_DEATH";
    }


    @Override
    public String getLocalizedCharacterName() {
        return "The Aquatic";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new AquaticCharacter(this.name);
    }
    
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
    
    @Override
    public Color getCardRenderColor() {
        return Color.CYAN;
    }
    
    @Override
    public String getVampireText() {
         return Vampires.DESCRIPTIONS[5];
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.BLUE;
    }
    
    @Override
    public String getSpireHeartText() {
        return "NL You flop like a fish...";
    }
    
    @Override
    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_VERTICAL};
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel(AquaticMod.getResourcePath("scenes/victory1.png")));
        panels.add(new CutscenePanel(AquaticMod.getResourcePath("scenes/victory2.png")));
        panels.add(new CutscenePanel(AquaticMod.getResourcePath("scenes/victory3.png")));
        return panels;
    }
}
