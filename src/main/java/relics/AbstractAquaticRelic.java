package aquaticmod.relics;

import com.badlogic.gdx.graphics.Texture;

import basemod.abstracts.CustomRelic;
import aquaticmod.AquaticMod;

public abstract class AbstractAquaticRelic extends CustomRelic{

    public AbstractAquaticRelic(String id, String img, RelicTier tier, LandingSound sfx) {
        super(id, new Texture(AquaticMod.getResourcePath(img)), tier, sfx);
    }

}
