package aquaticmod;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
//import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import aquaticmod.cards.*;
import aquaticmod.characters.AquaticCharacter;
import aquaticmod.patches.AbstractCardEnum;
import aquaticmod.patches.AquaticEnum;
import aquaticmod.relics.*;
import aquaticmod.AssetLoader;
//import aquaticmod.powers.AbstractAquaticPower;

import java.nio.charset.StandardCharsets;

class Keyword
{
    public String ID = "";
    public String PROPER_NAME;
    public String[] NAMES;
    public String DESCRIPTION;
}

@SpireInitializer
public class AquaticMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, PostDrawSubscriber, OnStartBattleSubscriber, PreMonsterTurnSubscriber, AddAudioSubscriber {

    public static final Logger logger = LogManager.getLogger(AquaticMod.class);

    public static final String modID = "aquaticmod";
    private static final String MODNAME = "AquaticMod";
    private static final String AUTHOR = "Pubby";
    private static final String DESCRIPTION = "Adds The Aquatic character";

    private static final Color AQUATIC_COLOR = CardHelper.getColor(20.0f, 10.0f, 100.0f);
    private static final String ASSETS_FOLDER = "aquaticmod_images";

    private static final String ATTACK_CARD = "512/bg_attack_aquatic.png";
    private static final String SKILL_CARD = "512/bg_skill_aquatic.png";
    private static final String POWER_CARD = "512/bg_power_aquatic.png";
    private static final String FROZEN_CARD = "512/bg_frozen_aquatic.png";

    private static final String ENERGY_ORB = "512/card_witch_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_aquatic.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_aquatic.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_aquatic.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_aquatic_orb.png";

    private static final String CHAR_BUTTON = "charSelect/button.png";
    private static final String CHAR_PORTRAIT = "charSelect/portrait.png";
    public static final String CHAR_SHOULDER_1 = "char/shoulder.png";
    public static final String CHAR_SHOULDER_2 = "char/shoulder2.png";
    public static final String CHAR_CORPSE = "char/corpse.png";
    public static final String CHAR_SKELETON_ATLAS = "char/skeleton.atlas";
    public static final String CHAR_SKELETON_JSON = "char/skeleton.json";

    public static final String BADGE_IMG = "badge.png";

    public static AssetLoader assets = new AssetLoader();

    public static Texture frozenTexture;

    public static final String getResourcePath(String resource) {
        return ASSETS_FOLDER + "/" + resource;
    }

    public AquaticMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.AQUATIC,
                AQUATIC_COLOR, AQUATIC_COLOR, AQUATIC_COLOR, AQUATIC_COLOR, AQUATIC_COLOR, AQUATIC_COLOR, AQUATIC_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
                getResourcePath(ENERGY_ORB),
                getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
                getResourcePath(ENERGY_ORB_PORTRAIT));
    }

    public static void loadFrozenTexture() {
        if (frozenTexture == null) {
            frozenTexture = ImageMaster.loadImage(getResourcePath(FROZEN_CARD));
        }
    }

    public static void initialize() {
        logger.info("Initializing Aquatic Mod");
        new AquaticMod();
    }

    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(getResourcePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("No settings", 400.0f, 700.0f, settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }


    public void receiveEditCharacters() {
        BaseMod.addCharacter(new AquaticCharacter("The Aquatic"),
                getResourcePath(CHAR_BUTTON),
                getResourcePath(CHAR_PORTRAIT),
                AquaticEnum.AQUATIC);
    }


    public void receiveEditRelics() {
        //RelicLibrary.add(new BigFin());
        RelicLibrary.add(new MagicRod());
        RelicLibrary.add(new PlatinumReel());
        RelicLibrary.add(new WornOar());
        RelicLibrary.add(new Lifebuoy());
        RelicLibrary.add(new CenozoicTooth());
        RelicLibrary.add(new Caviar());
        RelicLibrary.add(new Amoeba());
        RelicLibrary.add(new OvenMitt());
        /*
        BaseMod.addRelicToCustomPool(new BirdCage(), AbstractCardEnum.WITCH);
        BaseMod.addRelicToCustomPool(new WalkingCane(), AbstractCardEnum.WITCH);
        BaseMod.addRelicToCustomPool(new Scissors(), AbstractCardEnum.WITCH);
        BaseMod.addRelicToCustomPool(new ToyHorse(), AbstractCardEnum.WITCH);
        */
    }

    public void receiveEditCards() {
        // BASIC (4)
        BaseMod.addCard(new Strike_Aquatic());
        BaseMod.addCard(new Defend_Aquatic());
        BaseMod.addCard(new Pearl());
        BaseMod.addCard(new Hook());

        // COMMON (20) (goal 19?)

        // Attacks (12)
        BaseMod.addCard(new BrineBlast());
        BaseMod.addCard(new Bubble());
        BaseMod.addCard(new FieryFish());
        BaseMod.addCard(new HarpoonShot());
        BaseMod.addCard(new Nibble());
        BaseMod.addCard(new RazorFin());
        BaseMod.addCard(new RopeLash());
        BaseMod.addCard(new ShrimpyStrike());
        BaseMod.addCard(new StoneAged());
        BaseMod.addCard(new TorpedoStrike());
        BaseMod.addCard(new Wrap());
        BaseMod.addCard(new WringOut());

        // Skills (9)
        BaseMod.addCard(new AbyssScales());
        BaseMod.addCard(new Anemone());
        BaseMod.addCard(new Cloister());
        BaseMod.addCard(new FloatCard());
        BaseMod.addCard(new Patrol());
        BaseMod.addCard(new Plant());
        BaseMod.addCard(new Puddle());
        BaseMod.addCard(new ShoreScales());
        BaseMod.addCard(new NetCast());

        // UNCOMMON (36)

        // Attacks (8)
        BaseMod.addCard(new BarnacleBlitz());
        BaseMod.addCard(new Compression());
        BaseMod.addCard(new Demolish());
        BaseMod.addCard(new MopUp());
        BaseMod.addCard(new RogueWave());
        BaseMod.addCard(new Surf());
        BaseMod.addCard(new TentacleSlap());

        // Skills (18)
        BaseMod.addCard(new AlgaeBloom());
        BaseMod.addCard(new BlownOut());
        BaseMod.addCard(new BrightScales());
        BaseMod.addCard(new Chitin());
        BaseMod.addCard(new CoralScales());
        BaseMod.addCard(new DarkWaters());
        BaseMod.addCard(new Deploy());
        BaseMod.addCard(new Detonate());
        BaseMod.addCard(new FrozenSolid());
        BaseMod.addCard(new GoneFishin());
        BaseMod.addCard(new IcicleWall());
        BaseMod.addCard(new IcyScales());
        BaseMod.addCard(new Lure());
        BaseMod.addCard(new RisingTides());
        BaseMod.addCard(new Sabotage());
        BaseMod.addCard(new SonarSweep());
        BaseMod.addCard(new Maelstrom());
        BaseMod.addCard(new UpToSpeed());

        // Powers (10)
        BaseMod.addCard(new Ahoy());
        BaseMod.addCard(new BombsAway());
        BaseMod.addCard(new Cephalopod());
        BaseMod.addCard(new DrowningPool());
        BaseMod.addCard(new Iceberg());
        BaseMod.addCard(new InkCloud());
        BaseMod.addCard(new Lagoon());
        BaseMod.addCard(new MineField());
        BaseMod.addCard(new Waterborn());
        BaseMod.addCard(new YoHoHo());

        // RARE (19) (goal 17?)

        // Attacks (6)
        BaseMod.addCard(new FeedingFrenzy());
        BaseMod.addCard(new FromBelow());
        BaseMod.addCard(new KrakenGo());
        BaseMod.addCard(new Shipwreck());
        BaseMod.addCard(new SteelWave());
        BaseMod.addCard(new Typhoon());

        // Skills (8)
        BaseMod.addCard(new Clone());
        BaseMod.addCard(new DirtyBomb());
        BaseMod.addCard(new Dive());
        BaseMod.addCard(new Nuke());
        BaseMod.addCard(new SunkenChest());
        BaseMod.addCard(new Tsunami());
        BaseMod.addCard(new Rebreather());
        BaseMod.addCard(new Rejuvenate());

        // Powers (5)
        BaseMod.addCard(new Avidity());
        BaseMod.addCard(new Bacteria());
        BaseMod.addCard(new EggClutch());
        BaseMod.addCard(new SquidForm());
        BaseMod.addCard(new SwiftSwim());
    }


    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("localization/eng/AquaticMod-Relic-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal("localization/eng/AquaticMod-Card-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/eng/AquaticMod-Power-Strings.json");

        String uiStrings = Gdx.files.internal("localization/eng/AquaticMod-UI-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
    }


    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("localization/eng/AquaticMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }

        //BaseMod.addKeyword(modID.toLowerCase(), "Swim", ["swim"], "Hello world");
        //BaseMod.addKeyword(modID.toLowerCase(), "Swim", ["Swim"], "Hello world2");
    }

    public void receivePostDraw(AbstractCard c) {
        /*
        AbstractPlayer player = AbstractDungeon.player;
        //custom callback for card draw on powers
        for (AbstractPower p : player.powers) {
            if (p instanceof AbstractAquaticPower) {
                p.onCardDraw(c);
            }
        }
        cardsDrawnThisTurn++;
        cardsDrawnTotal++;
        if (c.type == CardType.CURSE) {
            cursesDrawnTotal++;
        }
        */
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster m) {
        //cardsDrawnThisTurn = 0;
        return true;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        /*
        cardsDrawnTotal = 0;
        cursesDrawnTotal = 0;
        cardsDrawnThisTurn = 0;
        */
    }

    @Override
    public void receiveAddAudio()
    {
        BaseMod.addAudio("AquaticMod:PLANT_MINE", "aquaticmod_audio/mine.ogg");
        BaseMod.addAudio("AquaticMod:EXPLODE_MINE", "aquaticmod_audio/explode_mine.ogg");
        BaseMod.addAudio("AquaticMod:NUKE", "aquaticmod_audio/nuke.ogg");
        BaseMod.addAudio("AquaticMod:SONAR", "aquaticmod_audio/sonar.ogg");
        BaseMod.addAudio("AquaticMod:ICE_BREAK", "aquaticmod_audio/icebreak.ogg");
    }

}

