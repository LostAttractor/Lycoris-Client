package rbq.wtf.lycoris.client.gui.ClickGUI.Component;

import org.lwjgl.input.Keyboard;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.gui.ClickGUI.Utils.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.value.TextValue;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.ChatAllowedCharacters;

import java.awt.*;

public class TextValueComponent extends Component {
    public TextValue value;
    private float x;
    private float y;

    public TextValueComponent(TextValue value, ClickGUI clickGUI) {
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

        RenderUtil.drawFastRoundedRect(x + 220 - 10,
                y + 10 + 1,
                x + 310,
                y + 30 - 1,
                2,
                new Color(38, 38, 38).getRGB()
        );
        FontLoaders.default20.drawCenteredString(value.get(),
                x + 260,
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
            clickGUI.currentActiveTextValue = this;
        } else {
            if (clickGUI.currentActiveTextValue != null) {
                if (clickGUI.currentActiveTextValue == this) {
                    clickGUI.currentActiveTextValue = null;
                }
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_BACK) {
            if (value.get().length() != 0) {
                value.set(value.get().substring(0, value.get().length() - 1));
            }
        } else {
            if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
                value.set(value.get() + typedChar);
            }
        }

    }
}
