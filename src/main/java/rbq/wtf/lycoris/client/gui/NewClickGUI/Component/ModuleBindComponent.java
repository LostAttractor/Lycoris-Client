package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.gui.NewClickGUI.NewClickGUI;
import rbq.wtf.lycoris.client.module.Module;

public class ModuleBindComponent extends Component{
    Module module;
    float x;
    float y;
    public ModuleBindComponent(Module module) {
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
        if (NewClickGUI.currentBindModule != null) {
            FontLoaders.default18.drawStringWithShadow("Bind to" ,
                    x,
                    y,
                    -1);
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
                if (NewClickGUI.currentBindModule == null) {
                    NewClickGUI.currentBindModule = module;
                } else {
                    NewClickGUI.currentBindModule = null;
                }
            }
        }
    }
}
