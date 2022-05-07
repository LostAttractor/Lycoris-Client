package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.gui.NewClickGUI.NewClickGUI;
import rbq.wtf.lycoris.client.module.ModuleCategory;

import java.awt.*;

public class CategoryButton extends Component{
    private ModuleCategory category;
    private float x;
    private float y;
    public CategoryButton(ModuleCategory Category) {

    }


    @Override
    public void updateComponent(float X, float Y , float mouseX, float mouseY) {
        this.x = X;
        this.y = Y;
        render();
    }

    @Override
    public void render() {
        if (category != NewClickGUI.currentModuleType) {
            FontLoaders.default20.drawString(category.toString(),
                    x,
                    y,
                    new Color(255, 255, 255).getRGB());
        } else {
            FontLoaders.default20.drawString(category.toString(),
                    x,
                    y,
                    new Color(101,81,255).getRGB());
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }
}
