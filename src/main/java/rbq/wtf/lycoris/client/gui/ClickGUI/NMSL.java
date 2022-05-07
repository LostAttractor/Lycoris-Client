package rbq.wtf.lycoris.client.gui.ClickGUI;

import net.minecraft.client.gui.GuiScreen;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;

import java.awt.*;

public class NMSL extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        FontLoaders.default25.drawStringWithShadow("ZhangMingJunSense.LGBT",
                5,
                5,
                new Color(0,200,100).getRGB());

    }
}
