package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapObject;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;
@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats",targetMap = MapEnum.Srg1_8_9)
public class DefaultVertexFormats extends IWrapper {
    @WrapObject(mcpName = "POSITION_TEX_COLOR",targetMap = MapEnum.Srg1_8_9)
    public static Object POSITION_TEX_COLOR;
    @WrapObject(mcpName = "POSITION",targetMap = MapEnum.Srg1_8_9)
    public static Object POSITION;
    @WrapObject(mcpName = "POSITION_COLOR",targetMap = MapEnum.Srg1_8_9)
    public static Object POSITION_COLOR;
    public DefaultVertexFormats(Object obj) {
        super(obj);
    }

    public static VertexFormat POSITION() {
        return new VertexFormat(POSITION);
    }
    public static VertexFormat POSITION_COLOR(){
        return new VertexFormat(POSITION_COLOR);
    }
    public static VertexFormat POSITION_TEX_COLOR() {return new VertexFormat(POSITION_COLOR);}
}