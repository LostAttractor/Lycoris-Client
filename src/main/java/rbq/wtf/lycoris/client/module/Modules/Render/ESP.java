package rbq.wtf.lycoris.client.module.Modules.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRenderWorldLast;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.module.Modules.Combat.KillAura;
import rbq.wtf.lycoris.client.utils.ValidUtils;
import rbq.wtf.lycoris.client.utils.WorldRenderUtils;
import rbq.wtf.lycoris.client.utils.WorldUtil;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.NumberValue;

import java.lang.reflect.Field;

public class ESP extends Module {
    Minecraft mc = Minecraft.getMinecraft();

    public BooleanValue invisibles = new BooleanValue("Invisibles", false,this);
    public BooleanValue players = new BooleanValue("Players", false,this);
    public BooleanValue mobs = new BooleanValue("Mobs", false,this);
    public NumberValue red = new NumberValue("Red" ,255,0,255,1);
    public NumberValue green = new NumberValue("Green" ,255,0,255,1);
    public NumberValue blue = new NumberValue("Blue" ,255,0,255,1);
    public ESP (){
        super("ESP", ModuleCategory.Render,0);
        this.addBooleanValue(invisibles);
        this.addBooleanValue(players);
        this.addBooleanValue(mobs);
        this.addNumberValue(red);
        this.addNumberValue(green);
        this.addNumberValue(blue);
    }
    @EventTarget
    private void onRender3D(EventRenderWorldLast event) {
        for (EntityLivingBase entity : WorldUtil.getLivingEntities()) {

            if (this.isValid(entity)) {
                continue;
            }

            try {
                Field field = ReflectionHelper.findField(Minecraft.class, new String[]{"renderManager", "field_175616_W"});
                field.setAccessible(true);

                Field F_renderPosX = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosX", "field_78725_b"});
                F_renderPosX.setAccessible(true);
                double renderPosX = F_renderPosX.getDouble(field.get(Minecraft.getMinecraft()));

                Field F_renderPosY = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosY", "field_78726_c"});
                F_renderPosY.setAccessible(true);
                double renderPosY = F_renderPosY.getDouble(field.get(Minecraft.getMinecraft()));

                Field F_renderPosZ = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosZ", "field_78723_d"});
                F_renderPosZ.setAccessible(true);
                double renderPosZ = F_renderPosZ.getDouble(field.get(Minecraft.getMinecraft()));

                double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) event.getEvent().getPartialTicks() - renderPosX;
                double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) event.getEvent().getPartialTicks() - renderPosY;
                double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) event.getEvent().getPartialTicks() - renderPosZ;
                if (KillAura.targets.indexOf(entity) != 0) {
                    WorldRenderUtils.drawOutlinedEntityESP(posX, posY, posZ, 0.4, entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY, this.red.getValue()/255f, this.green.getValue()/255f, this.blue.getValue()/255f, 1);
                } else {
                    WorldRenderUtils.drawOutlinedEntityESP(posX, posY, posZ, 0.4, entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY, 188/255f, 0/255f, 0/255f, 1);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }



        }
    }


    public boolean isValid(EntityLivingBase entity) {


        if(!players.getValue()) {
            if (ValidUtils.isPlayer(entity)) {
                return true;
            }
        }
        if(!mobs.getValue()) {
            if (ValidUtils.isMob(entity)) {
                return true;
            }
        }
        if(!invisibles.getValue()) {
            if (ValidUtils.isInvisible(entity)) {
                return true;
            }
        }


        return false;
    }
}
