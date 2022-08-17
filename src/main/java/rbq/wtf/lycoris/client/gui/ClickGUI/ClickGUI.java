package rbq.wtf.lycoris.client.gui.ClickGUI;


import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.gui.ClickGUI.Component.CategoryButton;
import rbq.wtf.lycoris.client.gui.ClickGUI.Component.Component;
import rbq.wtf.lycoris.client.gui.ClickGUI.Component.ModuleButtonList;
import rbq.wtf.lycoris.client.gui.ClickGUI.Utils.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.wrapper.wrappers.impl.GuiScreenImpl;

import java.awt.*;
import java.util.ArrayList;


public class ClickGUI extends GuiScreenImpl {
    public static ModuleCategory currentModuleType;
    public static Module currentModule;
    public static Module currentBindModule;
    public static Component currentActiveTextValue;
    public static float moduleWheel = 0.0F;
    public static float valueWheel = 0.0F;
    public static float startX = 100;
    public static float startY = 100;
    public float moveX = 0.0F;
    public float moveY = 0.0F;
    public ArrayList<CategoryButton> categoryButtonList = new ArrayList<CategoryButton>();
    public ArrayList<ModuleButtonList> moduleButtonList = new ArrayList<ModuleButtonList>();
    public static ArrayList<Component> valueComponentList = new ArrayList<Component>();

    public ClickGUI() {
        currentModuleType = ModuleCategory.Combat;
        for (ModuleCategory c : ModuleCategory.values()) {
            categoryButtonList.add(new CategoryButton(c));
            moduleButtonList.add(new ModuleButtonList(c));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        if (this.isHovered(startX, startY - 10.0F, startX + 400.0F, startY + 15.0F, mouseX, mouseY)
                && Mouse.isButtonDown(0)) {
            if (this.moveX == 0.0F && this.moveY == 0.0F) {
                this.moveX = (float) mouseX - startX;
                this.moveY = (float) mouseY - startY;
            } else {
                startX = (float) mouseX - this.moveX;
                startY = (float) mouseY - this.moveY;
            }

        } else if (this.moveX != 0.0F || this.moveY != 0.0F) {
            this.moveX = 0.0F;
            this.moveY = 0.0F;
        }

        //draw bg
        RenderUtil.drawFastRoundedRect(startX - 1, startY - 1, startX + 445 + 1,
                startY + 327 + 1, 5, new Color(15, 15, 15, 33).getRGB());
        RenderUtil.drawFastRoundedRect(startX - 1.5f, startY - 1.5f, startX + 445 + 1.5f,
                startY + 327 + 1.5f, 5, new Color(15, 15, 15, 31).getRGB());
        RenderUtil.drawFastRoundedRect(startX - 2, startY - 2, startX + 445 + 2,
                startY + 327 + 2, 5, new Color(15, 15, 15, 29).getRGB());
        RenderUtil.drawFastRoundedRect(startX, startY, startX + 445,
                startY + 327, 5, new Color(15, 15, 15).getRGB());
        RenderUtil.drawHLine(startX, startY + 35, startX + 445, startY + 35, 4,
                new Color(69, 78, 238).getRGB());
        RenderUtil.drawHLine(startX + 110, startY + 35, startX + 110, startY + 327, 4,
                new Color(69, 78, 238).getRGB());
        RenderUtil.drawHLine(startX + 107, startY + 36, startX + 107, startY + 327, 4,
                new Color(38, 38, 38).getRGB());
        RenderUtil.drawHLine(startX + 107, startY + 37, startX + 107, startY + 220, 2,
                new Color(87, 87, 87).getRGB());
        //DrawClientName
        FontLoaders.default15.drawStringWithShadow("L",
                startX + 403,
                startY + 317,
                new Color(96, 78, 238).getRGB());
        FontLoaders.default15.drawStringWithShadow("ycoris 2.0",
                startX + 403 + FontLoaders.default15.getStringWidth("L"),
                startY + 317,
                new Color(255, 255, 255).getRGB());
        //DrawCategory
        float categoryX = 0;
        for (CategoryButton c : categoryButtonList) {
            c.updateComponent(
                    startX + 14 + categoryX,
                    startY + 14,
                    mouseX,
                    mouseY
            );
            categoryX = categoryX + c.getHeight() + 15;
        }

        //DrawModules
        RenderUtil.startGlScissor((int) (ClickGUI.startX), (int) (ClickGUI.startY + 50.0F), 445, (int) 277);

        float moduleY = startY + 45;
        for (ModuleButtonList list : moduleButtonList) {
            if (list.category == currentModuleType) {
                list.updateComponent(startX + 13,
                        startY + 45 + moduleWheel,
                        mouseX,
                        mouseY
                );
            }
            moduleY = mouseY + list.getHeight();
        }

        //Modules Mouse Wheel
        int mouseWheel = Mouse.getDWheel();
        if (this.isHovered(startX + 1.0F, startY + 40.0F, startX + 100.0F, startY + 327.0F, mouseX, mouseY)) {
            if (mouseWheel < 0 && moduleY + moduleWheel - startY - 70 >= 260) {
                moduleWheel = moduleWheel - 7;
            }
            if (mouseWheel > 0 && moduleWheel != 0) {
                moduleWheel = moduleWheel + 7;
            }
        }

        //DrawValues
        float valueY = startY + 45;
        for (Component component : valueComponentList) {
            component.updateComponent(
                    startX + 120,
                    valueWheel + valueY,
                    mouseX,
                    mouseY
            );
            valueY += component.getHeight();
        }
        RenderUtil.stopGlScissor();
        if (this.isHovered(startX + 110, startY + 35.0F, startX + 440.0F, startY + 330.0F, mouseX, mouseY)) {
            if (mouseWheel < 0 && valueY + valueWheel - startY > 325) {
                valueWheel = valueWheel - 7;
            }

            if (mouseWheel > 0 && valueWheel != 0) {
                valueWheel = valueWheel + 7;
            }
        }


    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        System.out.println(mouseX);
        System.out.println(mouseY);
        System.out.println(mouseX - startX);
        System.out.println(mouseY - startY);
        //Module Click
        if (this.isHovered(startX + 1.0F, startY + 40.0F, startX + 100.0F, startY + 327.0F, mouseX, mouseY)) {
            for (ModuleButtonList list : moduleButtonList) {
                if (list.category == currentModuleType) {
                    list.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
        //Value Click
        if (this.isHovered(startX + 110, startY + 35.0F, startX + 440.0F, startY + 330.0F, mouseX, mouseY)) {
            float valueY = startY + 50;
            if (currentModule != null) {
                for (Component component : valueComponentList) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                    valueY += component.getHeight();
                }

            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.isHovered(startX + 110, startY + 35.0F, startX + 440.0F, startY + 330.0F, mouseX, mouseY)) {
            float valueY = startY + 50;
            if (currentModule != null) {
                for (Component component : valueComponentList) {
                    component.mouseReleased(state);
                    valueY += component.getHeight();
                }

            }
        }
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        if (currentActiveTextValue != null) {
            currentActiveTextValue.keyTyped(typedChar, keyCode);
        }
        return true;
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }
}
