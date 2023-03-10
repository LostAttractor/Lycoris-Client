package rbq.lycoris.client.gui.clickgui.component;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import rbq.lycoris.client.gui.clickgui.ClickGUI;
import rbq.lycoris.client.font.FontLoaders;
import rbq.lycoris.client.module.Module;

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
                FontLoaders.default18.drawStringWithShadow("Bind:" + Keyboard.getKeyName(module.getKeyBind()),
                        x,
                        y,
                        -1);
            }
        } else {
            FontLoaders.default18.drawStringWithShadow("Bind:" + Keyboard.getKeyName(module.getKeyBind()),
                    x,
                    y,
                    -1);
        }


    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.isHovered(x,
                y - 5.0F,
                x + FontLoaders.default18.getStringWidth("Bind:" + Keyboard.getKeyName(module.getKeyBind())),
                y + 5.0F,
                mouseX,
                mouseY)) {
            if (Mouse.isButtonDown(0)) {
                clickGUI.currentActiveTextValue = this;
            } else if (Mouse.isButtonDown(3)) {
                module.setKeyBind(Keyboard.CHAR_NONE);
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
        this.module.setKeyBind(keyCode);
        clickGUI.currentActiveTextValue = null;
    }
}
