package rbq.wtf.lycoris.client.gui.ClickGUI.Component;

import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.ModuleCategory;

import java.awt.*;

public class CategoryButton extends Component {
    public final ModuleCategory category;
    private float x;
    private float y;

    public CategoryButton(ModuleCategory Category, ClickGUI clickGUI) {
        super(clickGUI);
        this.category = Category;
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();
        if (this.isHovered(
                x,
                y,
                x + FontLoaders.default20.getStringWidth(category.toString()),
                y + FontLoaders.default20.getHeight(),
                mouseX,
                mouseY
        ) && Mouse.isButtonDown(0)) {
            clickGUI.currentModuleType = category;
            clickGUI.valueComponentList.clear();
            clickGUI.currentModule = null;
            clickGUI.moduleWheel = 0;
            clickGUI.valueWheel = 0;
            clickGUI.currentActiveTextValue = null;
        }
    }

    @Override
    public void render() {
        if (category != clickGUI.currentModuleType) {
            FontLoaders.default20.drawString(category.toString(),
                    x,
                    y,
                    new Color(255, 255, 255).getRGB());
        } else {
            FontLoaders.default20.drawString(category.toString(),
                    x,
                    y,
                    new Color(101, 81, 255).getRGB());
        }
    }

    @Override
    public float getHeight() {
        return FontLoaders.default20.getStringWidth(category.toString());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }
}
