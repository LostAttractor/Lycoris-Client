package rbq.wtf.lycoris.client.gui.ClickGUI.Component;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;

public class ModuleBindComponent extends Component {
    Module module;
    float x;
    float y;

    public ModuleBindComponent(Module module, ClickGUI clickGUI) {
        super(clickGUI);
        this.module = module;
        this.setHeight(15);
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();
    }

    @Override
    public void render() {
        if (clickGUI.currentActiveTextValue != null) {
            if (clickGUI.currentActiveTextValue == this) {
                FontLoaders.default18.drawStringWithShadow("Bind to",
                        x,
                        y,
                        -1);
            } else {
                FontLoaders.default18.drawStringWithShadow("Bind:" + Keyboard.getKeyName(module.getKey()),
                        x,
                        y,
                        -1);
            }
        } else {
            FontLoaders.default18.drawStringWithShadow("Bind:" + Keyboard.getKeyName(module.getKey()),
                    x,
                    y,
                    -1);
        }


    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.isHovered(x,
                y - 5.0F,
                x + FontLoaders.default18.getStringWidth("Bind:" + Keyboard.getKeyName(module.getKey())),
                y + 5.0F,
                mouseX,
                mouseY)) {
            if (Mouse.isButtonDown(0)) {
                clickGUI.currentActiveTextValue = this;
            } else if (Mouse.isButtonDown(3)) {
                module.setKey(0);
            }
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
        this.module.setKey(keyCode);
        clickGUI.currentActiveTextValue = null;
    }
}
