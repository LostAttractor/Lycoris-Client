package rbq.wtf.lycoris.client.gui.NewClickGUI;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.gui.ClickGUI.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.*;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class NewClickGUI extends GuiScreen {
    public static ModuleCategory currentModuleType;
    public static Module currentModule;
    public static Module currentBindModule;
    public static float moduleWheel = 0.0F;
    public static float valueWheel = 0.0F;
    public static float startX = 100;
    public static float startY = 100;
    public float moveX = 0.0F;
    public float moveY = 0.0F;
    public NewClickGUI() {
        currentModuleType = ModuleCategory.Combat;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
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
        RenderUtil.drawFastRoundedRect( startX - 1 , startY - 1 , startX + 445 + 1,
                startY + 327 + 1,5,new Color( 15, 15, 15,33).getRGB());
        RenderUtil.drawFastRoundedRect( startX - 1.5f , startY - 1.5f , startX + 445 + 1.5f,
                startY + 327 + 1.5f,5,new Color( 15, 15, 15,31).getRGB());
        RenderUtil.drawFastRoundedRect( startX - 2 , startY - 2 , startX + 445 + 2,
                startY + 327 + 2,5,new Color( 15, 15, 15,29).getRGB());
        RenderUtil.drawFastRoundedRect( startX , startY , startX + 445,
                startY + 327,5,new Color( 15, 15, 15).getRGB());
        RenderUtil.drawHLine(startX,startY + 35,startX + 445,startY + 35,4,
                new Color(69,78,238).getRGB());
        RenderUtil.drawHLine(startX + 110,startY + 35,startX + 110,startY + 327,4,
                new Color(69,78,238).getRGB());
        RenderUtil.drawHLine(startX + 107,startY + 36,startX + 107,startY + 327,4,
                new Color(38, 38, 38).getRGB());
        RenderUtil.drawHLine(startX + 107,startY + 37,startX + 107,startY + 220,2,
                new Color(87, 87, 87).getRGB());
        //DrawCategory
        float categoryX = 0;
        for (int moduleIndex = 0; moduleIndex < ModuleCategory.values().length; ++moduleIndex) {
            ModuleCategory[] categories = ModuleCategory.values();
            if (categories[moduleIndex] != currentModuleType) {
                FontLoaders.default20.drawString(categories[moduleIndex].toString(),
                        (startX + 14 + categoryX),
                        (startY + 14),
                        new Color(255, 255, 255).getRGB());
            } else {
                FontLoaders.default20.drawString(categories[moduleIndex].toString(),
                        (startX + 14 + categoryX),
                        (startY + 14 ),
                        new Color(101,81,255).getRGB());
            }
            if (this.isHovered(
                    startX + 10 + categoryX,
                    startY + 10 ,
                    startX + 18 + categoryX + FontLoaders.default20.getStringWidth(categories[moduleIndex].toString()),
                    startY + 18 + FontLoaders.default20.getHeight(),
                    mouseX,
                    mouseY
            ) && Mouse.isButtonDown(0)) {
                currentModuleType = categories[moduleIndex];
                currentModule = null;
                moduleWheel = 0;
                valueWheel = 0;
            }
            categoryX = categoryX + FontLoaders.default20.getStringWidth(categories[moduleIndex].toString())+15;
        }

        //DrawModules
        RenderUtil.startGlScissor((int) (startX ), (int) (startY + 50.0F),445, (int)277);
        float moduleY = startY + 45;
        for (int moduleIndex = 0; moduleIndex < LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).size(); ++moduleIndex) {
            Module module = LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).get(moduleIndex);
            if (module.isState()) {
                FontLoaders.default20.drawString(module.getName(),
                        startX + 13,
                        moduleY + moduleWheel,
                        new Color(87,72,216).getRGB());
            } else {
                FontLoaders.default20.drawString(module.getName(),
                        startX + 13,
                        moduleY + moduleWheel,
                        new Color(255,255,255).getRGB());
            }
            moduleY = moduleY + FontLoaders.default20.getHeight() + 15;
        }
        //Modules Mouse Wheel
        int mouseWheel = Mouse.getDWheel();
        if (this.isHovered(startX + 1.0F, startY + 40.0F, startX + 100.0F, startY + 327.0F, mouseX, mouseY)) {
            if (mouseWheel < 0  && moduleY + moduleWheel - startY - 70 >= 260) {
                moduleWheel = moduleWheel - 7;
            }

            if (mouseWheel > 0 && moduleWheel != 0) {

                moduleWheel = moduleWheel + 7;
            }
        }
        //DrawValues
        float valueY = startY + 45;
        if (currentModule != null) {
            for (int valueIndex = 0; valueIndex < currentModule.getValues().size(); ++valueIndex){
                Value value = currentModule.getValues().get(valueIndex);
                if (value instanceof BooleanValue) {
                    BooleanValue booleanValue = (BooleanValue) value;
                    if (booleanValue.getValue()) {
                        //Draw Switch Background

                        RenderUtil.drawFilledCircle(startX + 415, valueWheel + valueY + 10, 6,
                                new Color(79,66,184).getRGB(),5);
                        RenderUtil.drawRect(startX + 415 - 1, valueWheel + valueY + 4, startX + 415 + 10,
                                valueWheel + valueY + 16,
                                new Color(79,66,184).getRGB());
                        RenderUtil.drawFilledCircle(startX + 425 - 1, valueWheel + valueY + 10, 6,
                                new Color(79,66,184).getRGB(),5);

                        RenderUtil.drawFilledCircle(startX + 415, valueWheel + valueY + 10, 5,
                                new Color(26,16,46).getRGB(),5);
                        RenderUtil.drawRect(startX + 415 - 1, valueWheel + valueY + 5, startX + 415 + 10,
                                valueWheel + valueY + 15,
                                new Color(26,16,46).getRGB());
                        RenderUtil.drawFilledCircle(startX + 425 - 1, valueWheel + valueY + 10, 5,
                                new Color(26,16,46).getRGB(),5);

                        RenderUtil.drawFilledCircle(startX + 424, valueWheel + valueY + 10, 3,
                                new Color(98,96,207).getRGB(),5);
                    } else {
                        //Draw Switch Background
                        RenderUtil.drawFilledCircle(startX + 415, valueWheel + valueY + 10, 6,
                                new Color(83,69,193).getRGB(),5);
                        RenderUtil.drawRect(startX + 415 - 1, valueWheel + valueY + 4, startX + 415 + 10,
                                valueWheel + valueY + 16,
                                new Color(83,69,193).getRGB());
                        RenderUtil.drawFilledCircle(startX + 425 - 1, valueWheel + valueY + 10, 6,
                                new Color(83,69,193).getRGB(),5);

                        RenderUtil.drawFilledCircle(startX + 415, valueWheel + valueY + 10, 5,
                                new Color(25,25,25).getRGB(),5);
                        RenderUtil.drawRect(startX + 415 - 1, valueWheel + valueY + 5, startX + 415 + 10,
                                valueWheel + valueY + 15,
                                new Color(25,25,25).getRGB());
                        RenderUtil.drawFilledCircle(startX + 425 - 1, valueWheel + valueY + 10, 5,
                                new Color(25,25,25).getRGB(),5);

                        RenderUtil.drawFilledCircle(startX + 415, valueWheel + valueY + 10, 3,
                                new Color(80,81,81).getRGB(),5);
                    }

                    FontLoaders.default20.drawString(value.getName(),
                            startX + 120,
                            valueWheel + valueY,
                            new Color(255,255,255).getRGB());

                    valueY+=25;
                }
                if (value instanceof ModeValue) {
                    ModeValue modeValue = (ModeValue) value;
                    FontLoaders.default20.drawString(modeValue.getName(),
                            startX + 120,
                            valueWheel + valueY,
                            new Color(255,255,255).getRGB());
                    RenderUtil.drawFastRoundedRect(startX + 340,
                            valueWheel + valueY + 10,
                            startX + 430,
                            valueWheel + valueY + 30,
                            2,
                            new Color(83,69,193).getRGB()
                            );
                    RenderUtil.drawFastRoundedRect(startX + 340 + 1,
                            valueWheel + valueY + 10 + 1,
                            startX + 430 - 1,
                            valueWheel + valueY + 30 - 1,
                            2,
                            new Color(27,27,27).getRGB()
                    );
                    FontLoaders.default20.drawCenteredString(modeValue.getCurrentSelectionName(),
                            startX + 385,
                            valueWheel + valueY + 16,
                            new Color(255,255,255).getRGB()
                    );
                    valueY+=35;
                }
                if (value instanceof NumberValue) {
                    NumberValue numberValue = (NumberValue) value;
                    FontLoaders.default20.drawString(value.getName() + "["+numberValue.getValue()+"]",
                            startX + 120,
                            valueWheel + valueY,
                            new Color(255,255,255).getRGB());

                    double currentX = 295 * ((numberValue.getValue() - numberValue.getMin())/(numberValue.getMax() - numberValue.getMin()));

                    RenderUtil.drawFilledCircle(startX + 125, valueWheel + valueY + 25, 6,
                            new Color(45,37,104).getRGB(),5);
                    RenderUtil.drawRect(startX + 126, valueWheel + valueY + 19, startX + 415 + 11,
                            valueWheel + valueY + 31,
                            new Color(45,36,104).getRGB());
                    RenderUtil.drawFilledCircle(startX + 425, valueWheel + valueY + 25, 6,
                            new Color(45,37,104).getRGB(),5);

                    RenderUtil.drawFilledCircle(startX + 125, valueWheel + valueY + 25, 5,
                            new Color(45,45,45).getRGB(),5);
                    RenderUtil.drawRect(startX + 125, valueWheel + valueY + 20, startX + 415 + 10,
                            valueWheel + valueY + 30,
                            new Color(45,45,45).getRGB());
                    RenderUtil.drawFilledCircle(startX + 425, valueWheel + valueY + 25, 5,
                            new Color(45,45,45).getRGB(),5);

                    RenderUtil.drawFilledCircle(startX + 125, valueWheel + valueY + 25, 6,
                            new Color(97,79,237).getRGB(),5);
                    RenderUtil.drawRect(startX + 126, valueWheel + valueY + 19, (float)(startX + 130 + currentX),
                            valueWheel + valueY + 31,
                            new Color(97,79,237).getRGB());
                    RenderUtil.drawFilledCircle(startX + 130 + currentX, valueWheel + valueY  + 25, 8,
                            new Color(97,79,237).getRGB(),5);

                    if (this.isHovered(startX + 130,
                            valueWheel + valueY + 20,
                            startX + 425,
                            valueWheel + valueY + 30,
                            mouseX,
                            mouseY) && Mouse.isButtonDown(0)){
                        float current = (((mouseX - (startX + 130.0F)) / 295.0F) * (numberValue.getMax() - numberValue.getMin()))+numberValue.getMin();

                        if (numberValue.getIncrease() >= 1){
                            numberValue.setValue((float) round(current,0));

                        } else {
                            String str = "" + numberValue.getIncrease();
                            numberValue.setValue((float) round(current,str.length() - (str.indexOf(".") + 1)));
                        }
                    }
                    valueY+=40;
                }


            }
            if (currentBindModule != null) {
                FontLoaders.default18.drawStringWithShadow("Bind to" ,
                        startX + 120,
                        valueWheel + valueY,
                        -1);
            } else {
                FontLoaders.default18.drawStringWithShadow("Bind:" + Keyboard.getKeyName(currentModule.getKey()),
                        startX + 120,
                        valueWheel + valueY,
                        -1);
            }
            valueY+=15;
            if (this.isHovered(startX + 110, startY + 35.0F, startX + 440.0F, startY + 330.0F, mouseX, mouseY)){
                if (mouseWheel < 0  && valueY + valueWheel - startY > 325) {
                    valueWheel = valueWheel - 7;
                }

                if (mouseWheel > 0 && valueWheel != 0) {
                    valueWheel = valueWheel + 7;
                }
            }

        }
        RenderUtil.stopGlScissor();
        //DrawClientName
        FontLoaders.default15.drawStringWithShadow("L",
                startX + 403 ,
                startY + 317 ,
                new Color(96,78,238).getRGB());
        FontLoaders.default15.drawStringWithShadow("ycoris 2.0",
                startX + 403 + FontLoaders.default15.getStringWidth("L"),
                startY + 317 ,
                new Color(255,255,255).getRGB());
    }
    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        System.out.println(mouseX);
        System.out.println(mouseY);
        System.out.println(mouseX - startX);
        System.out.println(mouseY - startY);
        //Module Click
        if (this.isHovered(startX + 1.0F, startY + 40.0F, startX + 100.0F, startY + 327.0F, mouseX, mouseY)){
            float moduleY = startY + 45;
            for (int moduleIndex = 0; moduleIndex < LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).size(); ++moduleIndex) {
                Module module = LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).get(moduleIndex);
                if (this.isHovered(
                        startX + 13,
                        moduleY + moduleWheel ,
                        startX + 18 + FontLoaders.default20.getStringWidth(module.getName()),
                        5 + moduleY + FontLoaders.default20.getHeight() + moduleWheel,
                        mouseX,
                        mouseY
                )) {
                    switch (mouseButton){
                        case 0 :
                            module.toggle();
                            break;
                        case 1 :
                            currentModule = module;
                            valueWheel = 0;
                            break;
                        default:
                            break;
                    }
                }

                moduleY = moduleY + FontLoaders.default20.getHeight() + 15;
            }
        }
        //Value Click
        if (this.isHovered(startX + 110, startY + 35.0F, startX + 440.0F, startY + 330.0F, mouseX, mouseY)){
            float valueY = startY + 50;
            if (currentModule != null) {
                for (int valueIndex = 0; valueIndex < currentModule.getValues().size(); ++valueIndex){
                    Value value = currentModule.getValues().get(valueIndex);
                    if (value instanceof BooleanValue) {
                        BooleanValue booleanValue = (BooleanValue) value;
                        if (this.isHovered(startX + 415 - 5, valueWheel + valueY + 3, startX + 415 + 10,
                                valueWheel + valueY + 17,mouseX,mouseY)) {
                            booleanValue.setValue(!booleanValue.getValue());
                        }
                        valueY+=25;
                    }
                    if (value instanceof ModeValue) {
                        ModeValue modeValue = (ModeValue) value;
                        if (this.isHovered(startX + 340,
                                valueWheel + valueY + 10,
                                startX + 430,
                                valueWheel + valueY + 30,
                                mouseX,
                                mouseY)){
                            modeValue.incrementSelection();
                        }
                        valueY+=35;
                    }
                    if (value instanceof NumberValue) {
                        valueY+=40;
                    }
                }
                if (this.isHovered(startX + 120,
                        valueWheel + valueY - 5.0F,
                        startX + 120 + FontLoaders.default18.getStringWidth("Bind:" + Keyboard.getKeyName(currentModule.getKey())),
                        valueWheel + valueY + 5.0F,
                        mouseX,
                        mouseY)) {
                    if (Mouse.isButtonDown(0)) {
                        if (currentBindModule == null) {
                            currentBindModule = currentModule;
                        } else {
                            currentBindModule = null;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (currentBindModule == null) {
            return;
        } else {
            currentBindModule.setKey(keyCode);
            currentBindModule = null;
        }
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }
}
