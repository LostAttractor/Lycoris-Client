package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.settings.GameSettings",targetMap = MapEnum.MDK189)
public class GameSettings extends IWrapper {
    @WrapField(mcpName = "keyBindForward",targetMap = MapEnum.MDK189)
    public static Field keyBindForward;
    @WrapField(mcpName = "keyBindLeft",targetMap = MapEnum.MDK189)
    public static Field keyBindLeft;
    @WrapField(mcpName = "keyBindBack",targetMap = MapEnum.MDK189)
    public static Field keyBindBack;
    @WrapField(mcpName = "keyBindRight",targetMap = MapEnum.MDK189)
    public static Field keyBindRight;
    @WrapField(mcpName = "keyBindJump",targetMap = MapEnum.MDK189)
    public static Field keyBindJump;
    @WrapField(mcpName = "keyBindSneak",targetMap = MapEnum.MDK189)
    public static Field keyBindSneak;
    @WrapField(mcpName = "keyBindSprint",targetMap = MapEnum.MDK189)
    public static Field keyBindSprint;
    @WrapField(mcpName = "keyBindInventory",targetMap = MapEnum.MDK189)
    public static Field keyBindInventory;
    @WrapField(mcpName = "keyBindUseItem",targetMap = MapEnum.MDK189)
    public static Field keyBindUseItem;
    @WrapField(mcpName = "keyBindDrop",targetMap = MapEnum.MDK189)
    public static Field keyBindDrop;
    @WrapField(mcpName = "keyBindAttack",targetMap = MapEnum.MDK189)
    public static Field keyBindAttack;
    @WrapField(mcpName = "keyBindPickBlock",targetMap = MapEnum.MDK189)
    public static Field keyBindPickBlock;
    @WrapField(mcpName = "keyBindChat",targetMap = MapEnum.MDK189)
    public static Field keyBindChat;
    @WrapField(mcpName = "keyBindPlayerList",targetMap = MapEnum.MDK189)
    public static Field keyBindPlayerList;
    @WrapField(mcpName = "keyBindCommand",targetMap = MapEnum.MDK189)
    public static Field keyBindCommand;
    @WrapField(mcpName = "keyBindScreenshot",targetMap = MapEnum.MDK189)
    public static Field keyBindScreenshot;
    @WrapField(mcpName = "keyBindTogglePerspective",targetMap = MapEnum.MDK189)
    public static Field keyBindTogglePerspective;
    @WrapField(mcpName = "keyBindSmoothCamera",targetMap = MapEnum.MDK189)
    public static Field keyBindSmoothCamera;
    @WrapField(mcpName = "keyBindFullscreen",targetMap = MapEnum.MDK189)
    public static Field keyBindFullscreen;
    @WrapField(mcpName = "keyBindSpectatorOutlines",targetMap = MapEnum.MDK189)
    public static Field keyBindSpectatorOutlines;
    @WrapField(mcpName = "keyBindsHotbar",targetMap = MapEnum.MDK189)
    public static Field keyBindsHotbar;
    @WrapField(mcpName = "keyBindings",targetMap = MapEnum.MDK189)
    public static Field keyBindings;
    @WrapField(mcpName = "mouseSensitivity",targetMap = MapEnum.MDK189)
    public static Field mouseSensitivity;
    @WrapField(mcpName = "gammaSetting",targetMap = MapEnum.MDK189)
    public static Field gammaSetting;
    @WrapField(mcpName = "chatLinks",targetMap = MapEnum.MDK189)
    public static Field chatLinks;
    @WrapField(mcpName = "forceUnicodeFont",targetMap = MapEnum.MDK189)
    public static Field forceUnicodeFont;
    @WrapField(mcpName = "viewBobbing",targetMap = MapEnum.MDK189)
    public static Field viewBobbing;
    @WrapField(mcpName = "thirdPersonView",targetMap = MapEnum.MDK189)
    public static Field thirdPersonView;
    @WrapField(mcpName = "guiScale",targetMap = MapEnum.MDK189)
    public static Field guiScale;
    public GameSettings(Object obj) {
        super(obj);
    }
    public KeyBinding getKeyBindAttack() {
        return new KeyBinding(ReflectUtil.getField(keyBindAttack,getWrapObject()));
    }
    public KeyBinding getKeyBindUseItem(){
        return new KeyBinding(ReflectUtil.getField(keyBindUseItem,getWrapObject()));
    }
    public float getMouseSensitivity(){
        return (float) getField(mouseSensitivity);
    }
    public KeyBinding getKeyBindSneak(){
        return new KeyBinding(getField(keyBindSneak));
    }
    public KeyBinding getKeyBindJump(){
        return new KeyBinding(getField(keyBindJump));
    }
    public KeyBinding getKeyBindForward(){
        return new KeyBinding(getField(keyBindForward));
    }
    public KeyBinding getKeyBindRight(){
        return new KeyBinding(getField(keyBindRight));
    }
    public KeyBinding getKeyBindLeft(){
        return new KeyBinding(getField(keyBindLeft));
    }
    public KeyBinding getKeyBindBack(){
        return new KeyBinding(getField(keyBindBack));
    }
    public KeyBinding getKeyBindSprint() {
        return new KeyBinding(getField(keyBindSprint));
    }
    public void setGammaSetting(float v){
        setField(gammaSetting,v);
    }
    public boolean getChatLinks(){
        return (boolean) getField(chatLinks);
    }
    public int getGuiScale(){
        return (int) getField(guiScale);
    }
    public boolean getForceUnicodeFont(){
        return (boolean) getField(forceUnicodeFont);
    }
    public boolean getViewBobbing(){
        return (boolean) getField(viewBobbing);
    }
    public void setViewBobbing(boolean b){
        setField(viewBobbing,b);
    }
    public void setThirdPersonView(int view){
        setField(thirdPersonView,view);
    }
}