package rbq.wtf.lycoris.client.module.Modules.Player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.event.events.EventPacket;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

public class ChestStealer extends Module {
    public NumberValue delay = new NumberValue("Delay", 4.0, 0.0, 20.0,1,this);

    public SPacketWindowItems packet;
    public int ticks;
    public ChestStealer() {
        super("ChestStealer", ModuleCategory.Player,0);
        this.addNumberValue(delay);
    }
    @EventTarget
    public boolean onPacket(EventPacket event) {
        if(event.getSide() == Connection.Side.IN && packet instanceof SPacketWindowItems) {
            this.packet = (SPacketWindowItems)packet;
        }
        return true;
    }

    boolean isContainerEmpty(Container container) {
        boolean temp = true;
        int i = 0;
        for(int slotAmount = container.inventorySlots.size() == 90 ? 54 : 35; i < slotAmount; i++) {
            if (container.getSlot(i).getHasStack()) {
                temp = false;
            }
        }
        return temp;
    }

    @EventTarget
    public void onClientTick(EventClientTick event) {

        if(event.getEvent().phase != TickEvent.Phase.START) return;
        EntityPlayerSP player = Wrapper.INSTANCE.player();
        if ((!Wrapper.INSTANCE.mc().inGameHasFocus)
                && (this.packet != null)
                && (player.openContainer.windowId == this.packet.getWindowId())
                && ((Wrapper.INSTANCE.mc().currentScreen instanceof GuiChest))) {
            if (!isContainerEmpty(player.openContainer)) {
                for (int i = 0; i < player.openContainer.inventorySlots.size() - 36; ++i) {
                    Slot slot = player.openContainer.getSlot(i);
                    if (slot.getHasStack() && slot.getStack() != null) {
                        if (this.ticks >= this.delay.getValue().intValue()) {
                            Wrapper.INSTANCE.controller().windowClick(player.openContainer.windowId, i, 1, ClickType.QUICK_MOVE, player);
                            this.ticks = 0;
                        }
                    }
                }
                this.ticks += 1;
            }
            else
            {
                player.closeScreen();
                this.packet = null;
            }
        }
    }
}
