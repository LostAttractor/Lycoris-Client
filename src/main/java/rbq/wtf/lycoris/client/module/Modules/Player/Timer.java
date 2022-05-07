package rbq.wtf.lycoris.client.module.Modules.Player;

import net.minecraft.client.Minecraft;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventPlayerTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

public class Timer extends Module {
    public static NumberValue speed;
    public static NumberValue Exspeed;
    public static BooleanValue Eexspeed;
    public Timer(){
        super("Timer", ModuleCategory.Player,0);
        speed = new NumberValue("Speed", 1.0f, 0.1f, 3.0f, 1.0f);
        Exspeed = new NumberValue("ExSpeed", 1.0f, 0.0f, 15.0f, 1.0f);
        Eexspeed = new BooleanValue("use EX",false);
        this.addBooleanValue(Eexspeed);
        this.addNumberValue(speed);
        this.addNumberValue(Exspeed);
    }
    @EventTarget
    public void onUpdate(EventPlayerTick e){
        float expandvalue;
        if(Eexspeed.getValue()){
            expandvalue = Exspeed.getValue();
        } else {
            expandvalue = 0.0f;
        }
        Wrapper.setTickLength(Minecraft.getMinecraft(),speed.getValue()+expandvalue);
    }
}
