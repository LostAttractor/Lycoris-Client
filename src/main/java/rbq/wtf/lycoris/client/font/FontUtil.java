package rbq.wtf.lycoris.client.font;

import rbq.wtf.lycoris.client.utils.MinecraftInstance;
import rbq.wtf.lycoris.client.wrapper.wrappers.util.ResourceLocation;

import java.awt.*;


public class FontUtil extends MinecraftInstance {

    public static Font getFontFromTTF(ResourceLocation fontLocation, float fontSize, int fontType) {
        Font output = null;
        try {
            output = Font.createFont(fontType, mc.getResourceManager().getResource(fontLocation).getInputStream());
            output = output.deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}