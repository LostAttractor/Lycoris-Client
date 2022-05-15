package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

import rbq.wtf.lycoris.client.value.ColorValue;

public class ColorValueComponent extends Component{
    public ColorValue value;
    private float x;
    private float y;
    public ColorValueComponent(ColorValue Value) {
        this.value = Value;
    }
    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X;
        this.y = Y;
        render();
    }

    @Override
    public void render() {
        super.render();
    }
}
