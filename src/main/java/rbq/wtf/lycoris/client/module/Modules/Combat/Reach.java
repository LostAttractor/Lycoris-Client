package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.MouseEvent;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventMouse;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.NumberValue;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class Reach extends Module {
    public static BooleanValue throughBlocks;
    public static Minecraft mc;
    public static NumberValue reachMaxVal;
    public static  NumberValue reachMinVal;
    public Random r;
    public static Field pZ;
    public static  Field pX;

    public Reach() {
        super("Reach",ModuleCategory.Combat,0);
        r = new Random();
        mc = Minecraft.getMinecraft();
        reachMinVal = new NumberValue("Min Reach", 3.0, 3.0, 6.0, 1.0);
        this.addNumberValue(reachMinVal);
        reachMaxVal = new NumberValue("Max Reach", 3.0, 3.0, 6.0, 1.0);
        this.addNumberValue(reachMaxVal);
        throughBlocks = new BooleanValue("Through Blocks", false);
        this.addBooleanValue(throughBlocks);
    }

    public static Object[] getMouseOver(double Range, double bbExpand, float f) {
        Entity renderViewEntity = mc.getRenderViewEntity();
        Entity entity = null;
        if (renderViewEntity == null || Reach.mc.world == null) {
            return null;
        }
        Reach.mc.mcProfiler.startSection("pick");
        Vec3d positionEyes = renderViewEntity.getPositionEyes(0.0f);
        Vec3d renderViewEntityLook = renderViewEntity.getLook(0.0f);
        Vec3d vector = positionEyes.addVector(renderViewEntityLook.x * Range, renderViewEntityLook.y * Range, renderViewEntityLook.z * Range);
        Vec3d ve = null;
        List entitiesWithinAABB = Reach.mc.world.getEntitiesWithinAABBExcludingEntity(renderViewEntity, renderViewEntity.getEntityBoundingBox().expand(renderViewEntityLook.x * Range, renderViewEntityLook.y * Range, renderViewEntityLook.z * Range).expand(1.0, 1.0, 1.0));
        double range = Range;
        for (int i = 0; i < entitiesWithinAABB.size(); ++i) {
            double v;
            Entity e = (Entity)entitiesWithinAABB.get(i);
            if (!e.canBeCollidedWith()) continue;
            float size = e.getCollisionBorderSize();
            AxisAlignedBB bb = e.getEntityBoundingBox().expand((double)size, (double)size, (double)size);
            bb = bb.expand(bbExpand, bbExpand, bbExpand);
            RayTraceResult objectPosition = bb.calculateIntercept(positionEyes, vector);
            if (bb.contains(positionEyes)) {
                if (!(0.0 < range) && range != 0.0) continue;
                entity = e;
                ve = objectPosition == null ? positionEyes : objectPosition.hitVec;
                range = 0.0;
                continue;
            }
            if (objectPosition == null || !((v = positionEyes.distanceTo(objectPosition.hitVec)) < range) && range != 0.0) continue;
            boolean b = false;
            if (e == renderViewEntity.getRidingEntity()) {
                if (range != 0.0) continue;
                entity = e;
                ve = objectPosition.hitVec;
                continue;
            }
            entity = e;
            ve = objectPosition.hitVec;
            range = v;
        }
        if (range < Range && !(entity instanceof EntityLivingBase) && !(entity instanceof EntityItemFrame)) {
            entity = null;
        }
        Reach.mc.mcProfiler.endSection();
        if (entity == null || ve == null) {
            return null;
        }
        return new Object[]{entity, ve};
    }

    @EventTarget
    public void onMouseEvent(EventMouse event) {

        if (!throughBlocks.getValue() && Reach.mc.objectMouseOver != null && Reach.mc.objectMouseOver.typeOfHit != null && Reach.mc.objectMouseOver.typeOfHit ==  RayTraceResult.Type.BLOCK) {
            return;
        }
        double range = reachMinVal.getValue() + r.nextDouble() * (reachMaxVal.getValue() - reachMinVal.getValue());
        Object[] mouseOver = Reach.getMouseOver(range, 0.0, 0.0f);
        if (mouseOver == null) {
            return;
        }
        Vec3d lookVec = Reach.mc.player.getLookVec();
        Reach.mc.objectMouseOver = new RayTraceResult((Entity)mouseOver[0], (Vec3d)mouseOver[1]);
        Reach.mc.pointedEntity = (Entity)mouseOver[0];
    }
}
