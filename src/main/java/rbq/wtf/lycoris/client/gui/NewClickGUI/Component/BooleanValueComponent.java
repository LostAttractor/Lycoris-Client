package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

import rbq.wtf.lycoris.client.gui.ClickGUI.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.gui.NewClickGUI.BlurBuffer;
import rbq.wtf.lycoris.client.gui.NewClickGUI.BlurUtil;
import rbq.wtf.lycoris.client.gui.NewClickGUI.NewClickGUI;
import rbq.wtf.lycoris.client.value.BooleanValue;

import java.awt.*;

public class BooleanValueComponent extends Component{
    private BooleanValue Value;
    private float x;
    private float y;
    public BooleanValueComponent(BooleanValue value) {
        this.Value = value;
        this.setHeight(25);
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();
    }
    @Override
    public void render() {
        RenderUtil.startGlScissor((int) (NewClickGUI.startX), (int) (NewClickGUI.startY + 50.0F),445, (int)277);
        FontLoaders.default20.drawString(Value.getName(),
                x,
                y,
                new Color(255,255,255).getRGB());
        RenderUtil.stopGlScissor();
        if (Value.getValue()) {
            //Draw Switch Background
            /*Render Glow*/
//            RenderUtil.startGlScissor((int) (x + 287), (int) (y + 15),26, 8);
//            RenderUtil.drawFilledCircle(x + 295, y + 10, 6,
//                    new Color(79,66,184).getRGB(),5);
//            RenderUtil.drawRect(x + 295 - 1, y + 4, x + 295 + 10,
//                    y + 16,
//                    new Color(79,66,184).getRGB());
//            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 6,
//                    new Color(79,66,184).getRGB(),5);
//            BlurUtil.blurAll(3);
//            RenderUtil.stopGlScissor();
            /*Glow End*/

            RenderUtil.drawFilledCircle(x + 295, y + 10, 6,
                    new Color(79,66,184).getRGB(),5);
            RenderUtil.drawRect(x + 295 - 1, y + 4, x + 295 + 10,
                    y + 16,
                    new Color(79,66,184).getRGB());
            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 6,
                    new Color(79,66,184).getRGB(),5);

            RenderUtil.drawFilledCircle(x + 295, y + 10, 5,
                    new Color(26,16,46).getRGB(),5);
            RenderUtil.drawRect(x + 295 - 1, y + 5, x + 295 + 10,
                    y + 15,
                    new Color(26,16,46).getRGB());
            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 5,
                    new Color(26,16,46).getRGB(),5);

            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 3,
                    new Color(98,96,207).getRGB(),5);
        } else {
            //Draw Switch Background
//            RenderUtil.startGlScissor((int) (x + 287), (int) (y + 15),26, 10);
//            RenderUtil.drawFilledCircle(x + 295, y + 10, 6,
//                    new Color(83,69,193).getRGB(),5);
//            RenderUtil.drawRect(x + 295 - 1, y + 4, x + 295 + 10,
//                    y + 16,
//                    new Color(83,69,193).getRGB());
//            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 6,
//                    new Color(83,69,193).getRGB(),5);
//            BlurUtil.blurAll(3);
//            RenderUtil.stopGlScissor();
            RenderUtil.drawFilledCircle(x + 295, y + 10, 6,
                    new Color(83,69,193).getRGB(),5);
            RenderUtil.drawRect(x + 295 - 1, y + 4, x + 295 + 10,
                    y + 16,
                    new Color(83,69,193).getRGB());
            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 6,
                    new Color(83,69,193).getRGB(),5);

            RenderUtil.drawFilledCircle(x + 295, y + 10, 5,
                    new Color(25,25,25).getRGB(),5);
            RenderUtil.drawRect(x + 295 - 1, y + 5, x + 295 + 10,
                    y + 15,
                    new Color(25,25,25).getRGB());
            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 5,
                    new Color(25,25,25).getRGB(),5);

            RenderUtil.drawFilledCircle(x + 295, y + 10, 3,
                    new Color(80,81,81).getRGB(),5);
        }

        super.render();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.isHovered(x + 295 - 5, y + 3, x + 295 + 10,
                y + 17,mouseX,mouseY)) {
            Value.setValue(!Value.getValue());
        }
    }
}
