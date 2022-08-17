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

    @EventTarget
    public void EventMotionUpdate(EventMotionUpdate e) {
        //if (e.isPre()) {
        //    mc.getPlayer().setSprinting(true);
        //    mc.getPlayer().setSprintingTicksLeft(0);
        //    Logger.log("Set Sprint");
        //}
    }
}
