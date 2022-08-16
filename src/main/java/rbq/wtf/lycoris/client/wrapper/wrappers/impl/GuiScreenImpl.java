package rbq.wtf.lycoris.client.wrapper.wrappers.impl;



public abstract class GuiScreenImpl{
    public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);
    public void initGui(){}
    public void onGuiClosed(){}
    public boolean keyTyped(char typedChar, int keyCode){
        return true;
    }
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){}
    public void mouseReleased(int mouseX, int mouseY, int state){}
    public void updateScreen(){}
}