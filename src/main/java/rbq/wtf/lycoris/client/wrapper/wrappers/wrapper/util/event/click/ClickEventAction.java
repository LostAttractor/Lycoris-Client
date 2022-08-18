package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.click;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.EnumWrapper;

@WrapperClass(mcpName = "net.minecraft.event.ClickEvent$Action", targetMap = MapEnum.VANILLA189)
public class ClickEventAction extends EnumWrapper {
    @WrapEnum(mcpName = "OPEN_URL", targetMap = MapEnum.VANILLA189)
    public static Enum OPEN_URL;
    @WrapEnum(mcpName = "OPEN_FILE", targetMap = MapEnum.VANILLA189)
    public static Enum OPEN_FILE;
    @WrapEnum(mcpName = "RUN_COMMAND", targetMap = MapEnum.VANILLA189)
    public static Enum RUN_COMMAND;
    @WrapEnum(mcpName = "TWITCH_USER_INFO", targetMap = MapEnum.VANILLA189)
    public static Enum TWITCH_USER_INFO;
    @WrapEnum(mcpName = "SUGGEST_COMMAND", targetMap = MapEnum.VANILLA189)
    public static Enum SUGGEST_COMMAND;
    @WrapEnum(mcpName = "CHANGE_PAGE", targetMap = MapEnum.VANILLA189)
    public static Enum CHANGE_PAGE;

    public ClickEventAction(Object obj) {
        super(obj);
    }
}