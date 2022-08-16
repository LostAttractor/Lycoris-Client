package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.text;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.ChatStyle;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.IChatComponent",targetMap = MapEnum.Srg1_8_9)
public class IChatComponent extends IWrapper {
    @WrapMethod(mcpName = "getUnformattedText",targetMap = MapEnum.Srg1_8_9)
    public static Method getUnformattedText;
    @WrapMethod(mcpName = "getFormattedText",targetMap = MapEnum.Srg1_8_9)
    public static Method getFormattedText;
    @WrapMethod(mcpName = "getChatStyle",targetMap = MapEnum.Srg1_8_9)
    public static Method getChatStyle;
    public IChatComponent(Object obj) {
        super(obj);
    }
    public String getUnformattedText(){
        return (String) ReflectUtil.invoke(getUnformattedText,getWrapObject());
    }
    public String getFormattedText(){
        return (String) ReflectUtil.invoke(getFormattedText,getWrapObject());
    }
    public ChatStyle getChatStyle(){
        return new ChatStyle(invoke(getChatStyle));
    }
}