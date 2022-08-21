package rbq.wtf.lycoris.client.gui.ClickGUI.Component;

import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.*;

import java.awt.*;

public class ModuleButtonList extends Component {
    public final ModuleCategory category;
    private float x;
    private float y;

    public ModuleButtonList(ModuleCategory Category, ClickGUI clickGUI) {
        super(clickGUI);
        this.category = Category;
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();
    }

    @Override
    public void render() {
        float moduleY = 0;
        for (Module module : Client.moduleManager.getModulesInType(category)) {
            if (module.isState()) {
                FontLoaders.default20.drawString(module.getName(),
                        x,
                        y + moduleY,
                        new Color(87, 72, 216).getRGB());
            } else {
                FontLoaders.default20.drawString(module.getName(),
                        x,
                        y + moduleY,
                        new Color(255, 255, 255).getRGB());
            }
            moduleY = moduleY + FontLoaders.default20.getHeight() + 15;
        }
        this.setHeight(moduleY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        float moduleY = 0;
        for (Module module : Client.moduleManager.getModulesInType(category)) {
            if (this.isHovered(
                    x,
                    y + moduleY,
                    x + 5 + FontLoaders.default20.getStringWidth(module.getName()),
                    y + 5 + moduleY + FontLoaders.default20.getHeight(),
                    mouseX,
                    mouseY
            )) {
                switch (mouseButton) {
                    case 0:
                        module.toggle();
                        break;
                    case 1:
                        clickGUI.currentModule = module;
                        clickGUI.valueWheel = 0;
                        clickGUI.valueComponentList.clear();
                        clickGUI.currentActiveTextValue = null;
                        for (Value<?> value : module.getValues()) {
                            if (value instanceof BooleanValue) {
                                clickGUI.valueComponentList.add(new BooleanValueComponent((BooleanValue) value, clickGUI));
                            }
                            if (value instanceof NumberValue) {
                                clickGUI.valueComponentList.add(new NumberValueComponent((NumberValue) value, clickGUI));
                            }
                            if (value instanceof ModeValue) {
                                clickGUI.valueComponentList.add(new ModeValueComponent((ModeValue) value, clickGUI));
                            }
                            if (value instanceof ColorValue) {
                                clickGUI.valueComponentList.add(new ColorValueComponent((ColorValue) value, clickGUI));
                            }
                            if (value instanceof TextValue) {
                                clickGUI.valueComponentList.add(new TextValueComponent((TextValue) value, clickGUI));
                            }
                        }
                        clickGUI.valueComponentList.add(new ModuleBindComponent(module, clickGUI));
                        break;
                    default:
                        break;
                }
            }

            moduleY = moduleY + FontLoaders.default20.getHeight() + 15;
        }

    }
}
