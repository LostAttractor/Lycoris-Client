package rbq.wtf.lycoris.client.gui.NewClickGUI.Component;

public class Component {

    private float Height = 0;
    private float Width = 0;

    public Component() {
    }

    public void render() {
    }

    public void keyTyped(char typedChar, int keyCode) {
    }

    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

    public float getWidth() {
        return Width;
    }

    public void setWidth(float width) {
        Width = width;
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }
    public void mouseReleased(int mouseButton){

    }
}
