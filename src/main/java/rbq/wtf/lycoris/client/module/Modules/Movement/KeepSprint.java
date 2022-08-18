package rbq.wtf.lycoris.client.module.Modules.Movement;


import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventMotionUpdate;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Logger;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

public class KeepSprint extends Module {

    public KeepSprint() {
        super("KeepSprint", ModuleCategory.Movement, 0);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }


    // 应当使用EventMove，但是暂时还没想好该事件如何实现
    @EventTarget
    public void EventMotionUpdate(EventMotionUpdate e) {
        if (e.isPre()) {
            if (mc.getPlayer().isMoving() && mc.getPlayer().getEntityPlayerInstance().getFoodStats().getFoodLevel() > 6) {
                mc.getPlayer().setSprinting(true);
                mc.getPlayer().setSprintingTicksLeft(0);
                Logger.log("Set Sprint", "SprintModule", Logger.LogLevel.DEBUG);
            }
        }
    }
}
