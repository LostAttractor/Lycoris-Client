package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.gui.ClickGUI.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.value.NumberValue;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberValueComponent extends Component{
    public NumberValue Value;
    private float x;
    private float y;
    public NumberValueComponent(NumberValue value) {
        this.Value = value;
        this.setHeight(45);
    }
    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();

        if (this.isHovered(x + 10 ,
                y + 20,
                x + 300,
                y + 30,
                mouseX,
                mouseY) && Mouse.isButtonDown(0)){
            float current = (((mouseX - (x + 10)) / 295.0F) * (Value.getMax() - Value.getMin()))+Value.getMin();

            if (Value.getIncrease() >= 1){
                Value.setValue((float) round(current,0));

            } else {
                String str = "" + Value.getIncrease();
                Value.setValue((float) round(current,str.length() - (str.indexOf(".") + 1)));
            }
        }
    }

    @Override
    public void render() {
        FontLoaders.default20.drawString(Value.getName() + "["+Value.getValue()+"]",
                x,
                y,
                new Color(255,255,255).getRGB());

        double currentX = 295 * ((Value.getValue() - Value.getMin())/(Value.getMax() - Value.getMin()));

        RenderUtil.drawFilledCircle(x + 5, y + 25, 6,
                new Color(45,37,104).getRGB(),5);
        RenderUtil.drawRect(x + 6, y + 19, x + 295 + 11,
                y + 31,
                new Color(45,36,104).getRGB());
        RenderUtil.drawFilledCircle(x + 305, y + 25, 6,
                new Color(45,37,104).getRGB(),5);

        RenderUtil.drawFilledCircle(x + 5, y + 25, 5,
                new Color(45,45,45).getRGB(),5);
        RenderUtil.drawRect(x + 5, y + 20, x + 295 + 10,
                y + 30,
                new Color(45,45,45).getRGB());
        RenderUtil.drawFilledCircle(x + 305, y + 25, 5,
                new Color(45,45,45).getRGB(),5);

        RenderUtil.drawFilledCircle(x + 5, y + 25, 6,
                new Color(97,79,237).getRGB(),5);
        RenderUtil.drawRect(x + 10 - 4, y + 19, (float)(x + 10  + currentX),
                y + 31,
                new Color(97,79,237).getRGB());
        RenderUtil.drawFilledCircle(x + 10  + currentX, y  + 25, 8,
                new Color(97,79,237).getRGB(),5);

    }

    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
