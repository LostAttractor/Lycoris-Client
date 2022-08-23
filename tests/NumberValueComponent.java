package rbq.wtf.lycoris.client.gui.clickgui.component;

import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI;
import rbq.wtf.lycoris.client.gui.clickgui.utils.RenderUtil;
import rbq.wtf.lycoris.client.font.FontLoaders;
import rbq.wtf.lycoris.client.value.NumberValue;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberValueComponent extends Component {
    public NumberValue value;
    private float x;
    private float y;

    public NumberValueComponent(NumberValue value, ClickGUI clickGUI) {
        super(clickGUI);
        this.value = value;
        this.setHeight(45);
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();

        if (this.isHovered(x + 10, y + 20, x + 300, y + 30, mouseX, mouseY) && Mouse.isButtonDown(0)) {
            float current = (((mouseX - (x + 10)) / 295.0F) * (value.getMaximum() - value.getMinimum())) + value.getMinimum();

            if (value.getIncrease() >= 1) {
                value.set(round(current, 0));
            } else {
                String str = String.valueOf(value.getIncrease());
                value.set(round(current, str.length() - (str.indexOf(".") + 1)));
            }
        }
    }

    @Override
    public void render() {
        FontLoaders.default20.drawString(value.getName() + "[" + value.get() + "]",
                x,
                y,
                new Color(255, 255, 255).getRGB());
        float currentX = 295 * ((value.get() - value.getMinimum()) / (value.getMaximum() - value.getMinimum()));

        RenderUtil.drawFilledCircle(x + 5, y + 25, 6,
                new Color(45, 37, 104).getRGB(), 5);
        RenderUtil.drawRect(x + 6, y + 19, x + 295 + 11,
                y + 31,
                new Color(45, 36, 104).getRGB());
        RenderUtil.drawFilledCircle(x + 305, y + 25, 6,
                new Color(45, 37, 104).getRGB(), 5);

        RenderUtil.drawFilledCircle(x + 5, y + 25, 5,
                new Color(45, 45, 45).getRGB(), 5);
        RenderUtil.drawRect(x + 5, y + 20, x + 295 + 10,
                y + 30,
                new Color(45, 45, 45).getRGB());
        RenderUtil.drawFilledCircle(x + 305, y + 25, 5,
                new Color(45, 45, 45).getRGB(), 5);

        RenderUtil.drawFilledCircle(x + 5, y + 25, 6,
                new Color(97, 79, 237).getRGB(), 5);
        RenderUtil.drawRect(x + 10 - 4, y + 19, (float) (x + 10 + currentX),
                y + 31,
                new Color(97, 79, 237).getRGB());
        RenderUtil.drawFilledCircle(x + 10 + currentX, y + 25, 8,
                new Color(97, 79, 237).getRGB(), 5);

    }

    private float round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
