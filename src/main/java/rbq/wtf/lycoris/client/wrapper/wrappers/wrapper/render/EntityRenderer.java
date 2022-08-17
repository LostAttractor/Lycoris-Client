package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = MapEnum.VANILLA189)
public class EntityRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = MapEnum.VANILLA189)
    public static Class EntityRendererClass;
    @WrapMethod(mcpName = "renderWorldPass",targetMap = MapEnum.VANILLA189)
    public static Method renderWorldPass;
    @WrapMethod(mcpName = "getMouseOver",targetMap = MapEnum.VANILLA189)
    public static Method getMouseOver;
    @WrapField(mcpName = "pointedEntity",targetMap = MapEnum.VANILLA189)
    public static Field pointedEntity;
    @WrapMethod(mcpName = "disableLightmap",targetMap = MapEnum.VANILLA189)
    public static Method disableLightmap;
    @WrapMethod(mcpName = "enableLightmap",targetMap = MapEnum.VANILLA189)
    public static Method enableLightmap;
    @WrapMethod(mcpName = "setupCameraTransform",targetMap = MapEnum.VANILLA189)
    public static Method setupCameraTransform;
    @WrapField(mcpName = "mc",targetMap = MapEnum.VANILLA189)
    public static Field mc;
    public EntityRenderer(Object obj) {
        super(obj);
    }
    public void enableLightmap(){
        invoke(enableLightmap);
    }
    public void disableLightmap(){
        invoke(disableLightmap);
    }
    public void setupCameraTransform(float partialTicks, int pass){
        invoke(setupCameraTransform,partialTicks,pass);
    }
    public Entity getPointedEntity(){
        return new Entity(getField(pointedEntity));
    }
    public void setPointedEntity(Entity entity){
        setField(pointedEntity,entity.getWrapObject());
    }
}