package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event.click;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.EnumWrapper;

@WrapperClass(mcpName = "net.minecraft.event.ClickEvent$Action",targetMap = MapEnum.Srg1_8_9)
public class ClickEventAction extends EnumWrapper {
    @WrapEnum(mcpName = "OPEN_URL",targetMap = MapEnum.Srg1_8_9)
    public static Enum OPEN_URL;
    @WrapEnum(mcpName = "OPEN_FILE",targetMap = MapEnum.Srg1_8_9)
    public static Enum OPEN_FILE;
    @WrapEnum(mcpName = "RUN_COMMAND",targetMap = MapEnum.Srg1_8_9)
    public static Enum RUN_COMMAND;
    @WrapEnum(mcpName = "TWITCH_USER_INFO",targetMap = MapEnum.Srg1_8_9)
    public static Enum TWITCH_USER_INFO;
    @WrapEnum(mcpName = "SUGGEST_COMMAND",targetMap = MapEnum.Srg1_8_9)
    public static Enum SUGGEST_COMMAND;
    @WrapEnum(mcpName = "CHANGE_PAGE",targetMap = MapEnum.Srg1_8_9)
    public static Enum CHANGE_PAGE;
    public ClickEventAction(Object obj) {
        super(obj);
    }
}