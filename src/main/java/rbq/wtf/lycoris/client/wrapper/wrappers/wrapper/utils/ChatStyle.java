package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event.HoverEvent;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event.click.ClickEvent;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.ChatStyle",targetMap = MapEnum.VANILLA189)
public class ChatStyle extends IWrapper {
    @WrapMethod(mcpName = "getChatHoverEvent",targetMap = MapEnum.VANILLA189)
    public static Method getChatHoverEvent;
    @WrapMethod(mcpName = "getChatClickEvent",targetMap = MapEnum.VANILLA189)
    public static Method getChatClickEvent;
    @WrapMethod(mcpName = "getInsertion",targetMap = MapEnum.VANILLA189)
    public static Method getInsertion;
    public ChatStyle(Object obj) {
        super(obj);
    }
    public HoverEvent getChatHoverEvent(){
        return new HoverEvent(invoke(getChatHoverEvent));
    }
    public ClickEvent getChatClickEvent(){
        return new ClickEvent(invoke(getChatClickEvent));
    }
    public String getInsertion(){
        return (String) invoke(getInsertion);
    }
}