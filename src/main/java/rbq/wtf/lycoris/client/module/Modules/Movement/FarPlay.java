package rbq.wtf.lycoris.client.module.Modules.Movement;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.event.events.EventPacket;
import rbq.wtf.lycoris.client.event.events.EventRenderWorldLast;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.AStarCustomPathFinder;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.utils.Render.ChatUtils;
import rbq.wtf.lycoris.client.utils.Vec3;
import rbq.wtf.lycoris.client.utils.WorldRenderUtils;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class FarPlay extends Module {
    Minecraft mc = Minecraft.getMinecraft();
    public NumberValue speed_x = new NumberValue("SpeedX",1,1,10,0.1);
    public NumberValue speed_y = new NumberValue("SpeedY",1,1,10,0.1);
    public FarPlay(){
        super("FarPlay", ModuleCategory.Movement,0);
        this.addNumberValue(speed_x);
        this.addNumberValue(speed_y);
    }
    private double dashDistance = 2;
    public int farPlayHeartbeatTimer = 0;
    public int farPlayCdTimer = 0;
    public Vec3 farPlaySavePos = null;
    public float farPlaySavePicth = 0;
    public float farPlaySaveYaw = 0;
    public boolean farPlaySending = false;
    public List<Packet> farPlaySavePacketList = new ArrayList<Packet>();
    public List<Vec3> routeList = new ArrayList();
    public boolean farPlaySneak = false;
    public int fastEatTimer = 0;
    @Override
    public void onEnable() {
        if (mc.player==null || mc.world == null) {
            return;
        }
        this.farPlaySavePos = new Vec3(mc.player.posX, mc.player.posY, mc.player.posZ);
        this.farPlaySavePicth = mc.player.rotationPitch;
        this.farPlaySaveYaw = mc.player.rotationYaw;
        farPlaySavePacketList.clear();
        routeList.clear();
    }

    @Override
    public void onDisable() {
        mc.player.setPositionAndRotation(farPlaySavePos.x, farPlaySavePos.y, farPlaySavePos.z, farPlaySaveYaw, farPlaySavePicth);
        fastEatTimer = 0;

        farPlayHeartbeatTimer = 0;
        farPlayCdTimer = 0;
        this.farPlaySavePos = null;

        farPlaySavePacketList.clear();
        if (farPlaySneak && mc.player!=null && mc.world != null && mc.player.isEntityAlive()) {
            farPlaySneak = false;
            mc.getConnection().sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }

        farPlaySavePacketList.clear();
        routeList.clear();
    }

    private boolean canPassThrow(BlockPos pos) {
        Block block = Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock();
        return block.getMaterial(Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ()))) == Material.AIR || block.getMaterial(Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ()))) == Material.PLANTS || block.getMaterial(Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ()))) == Material.VINE || block == Blocks.LADDER || block == Blocks.WATER ||
                block == Blocks.FLOWING_WATER || block == Blocks.WALL_SIGN || block == Blocks.STANDING_SIGN;
    }
    public List<Vec3> farPlayRoute(Vec3 topFrom, Vec3 to) {
        if (!canPassThrow(new BlockPos(topFrom.mc()))) {
            topFrom = topFrom.addVector(0, 1, 0);
        }
        AStarCustomPathFinder pathfinder = new AStarCustomPathFinder(topFrom, to);
        pathfinder.compute();

        int i = 0;
        Vec3 lastLoc = null;
        Vec3 lastDashLoc = null;
        ArrayList<Vec3> path = new ArrayList<Vec3>();
        ArrayList<Vec3> pathFinderPath = pathfinder.getPath();
        for (Vec3 pathElm : pathFinderPath) {
            if (i == 0 || i == pathFinderPath.size() - 1) {
                if (lastLoc != null) {
                    path.add(lastLoc.addVector(0.5, 0, 0.5));
                }
                path.add(pathElm.addVector(0.5, 0, 0.5));
                lastDashLoc = pathElm;
            } else {
                boolean canContinue = true;
                if (pathElm.squareDistanceTo(lastDashLoc) > dashDistance * dashDistance) {
                    canContinue = false;
                } else {
                    double smallX = Math.min(lastDashLoc.getX(), pathElm.getX());
                    double smallY = Math.min(lastDashLoc.getY(), pathElm.getY());
                    double smallZ = Math.min(lastDashLoc.getZ(), pathElm.getZ());
                    double bigX = Math.max(lastDashLoc.getX(), pathElm.getX());
                    double bigY = Math.max(lastDashLoc.getY(), pathElm.getY());
                    double bigZ = Math.max(lastDashLoc.getZ(), pathElm.getZ());
                    cordsLoop:
                    for (int x = (int) smallX; x <= bigX; x++) {
                        for (int y = (int) smallY; y <= bigY; y++) {
                            for (int z = (int) smallZ; z <= bigZ; z++) {
                                if (!AStarCustomPathFinder.checkPositionValidity(x, y, z, false)) {
                                    canContinue = false;
                                    break cordsLoop;
                                }
                            }
                        }
                    }
                }
                if (!canContinue) {
                    path.add(lastLoc.addVector(0.5, 0, 0.5));
                    lastDashLoc = lastLoc;
                }
            }
            lastLoc = pathElm;
            i++;
        }
        return path;
    }
    
    @EventTarget
    public void onClientTick(EventClientTick eventClientTick) {
        if (mc.player==null || mc.world == null || !mc.player.isEntityAlive()) {

            this.toggle();
            return;
        }



        farPlayHeartbeatTimer++;
        if (farPlayHeartbeatTimer > 20) {
            double x,y,z;
            x = farPlaySavePos.x;
            y = farPlaySavePos.y;
            z = farPlaySavePos.z;
            farPlaySending = true;
            mc.getConnection().sendPacket( new CPacketPlayer.PositionRotation(x,y,z, farPlaySaveYaw, farPlaySavePicth, true) );
//				this.displayChatMessage("farPlay heartbeat");
            farPlaySending = false;
            farPlayHeartbeatTimer = 0;
        }

        EntityPlayerSP player = mc.player;
        player.movementInput.updatePlayerMoveState();
        double angle = MathHelper.atan2(player.movementInput.moveStrafe,player.movementInput.moveForward) / Math.PI * 180;
        float f1= player.rotationPitch * 0.017453292F;
        float f = (player.rotationYaw-(float)angle) * 0.017453292F;
        double h_speed = this.speed_x.getValue();

        boolean sprinting = false;
        int jumpKeyCode = mc.gameSettings.keyBindSprint.getKeyCode();
        if (jumpKeyCode < 0) {
            sprinting = Mouse.isButtonDown(jumpKeyCode+100);
        } else {
            sprinting = Keyboard.isKeyDown(jumpKeyCode);
        }

        if (player.movementInput.moveStrafe == 0 && player.movementInput.moveForward == 0) {
            h_speed = 0;
        }
        // mod h_speed
        else if (!sprinting) {
            h_speed *= 0.5;
            h_speed = Math.min(h_speed, 0.5);
        }

        double v_speed = 0;

        if (player.movementInput.sneak)
        {
            v_speed = -this.speed_y.getValue()*2;
        }

        if (player.movementInput.jump)
        {
            v_speed += this.speed_y.getValue();
            //this.log(String.format("v_speed %s", v_speed));
        }


        player.motionX = -(double)(MathHelper.sin(f) * h_speed) * (player.collidedVertically ? 1 :1);
        player.motionZ = +(double)(MathHelper.cos(f) * h_speed) * (player.collidedVertically ? 1 :1);
        player.motionY = +(double)v_speed;

        if (fastEatTimer>0) {
            fastEatTimer--;
        }

        if (farPlayCdTimer>0) {
            ChatUtils.message(String.format("farPlayCdTimer: %s", farPlayCdTimer));
            farPlayCdTimer--;
        }

        if (this.farPlaySavePacketList.size() > 0) {
            boolean needTp = false;
            boolean mustNeedTp = false;
            for (int j=0; j<farPlaySavePacketList.size(); j++) {
                Packet packet = farPlaySavePacketList.get(j);
                if (packet instanceof CPacketAnimation) {
                }
                else if (packet instanceof CPacketClickWindow) {
                    needTp = true;
                    mustNeedTp = true;
                    break;
                }
                else if (packet instanceof CPacketEntityAction) {
                }
                else if (packet instanceof CPacketPlayerTryUseItemOnBlock) {
                    CPacketPlayerTryUseItemOnBlock c08p = (CPacketPlayerTryUseItemOnBlock)packet;
                    ItemStack itemStack = mc.player.getActiveItemStack();
                    if (itemStack!=null) {
                        Item item = itemStack.getItem();
                        if (item != null) {
                            EnumAction action = item.getItemUseAction(itemStack);
                            if (action == EnumAction.BOW || action == EnumAction.DRINK || action == EnumAction.EAT) {

                            }
                            else {
                                needTp = true;
                                mustNeedTp = true;
                                break;
                            }
                        }
                        else {
                            needTp = true;
                            mustNeedTp = true;
                            break;
                        }
                    }
                    else {
                        needTp = true;
                        mustNeedTp = true;
                        break;
                    }
                }
                else if (packet instanceof CPacketPlayerDigging) {
                    CPacketPlayerDigging c07p = (CPacketPlayerDigging)packet;
                    CPacketPlayerDigging.Action action = c07p.getAction();
                    if (action == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK || action == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                        needTp = true;
                        mustNeedTp = true;
                        break;
                    }
                    else if (action == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                        ItemStack itemStack = mc.player.getActiveItemStack();
                        Item item = itemStack == null ? null : itemStack.getItem();
                        if (item!=null && (item == Items.BOW)) {
                            needTp = true;
                            mustNeedTp = false;
                        }
                    }
                    else if (action == CPacketPlayerDigging.Action.DROP_ALL_ITEMS || action == CPacketPlayerDigging.Action.DROP_ITEM) {
                        needTp = true;
                        mustNeedTp = false;
                    }
                }
                else if (packet instanceof CPacketUseEntity) {
                    needTp = true;
                    mustNeedTp = true;
                    break;
                }
            }

            if ((!needTp) || (!mustNeedTp && this.farPlayCdTimer > 0)) {
                if (needTp && !mustNeedTp && this.farPlayCdTimer > 0) {
                    ChatUtils.message("wait but not must tp, org pos send");
                }
                farPlaySending = true;
                for (int j=0; j<farPlaySavePacketList.size(); j++) {
                    Packet packet = farPlaySavePacketList.get(j);
                    mc.getConnection().sendPacket( packet );
                }
                farPlaySending = false;
            }
            else if (this.farPlayCdTimer <= 0) {
                ChatUtils.message("send packet");
                farPlaySending = true;
                routeList = farPlayRoute( new Vec3(this.farPlaySavePos.x, this.farPlaySavePos.y, this.farPlaySavePos.z), new Vec3(mc.player.posX, mc.player.posY, mc.player.posZ));

                if (routeList.size() > 0) {
                    ChatUtils.message(String.format("route size: %s", routeList.size()));
                    ChatUtils.message("go");
                    for(int i=0; i<routeList.size();i++) {
//										this.log(routeList.get(i).toString());
                        Vec3 point = routeList.get(i);
                        mc.getConnection().sendPacket( new CPacketPlayer.Position( point.x, point.y, point.z, true ) );
                    }
                    mc.getConnection().sendPacket( new CPacketPlayer.Rotation(mc.player.rotationYaw,mc.player.rotationPitch,true) );

                    for (int j=0; j<farPlaySavePacketList.size(); j++) {
                        Packet packet = farPlaySavePacketList.get(j);
                        mc.getConnection().sendPacket( packet );
                    }
                    farPlayHeartbeatTimer = 0;
                    farPlayCdTimer = 2;
                    ChatUtils.message("back");
                    for(int i=routeList.size()-1; i>=0;i--) {

//										this.log(routeList.get(i).toString());
                        Vec3 point = routeList.get(i);
                        mc.getConnection().sendPacket( new CPacketPlayer.Position( point.x, point.y, point.z, true ) );
                    }
                    double x,y,z;
                    x = farPlaySavePos.x;
                    y = farPlaySavePos.y;
                    z = farPlaySavePos.z;
                    mc.getConnection().sendPacket( new CPacketPlayer.PositionRotation(x,y,z, farPlaySaveYaw, farPlaySavePicth, true) );
                }
                else {
                    ChatUtils.message(String.format("no route, failed"));
                }

                farPlaySending = false;
            }
            else {
                ChatUtils.message("wait..");
            }

            farPlaySavePacketList.clear();
        }
    }


    @EventTarget
    public void onRender3D(EventRenderWorldLast event) {
        for (Vec3 pos : routeList) {
            if (pos != null) {
                double width = mc.player.getEntityBoundingBox().maxX - mc.player.getEntityBoundingBox().minX;
                double height = mc.player.getEntityBoundingBox().maxY - mc.player.getEntityBoundingBox().minY;
                double x = pos.getX() - Wrapper.getRenderPosX();
                double y = pos.getY() - Wrapper.getRenderPosY();
                double z = pos.getZ() - Wrapper.getRenderPosZ();
                WorldRenderUtils.drawOutlinedEntityESP(x, y, z, width, height, 255f, 255f, 255f, 1);
            }
        }
    }


    @EventTarget
    public void onPacket(EventPacket eventPacket) {
        if (eventPacket.getSide() == Connection.Side.IN) {
            if (eventPacket.getPacket() instanceof SPacketPlayerPosLook) {
                eventPacket.setCancelled(true);
            }
            return;
        }
        if (farPlaySending)
            return;
        Packet<?> packet = (Packet<?>) eventPacket.getPacket();
        boolean shouldReturn = false;
        if (packet instanceof CPacketPlayer){
            eventPacket.setCancelled(true);
        }


        if (packet instanceof CPacketPlayer.Position || packet instanceof CPacketPlayer.PositionRotation) {
//				farPlaySavePacketList.add(packet);
            eventPacket.setCancelled(true);
        }
        else if (packet instanceof CPacketAnimation) {
            farPlaySavePacketList.add(packet);
            eventPacket.setCancelled(true);
        }
        else if (packet instanceof CPacketEntityAction) { // Cancel
            CPacketEntityAction c0Bp = (CPacketEntityAction)packet;
            if (c0Bp.getAction() == CPacketEntityAction.Action.START_SNEAKING
                    || c0Bp.getAction() == CPacketEntityAction.Action.START_SPRINTING
                    || c0Bp.getAction() == CPacketEntityAction.Action.STOP_SPRINTING ) {
                // farPlaySavePacketList.add(packet);
                ChatUtils.message("C0BPacketEntityAction");
                eventPacket.setCancelled(false);
            }
            else {
                eventPacket.setCancelled(true);
            }
            if (c0Bp.getAction() == CPacketEntityAction.Action.START_SNEAKING) {
                farPlaySneak = true;
            }

        }
        else if (packet instanceof CPacketUseEntity) {
            farPlaySavePacketList.add(packet);
            eventPacket.setCancelled(true);
        }
        else if (packet instanceof CPacketPlayerTryUseItem) {
            CPacketPlayerTryUseItem c08p = (CPacketPlayerTryUseItem)packet;
            ItemStack itemStack = mc.player.getHeldItem(c08p.getHand());
            if (itemStack!=null) {
                Item item = itemStack.getItem();
                if (item != null) {
                    EnumAction action = item.getItemUseAction(itemStack);
                    if (action == EnumAction.BOW || action == EnumAction.DRINK || action == EnumAction.EAT) {
                        if ( (action == EnumAction.DRINK || action == EnumAction.EAT) && (item instanceof ItemFood || item instanceof ItemBucketMilk || item instanceof ItemPotion)) {
                            if (fastEatTimer == 0) {
                                // this.displayChatMessage("eating need move, fast eat now");
                                this.farPlaySending = true;
                                mc.getConnection().sendPacket(packet);
                                for (int i=0; i<=35; i++) {
                                    mc.getConnection().sendPacket(new CPacketPlayer());
                                }
                                this.farPlayCdTimer = 2;
                                fastEatTimer = 20;
                                this.farPlaySending = false;
                            }
                            else {
                                ChatUtils.message("FarPlay Wait");
                            }
                            eventPacket.setCancelled(true);
                        }
                    }
                    else {
                        farPlaySavePacketList.add(packet);
                        eventPacket.setCancelled(true);
                    }
                }
                else {
                    farPlaySavePacketList.add(packet);
                    eventPacket.setCancelled(true);
                }
            }
            else {
                farPlaySavePacketList.add(packet);
                eventPacket.setCancelled(true);
            }
        }
        else if (packet instanceof CPacketPlayerDigging) {
            CPacketPlayerDigging c07p = (CPacketPlayerDigging)packet;

            CPacketPlayerDigging.Action action = c07p.getAction();
            if (action == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK || action == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                farPlaySavePacketList.add(packet);
                eventPacket.setCancelled(true);
            }
            else if (action == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                ItemStack itemStack = mc.player.getActiveItemStack();
                Item item = itemStack == null ? null : itemStack.getItem();
                if (item!=null && (item == Items.BOW)) {
                    farPlaySavePacketList.add(packet);
                    eventPacket.setCancelled(true);
                }
            }
            else if (action == CPacketPlayerDigging.Action.DROP_ALL_ITEMS || action == CPacketPlayerDigging.Action.DROP_ITEM) {
                farPlaySavePacketList.add(packet);
                eventPacket.setCancelled(true);
            }

        }
        else if (packet instanceof CPacketClickWindow) {
            CPacketClickWindow p = (CPacketClickWindow)packet;
            if (p.getActionNumber() == 4 || p.getSlotId() == -999) {
                farPlaySavePacketList.add(packet);
                eventPacket.setCancelled(true);
            }
        } else if (packet instanceof CPacketPlayerTryUseItemOnBlock) {
            CPacketPlayerTryUseItemOnBlock c08p = (CPacketPlayerTryUseItemOnBlock)packet;
            ItemStack itemStack = mc.player.getHeldItem(c08p.getHand());
            if (itemStack!=null) {
                Item item = itemStack.getItem();
                if (item != null) {
                    EnumAction action = item.getItemUseAction(itemStack);
                    if (action == EnumAction.BOW || action == EnumAction.DRINK || action == EnumAction.EAT) {
                        if ( (action == EnumAction.DRINK || action == EnumAction.EAT) && (item instanceof ItemFood || item instanceof ItemBucketMilk || item instanceof ItemPotion)) {
                            if (fastEatTimer == 0) {
                                // this.displayChatMessage("eating need move, fast eat now");
                                this.farPlaySending = true;
                                mc.getConnection().sendPacket(packet);
                                for (int i=0; i<=35; i++) {
                                    mc.getConnection().sendPacket(new CPacketPlayer());
                                }
                                this.farPlayCdTimer = 2;
                                fastEatTimer = 20;
                                this.farPlaySending = false;
                            }
                            else {
                                ChatUtils.message("FarPlay Wait");
                            }
                            eventPacket.setCancelled(true);
                        }
                    }
                    else {
                        farPlaySavePacketList.add(packet);
                        eventPacket.setCancelled(true);
                    }
                }
                else {
                    farPlaySavePacketList.add(packet);
                    eventPacket.setCancelled(true);
                }
            }
            else {
                farPlaySavePacketList.add(packet);
                eventPacket.setCancelled(true);
            }
        }
    }


}
