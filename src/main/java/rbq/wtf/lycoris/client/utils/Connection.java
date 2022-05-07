package rbq.wtf.lycoris.client.utils;

import io.netty.channel.*;
import net.minecraft.network.play.client.CPacketChatMessage;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.event.EventHandler;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.events.EventPacket;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

public class Connection extends ChannelDuplexHandler {

    public static enum Side { IN, OUT; }

    public Connection() {

        try {
            ChannelPipeline pipeline = Wrapper.INSTANCE.mc().getConnection().getNetworkManager().channel().pipeline();
            pipeline.addBefore("packet_handler", "PacketHandler", (ChannelHandler) this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {

        EventPacket event = new EventPacket(packet,Side.IN);
        EventManager.call(event);
        if (event.isCancelled()) return;
        super.channelRead(ctx, packet);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
        if (packet instanceof CPacketChatMessage) {
            if(((CPacketChatMessage) packet).getMessage().indexOf(";") == 0){
                LycorisClient.instance.getCommandManager().runCommands(((CPacketChatMessage) packet).getMessage());
                return;

            }

        }
        EventPacket event = new EventPacket(packet,Side.OUT);
        EventManager.call(event);
        if (event.isCancelled()) return;
        super.write(ctx, packet, promise);
    }
}
