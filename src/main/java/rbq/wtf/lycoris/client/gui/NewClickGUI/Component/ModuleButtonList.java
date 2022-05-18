package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.gui.NewClickGUI.NewClickGUI;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.*;
import scala.reflect.internal.Trees;

import java.awt.*;

public class ModuleButtonList extends Component{
    public ModuleCategory category;
    private float x;
    private float y;
    public ModuleButtonList(ModuleCategory Category) {
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
        for (Module module : LycorisClient.instance.getModuleManager().getModulesInType(category) ) {
            if (module.isState()) {
                FontLoaders.default20.drawString(module.getName(),
                        x,
                        y + moduleY,
                        new Color(87,72,216).getRGB());
            } else {
                FontLoaders.default20.drawString(module.getName(),
                        x,
                        y + moduleY,
                        new Color(255,255,255).getRGB());
            }
            moduleY = moduleY + FontLoaders.default20.getHeight() + 15;
        }
        this.setHeight(moduleY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        float moduleY = 0;
        for (Module module : LycorisClient.instance.getModuleManager().getModulesInType(category) ) {
            if (this.isHovered(
                    x,
                    y + moduleY  ,
                    x + 5 + FontLoaders.default20.getStringWidth(module.getName()),
                    y + 5 + moduleY + FontLoaders.default20.getHeight(),
                    mouseX,
                    mouseY
            )) {
                switch (mouseButton){
                    case 0 :
                        module.toggle();
                        break;
                    case 1 :
                        NewClickGUI.currentModule = module;
                        NewClickGUI.valueWheel = 0;
                        NewClickGUI.valueComponentList.clear();
                        NewClickGUI.currentActiveTextValue = null;
                        for (Value<?> value: module.getValues()) {
                            if (value instanceof BooleanValue) {
                                NewClickGUI.valueComponentList.add(new BooleanValueComponent((BooleanValue) value));
                            }
                            if (value instanceof NumberValue) {
                                NewClickGUI.valueComponentList.add(new NumberValueComponent((NumberValue) value));
                            }
                            if (value instanceof ModeValue) {
                                NewClickGUI.valueComponentList.add(new ModeValueComponent((ModeValue) value));
                            }
                            if (value instanceof ColorValue) {
                                NewClickGUI.valueComponentList.add(new ColorValueComponent((ColorValue) value));
                            }
                            if (value instanceof TextValue) {
                                NewClickGUI.valueComponentList.add(new TextValueComponent((TextValue) value));
                            }
                        }
                        NewClickGUI.valueComponentList.add(new ModuleBindComponent(module));
                        break;
                    default:
                        break;
                }
            }

            moduleY = moduleY + FontLoaders.default20.getHeight() + 15;
        }

    }
}
