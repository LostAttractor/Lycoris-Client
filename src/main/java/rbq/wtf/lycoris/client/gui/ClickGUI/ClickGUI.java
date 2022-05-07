package rbq.wtf.lycoris.client.gui.ClickGUI;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.ResourceLocation;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.gui.ClickGUI.Opacity;
import rbq.wtf.lycoris.client.gui.ClickGUI.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.value.Value;

import javax.swing.*;
public class ClickGUI extends GuiScreen{

    public static ModuleCategory currentModuleType;
    public static Module currentModule;
    public static Module currentBindModule;
    public static float startX;
    public static float startY;
    public int moduleStart = 0;
    public int valueStart = 0;
    boolean previousmouse = true;
    boolean mouse;
    public Opacity opacity = new Opacity(0);
    public int opacityx = 255;
    public float moveX = 0.0F;
    public float moveY = 0.0F;

    public float lastPercent;
    public float percent;
    public float percent2;
    public float lastPercent2;

    public float outro;
    public float lastOutro;

    public int mouseWheel;

    public int mouseX;
    public int mouseY;
    boolean initialized;


    public ClickGUI(){
        currentBindModule = null;
        currentModuleType = ModuleCategory.Combat;
        currentModule = LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).size() != 0
                ? LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).get(0)
                : null;
        startX = 100.0F;
        startY = 100.0F;
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


    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        JOptionPane.showConfirmDialog(null,"2","DXPSL", JOptionPane.PLAIN_MESSAGE);
//        if (!initialized) {
//            new Connection();
//            initialized = true;
//        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (mc.currentScreen != null)
            if (!(mc.currentScreen instanceof net.minecraft.client.gui.GuiMainScreen)) {
                lastOutro = outro;
                if (outro < 1.7) {
                    outro += 0.1f;

                    outro += ((1.7 - outro) / (3f)) - 0.001;
                }
                if (outro > 1.7) {
                    outro = 1.7f;
                }
                if (outro < 1) {
                    outro = 1;
                }
            }
        if (mc.currentScreen != null)
            if ((mc.currentScreen != null) && !(mc.currentScreen instanceof ClickGUI))
                return;
        lastPercent = percent;
        lastPercent2 = percent2;
        if (percent > .98) {
            percent += ((.98 - percent) / (1.45f)) - 0.001;
        }
        if (percent <= .98) {
            if (percent2 < 1) {
                percent2 += ((1 - percent2) / (2.8f)) + 0.002;
            }
        }
        if (this.isHovered(startX, startY - 25.0F, startX + 400.0F, startY + 25.0F, mouseX, mouseY)
                && Mouse.isButtonDown(0)) {
            if (this.moveX == 0.0F && this.moveY == 0.0F) {
                this.moveX = (float) mouseX - startX;
                this.moveY = (float) mouseY - startY;
            } else {
                startX = (float) mouseX - this.moveX;
                startY = (float) mouseY - this.moveY;
            }

            this.previousmouse = true;
        } else if (this.moveX != 0.0F || this.moveY != 0.0F) {
            this.moveX = 0.0F;
            this.moveY = 0.0F;
        }

        this.opacity.interpolate((float) this.opacityx);
        //DrawMainWindow
        RenderUtil.drawFastRoundedRect(startX, startY, startX + 80.0F, startY + 320.0F,4,
                (new Color(40, 40, 40, (int) this.opacity.getOpacity())).getRGB());
        RenderUtil.drawRect(startX + 56.0F, startY, startX + 80.0F, startY + 320.0F,
                (new Color(40, 40, 40, (int) this.opacity.getOpacity())).getRGB());
        RenderUtil.drawRect(startX + 80.0F, startY, startX + 220.0F, startY + 320.0F,
                (new Color(31, 31, 31, (int) this.opacity.getOpacity())).getRGB());
        RenderUtil.drawRect(startX + 220.0F, startY, startX + 224.0F, startY + 320.0F,
                (new Color(40, 40, 40, (int) this.opacity.getOpacity())).getRGB());
        RenderUtil.drawFastRoundedRect(startX + 220.0F, startY, startX + 440.0F, startY + 320.0F,4,
                (new Color(40, 40, 40, (int) this.opacity.getOpacity())).getRGB());
        //DrawCategory
        int m;
        for (m = 0; m < ModuleCategory.values().length; ++m) {
            ModuleCategory[] mY = ModuleCategory.values();
            if (mY[m] != currentModuleType) {
                FontLoaders.default18.drawCenteredString(mY[m].toString(),
                        (startX + 40.0F),
                        (startY + 30.0F + (float) (m * 40)),
                        new Color(255, 255, 255).getRGB());


            } else {
                FontLoaders.default18.drawCenteredString(mY[m].toString(),
                        (startX + 40.0F),
                        (startY + 30.0F + (float) (m * 40)),
                        new Color(101,81,255).getRGB());
            }

            try {
                if (this.isCategoryHovered(startX + 15.0F, startY + 15.0F + (float) (m * 40), startX + 45.0F,
                        startY + 45.0F + (float) (m * 40), mouseX, mouseY) && Mouse.isButtonDown(0)) {
                    currentModuleType = mY[m];
                    currentModule = LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).size() != 0
                            ? (Module) LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).get(0)
                            : null;
                    this.moduleStart = 0;
                }
            } catch (Exception var23) {
                System.err.println(var23);
            }
        }

        mouseWheel = Mouse.getDWheel();
        if (this.isCategoryHovered(startX + 60.0F, startY, startX + 200.0F, startY + 320.0F, mouseX, mouseY)) {
            if (mouseWheel < 0 && this.moduleStart < LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).size() - 1) {
                ++this.moduleStart;
            }

            if (mouseWheel > 0 && this.moduleStart > 0) {
                --this.moduleStart;
            }
        }

        if (this.isCategoryHovered(startX + 200.0F, startY, startX + 420.0F, startY + 320.0F, mouseX, mouseY)) {
            if (mouseWheel < 0 && this.valueStart < currentModule.getValues().size() - 1) {
                ++this.valueStart;
            }

            if (mouseWheel > 0 && this.valueStart > 0) {
                --this.valueStart;
            }
        }

        FontLoaders.default18.drawString(
                currentModuleType.toString(),
                startX + 90.0F, startY + 15.0F, (new Color(248, 248, 248)).getRGB());
        if (currentModule != null) {
            float valueY = startY + 30.0F;
            //DrawModule
            int i;
            for (i = 0; i < LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).size(); ++i) {
                Module value = (Module) LycorisClient.instance.getModuleManager().getModulesInType(currentModuleType).get(i);
                if (valueY > startY + 300.0F) {
                    break;
                }
                //DrawValue
                if (i >= this.moduleStart) {
                    RenderUtil.drawRect(startX + 95.0F, valueY, startX + 205.0F, valueY + 2.0F,
                            (new Color(40, 40, 40, (int) this.opacity.getOpacity())).getRGB());
                    FontLoaders.default18.drawString(value.getName(), startX + 110.0F, valueY + 10.0F,
                            (new Color(248, 248, 248, (int) this.opacity.getOpacity())).getRGB());
                    if (!value.isState()) {
                        RenderUtil.drawFilledCircle((double) (startX + 95.0F), (double) (valueY + 13.0F), 2.0D,
                                (new Color(255, 0, 0, (int) this.opacity.getOpacity())).getRGB(), 5);
                    } else {
                        RenderUtil.drawFilledCircle((double) (startX + 95.0F), (double) (valueY + 13.0F), 2.0D,
                                (new Color(0, 255, 0, (int) this.opacity.getOpacity())).getRGB(), 5);
                    }
                    if(value == currentModule)
                    {
                        FontLoaders.default18.drawStringWithShadow("->",  (startX + 66.0F + 120.0f), (valueY + 10.0F), -1);
                    }
                    else if(!value.getValues().isEmpty())
                    {
                        FontLoaders.default18.drawStringWithShadow("+",  (startX + 70.0F + 120.0f), (valueY + 10.0F), -1);
                    }

                    if (this.isSettingsButtonHovered(startX + 110.0F, valueY,
                            startX + 140.0F + (float) FontLoaders.default20.getStringWidth(value.getName()),
                            valueY + 8.0F + 10, mouseX, mouseY)) {
                        if (!this.previousmouse && Mouse.isButtonDown(0)) {
                            if (value.isState()) {
                                value.setState(false);
                            } else {
                                value.setState(true);
                            }

                            this.previousmouse = true;
                        }

                        if (!this.previousmouse && Mouse.isButtonDown(1)) {
                            this.previousmouse = true;
                        }
                    }

                    if (!Mouse.isButtonDown(0)) {
                        this.previousmouse = false;
                    }

                    if (this.isSettingsButtonHovered(startX + 90.0F, valueY,
                            startX + 100.0F + (float) FontLoaders.default20.getStringWidth(value.getName()),
                            valueY + 8.0F + 10, mouseX, mouseY)
                            && Mouse.isButtonDown(1)) {
//                        if(!value.getValues().isEmpty())
//                        {
                            currentModule = value;
                            this.valueStart = 0;
//                        }
                    }

                    valueY += 25.0F;
                }
            }
            //绘制配置组件
            valueY = startY + 40.0F;
            FontLoaders.default18.drawStringWithShadow(currentModule.getName(),
                    startX + 232.0F, startY + 15.0f,-1 );
            for (i = 0; i < currentModule.getValues().size() && valueY <= startY + 300.0F; ++i) {
                if (i >= this.valueStart) {
                    Value value = (Value) currentModule.getValues().get(i);
                    float x;
                    if (value instanceof NumberValue) {
                        x = startX + 340.0F;
                        double current = (double) (80.0F
                                * (((Number) value.getValue()).floatValue()
                                - ((NumberValue) value).getMin())
                                / (((NumberValue) value).getMax()
                                - ((NumberValue) value).getMin()));
                        RenderUtil.drawRect(x-2.0F , valueY + 15.0F, (float) ((double) x + 85.0D), valueY + 17.0F,
                                (new Color(50, 50, 50, (int) this.opacity.getOpacity())).getRGB());
                        RenderUtil.drawRect(x-2.0F , valueY + 15.0F, (float) ((double) x + current + 6.5D), valueY + 17.0F,
                                (new Color(61, 141, 255, (int) this.opacity.getOpacity())).getRGB());
                        RenderUtil.drawRect((float) ((double) x + current + 2.0D), valueY + 13F,
                                (float) ((double) x + current + 7.0D), valueY + 5.0F +13F,
                                (new Color(61, 141, 255, (int) this.opacity.getOpacity())).getRGB());
                        FontLoaders.default20.drawStringWithShadow(value.getName() + ": " + value.getValue(),
                                startX + 230.0F, valueY, -1);
                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }

                        if (this.isButtonHovered(x, valueY + 5.0F - 5F, x + 120.0F, valueY + 7.0F + 13F, mouseX, mouseY)
                                && Mouse.isButtonDown(0)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                current = ((NumberValue) value).getMin();
                                double max = ((NumberValue) value).getMax();
                                double inc = ((NumberValue) value).getIncrease();
                                double valAbs = (double) mouseX - ((double) x + 1.0D);
                                double perc = valAbs / 68.0D;
                                perc = Math.min(Math.max(0.0D, perc), 1.0D);
                                double valRel = (max - current) * perc;
                                double val = current + valRel;
                                val = (double) Math.round(val * (1.0D / inc)) / (1.0D / inc);
                                ((NumberValue) value).setValue((float) val);
                            }

                            if (!Mouse.isButtonDown(0)) {
                                this.previousmouse = false;
                            }
                        }

                        valueY += 20.0F;
                    }

                    if (value instanceof BooleanValue) {
                        x = startX + 340.0F;

                        RenderUtil.drawRect(x + 56.0F, valueY, x + 76.0F, valueY + 1.0F,
                                (new Color(255, 255, 255, (int) this.opacity.getOpacity())).getRGB());
                        RenderUtil.drawRect(x + 56.0F, valueY + 8.0F, x + 76.0F, valueY + 9.0F,
                                (new Color(255, 255, 255, (int) this.opacity.getOpacity())).getRGB());
                        RenderUtil.drawRect(x + 56.0F, valueY, x + 57.0F, valueY + 9.0F,
                                (new Color(255, 255, 255, (int) this.opacity.getOpacity())).getRGB());
                        RenderUtil.drawRect(x + 77.0F, valueY, x + 76.0F, valueY + 9.0F,
                                (new Color(255, 255, 255, (int) this.opacity.getOpacity())).getRGB());
                        FontLoaders.default18.drawStringWithShadow(value.getName(), startX + 230.0F, valueY, -1);
                        if (((Boolean) value.getValue())) {
                            RenderUtil.drawRect(x + 67.0F, valueY + 2.0F, x + 75.0F, valueY + 7.0F,
                                    (new Color(61, 141, 255, (int) this.opacity.getOpacity())).getRGB());
                        } else {
                            RenderUtil.drawRect(x + 58.0F, valueY + 2.0F, x + 65.0F, valueY + 7.0F,
                                    (new Color(150, 150, 150, (int) this.opacity.getOpacity())).getRGB());
                        }
                        if (this.isCheckBoxHovered(x + 56.0F, valueY, x + 76.0F, valueY + 9.0F, mouseX, mouseY)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                this.previousmouse = true;
                                this.mouse = true;
                            }

                            if (this.mouse) {
                                value.setValue(!(boolean)value.getValue());
                                this.mouse = false;
                            }
                        }

                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }

                        valueY += 20.0F;
                    }

                    if (value instanceof ModeValue) {
                        x = startX + 340.0F;
                        RenderUtil.drawRect(x - 5.0F, valueY - 5.0F, x + 90.0F, valueY + 15.0F,
                                (new Color(56, 56, 56, (int) this.opacity.getOpacity())).getRGB());
                        RenderUtil.drawBorderRect((double) (x - 5.0F), (double) (valueY - 5.0F), (double) (x + 90.0F),
                                (double) (valueY + 15.0F),
                                (new Color(101, 81, 255, (int) this.opacity.getOpacity())).getRGB(), 2.0D);
                        FontLoaders.default18.drawStringWithShadow(value.getName(), startX + 230.0F, valueY + 2, -1);

                        FontLoaders.default18
                                .drawStringWithShadow(((ModeValue)value).getCurrentSelectionName(),
                                        x + 44.0F
                                                - (float) (FontLoaders.default18
                                                .getStringWidth(((ModeValue)value).getCurrentSelectionName()) / 2),
                                        valueY+2, -1);
                        if (this.isStringHovered(x, valueY - 5.0F, x + 100.0F, valueY + 15.0F, mouseX, mouseY)) {
                            if (Mouse.isButtonDown(0) && !this.previousmouse) {
                                ((ModeValue) value).incrementSelection();
                                this.previousmouse = true;
                            }

                            if (!Mouse.isButtonDown(0)) {
                                this.previousmouse = false;
                            }
                        }

                        valueY += 25.0F;
                    }
                }
            }



            if (currentBindModule != null) {
                FontLoaders.default18.drawStringWithShadow("Bind to" , startX + 232.0F, valueY + 10, -1);
            } else {
                FontLoaders.default18.drawStringWithShadow("Bind:" + Keyboard.getKeyName(currentModule.getKey()), startX + 232.0F, valueY + 10, -1);
            }
            if (this.isHovered(startX + 210.0F, valueY + 7,
                    startX + 230 + FontLoaders.default18.getStringWidth("Bind:" + Keyboard.getKeyName(currentModule.getKey()))
                    , valueY + 15.0F, mouseX, mouseY)) {
                if (Mouse.isButtonDown(0)) {
                    currentBindModule = currentModule;
                }
            }
        }
    }



    public boolean isStringHovered(float f, float y, float g, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= f && (float) mouseX <= g && (float) mouseY >= y && (float) mouseY <= y2;
    }

    public boolean isSettingsButtonHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }

    public boolean isButtonHovered(float f, float y, float g, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= f && (float) mouseX <= g && (float) mouseY >= y && (float) mouseY <= y2;
    }

    public boolean isCheckBoxHovered(float f, float y, float g, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= f && (float) mouseX <= g && (float) mouseY >= y && (float) mouseY <= y2;
    }

    public boolean isCategoryHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }



    public void onGuiClosed() {
        //this.opacity.setOpacity(0.0F);
        currentBindModule = null;
    }
}
