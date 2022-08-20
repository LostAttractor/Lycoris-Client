package rbq.wtf.lycoris.client.gui.ClickGUI.Component;

import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.gui.ClickGUI.Utils.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.value.ModeValue;

import java.awt.*;

public class ModeValueComponent extends Component {
    public ModeValue value;
    private float x;
    private float y;

    public ModeValueComponent(ModeValue value, ClickGUI clickGUI) {
        super(clickGUI);
        this.value = value;
        this.setHeight(35);
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();
    }

    @Override
    public void render() {
        FontLoaders.default20.drawString(value.getName(),
                x,
                y,
                new Color(255, 255, 255).getRGB());
        RenderUtil.drawFastRoundedRect(x + 220,
                y + 10,
                x + 310,
                y + 30,
                2,
                new Color(83, 69, 193).getRGB()
        );
        RenderUtil.drawFastRoundedRect(x + 220 + 1,
                y + 10 + 1,
                x + 310 - 1,
                y + 30 - 1,
                2,
                new Color(27, 27, 27).getRGB()
        );
        FontLoaders.default20.drawCenteredString(value.getModeName(),
                x + 265,
                y + 16,
                new Color(255, 255, 255).getRGB()
        );
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.isHovered(x + 120,
                y + 10,
                x + 310,
                y + 30,
                mouseX,
                mouseY)) {
            value.incrementSelection();
        }
    }
}
