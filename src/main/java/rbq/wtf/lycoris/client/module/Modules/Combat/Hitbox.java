package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.NumberValue;

import java.util.List;

public class Hitbox extends Module {
    public static NumberValue expand;
    public static BooleanValue extra;
    public static NumberValue extraV;
    public static Minecraft mc = Minecraft.getMinecraft();
    public Hitbox (){
        super("HitBox", ModuleCategory.Combat,0);
        expand = new NumberValue("Expand", 0.1, 0.0, 1.0, 0.1);
        extra = new BooleanValue("Extra",false);
        extraV = new NumberValue("ExtraExpand", 0.0, 0.0, 15.0, 2.0);
        this.addNumberValue(expand);
        this.addBooleanValue(extra);
        this.addNumberValue(extraV);

    }

    @EventTarget
    public void onUpdate(EventClientTick eventClientTick) {

        Entity renderViewEntity = Reach.mc.getRenderViewEntity();
        List loadedEntityList = Reach.mc.world.loadedEntityList;
        Entity ent = entity();
        for (int i = 0; i < loadedEntityList.size(); ++i) {
            Entity e = (Entity)loadedEntityList.get(i);
            if (e == mc.player || !e.canBeCollidedWith()) continue;
            e.width = (float)(extra.getValue()?0.6 + expand.getValue()+extraV.getValue():0.6 + expand.getValue()//expand.getValue()
            );
        }




    }

    public static Entity entity() {
        Entity e = null;
        if (mc.player.world != null) {
            for (Object o : mc.world.loadedEntityList) {
                e = (Entity) o;
            }
        }
        return e;
    }

}
