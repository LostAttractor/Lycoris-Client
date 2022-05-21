package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventPlayerTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;

import java.lang.reflect.Field;

public class InfiniteAmmo extends Module {
    public InfiniteAmmo(){
        super("InfiniteAmmo", ModuleCategory.Combat,0);
    }
    @EventTarget
    public void onUpdate(EventPlayerTick eventPlayerTick) {
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack itemStack = mc.player.getHeldItem(EnumHand.MAIN_HAND);
        NBTTagCompound nbt = itemStack.getTagCompound();
        if(nbt.getString("bullet").isEmpty()) {
            return;
        }
        try {
            Class<?> hud = Class.forName("com.trychen.clay.core.weapon.hud.q");
            Field bullet = hud.getDeclaredField("long");
            bullet.setAccessible(true);
            bullet.setLong(null,114514);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mc.player.inventory.currentItem == 0 || mc.player.inventory.currentItem ==1) {
            try {
                Class<?> hud = Class.forName("com.trychen.clay.core.kta");
                Field bullet = hud.getDeclaredField("count");
                bullet.setAccessible(true);
                int[] count = (int[]) bullet.get(null);
                count[mc.player.inventory.currentItem] = 114514;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
