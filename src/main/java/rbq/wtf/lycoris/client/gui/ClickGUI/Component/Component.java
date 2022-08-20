package rbq.wtf.lycoris.client.gui.ClickGUI.Component;

public class Component {
    private float height = 0;
    private float width = 0;

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
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float) mouseX >= x && (float) mouseX <= x2 && (float) mouseY >= y && (float) mouseY <= y2;
    }

    public void mouseReleased(int mouseButton) {

    }
}
