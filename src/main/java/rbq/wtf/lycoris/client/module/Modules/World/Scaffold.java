package rbq.wtf.lycoris.client.module.Modules.World;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventCameraSetup;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.event.events.EventLeftClickBlock;
import rbq.wtf.lycoris.client.event.events.EventRenderWorldLast;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.*;
import rbq.wtf.lycoris.client.utils.Render.RenderUtils;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.Random;

public class Scaffold extends Module {


    public ModeValue mode = new ModeValue("Mode",new String[]{"Simple","AAC"},0,1);

    public TimerUtils timer;
    public BlockData blockData;
    boolean isBridging = false;
    BlockPos blockDown = null;
    public static float[] facingCam = null;
    float startYaw = 0;
    float startPitch = 0;
    public Scaffold (){
        super("Scaffold", ModuleCategory.World,0);
        this.addModeValue(mode);
    }

    @Override
    public void onDisable() {
        facingCam = null;
        super.onDisable();
    }

    @Override
    public void onEnable() {
        blockDown = null;
        facingCam = null;
        isBridging = false;
        startYaw = 0;
        startPitch = 0;
        if(mode.getCurrentSelectionName().equals("AAC") && Wrapper.INSTANCE.mcSettings().keyBindBack.isKeyDown()) {
            KeyBinding.setKeyBindState(Wrapper.INSTANCE.mcSettings().keyBindBack.getKeyCode(), false);
        }
        super.onEnable();
    }
    @EventTarget
    public void onClientTick(EventClientTick event) {
        if(mode.getCurrentSelectionName().equals("AAC")) {
            AAC();
        } else if(mode.getCurrentSelectionName().equals("Simple")){
            Simple();
        }
    }

    @EventTarget
    public void onRenderWorldLast(EventRenderWorldLast event) {
        if(blockDown != null) {
            RenderUtils.drawBlockESP(blockDown, 1F, 1F, 1F);
            if(mode.getCurrentSelectionName().equals("AAC")) {
                BlockPos blockDown2 = new BlockPos(Wrapper.INSTANCE.player()).down();
                BlockPos blockDown3 = new BlockPos(Wrapper.INSTANCE.player()).down();
                if(Wrapper.INSTANCE.player().getHorizontalFacing() == EnumFacing.EAST) {
                    blockDown2 = new BlockPos(Wrapper.INSTANCE.player()).down().west();
                    blockDown3 = new BlockPos(Wrapper.INSTANCE.player()).down().west(2);
                }
                else if(Wrapper.INSTANCE.player().getHorizontalFacing() == EnumFacing.NORTH) {
                    blockDown2 = new BlockPos(Wrapper.INSTANCE.player()).down().south();
                    blockDown3 = new BlockPos(Wrapper.INSTANCE.player()).down().south(2);
                }
                else if(Wrapper.INSTANCE.player().getHorizontalFacing() == EnumFacing.SOUTH) {
                    blockDown2 = new BlockPos(Wrapper.INSTANCE.player()).down().north();
                    blockDown3 = new BlockPos(Wrapper.INSTANCE.player()).down().north(2);
                }
                else if(Wrapper.INSTANCE.player().getHorizontalFacing() == EnumFacing.WEST) {
                    blockDown2 = new BlockPos(Wrapper.INSTANCE.player()).down().east();
                    blockDown3 = new BlockPos(Wrapper.INSTANCE.player()).down().east(2);
                }
                RenderUtils.drawBlockESP(blockDown2, 1F, 0F, 0F);
                RenderUtils.drawBlockESP(blockDown3, 1F, 0F, 0F);
            }
        }

    }

    @EventTarget
    public void onCameraSetup(EventCameraSetup event) {
        if(mode.getCurrentSelectionName().equals("AAC") && event.getEvent().getEntity() == Wrapper.INSTANCE.player()) {
            if(startYaw == 0 || startPitch == 0) {
                return;
            }
            event.getEvent().setYaw(startYaw);
            event.getEvent().setPitch(startPitch - 70);
            facingCam = new float[] { startYaw - 180, startPitch - 70};
        }
    }

    void AAC() {
        EntityPlayerSP player = Wrapper.INSTANCE.player();
        int oldSlot = -1;
        if(!check()) {
            if(isBridging) {
                KeyBinding.setKeyBindState(Wrapper.INSTANCE.mcSettings().keyBindSneak.getKeyCode(),
                        BlockUtils.isBlockMaterial(new BlockPos(player).down(), Blocks.AIR));
                isBridging = false;
                if(oldSlot != -1) {
                    player.inventory.currentItem = oldSlot;
                }
            }
            startYaw = 0;
            startPitch = 0;
            facingCam = null;
            blockDown = null;
            return;
        }
        startYaw = Wrapper.INSTANCE.player().rotationYaw;
        startPitch = Wrapper.INSTANCE.player().rotationPitch;
        KeyBinding.setKeyBindState(Wrapper.INSTANCE.mcSettings().keyBindRight.getKeyCode(), false);
        KeyBinding.setKeyBindState(Wrapper.INSTANCE.mcSettings().keyBindLeft.getKeyCode(), false);
        blockDown = new BlockPos(player).down();
        float r1 = new Random().nextFloat();
        if(r1 == 1.0f) r1--;
        int newSlot = findSlotWithBlock();
        if (newSlot == -1) return;
        oldSlot = player.inventory.currentItem;
        player.inventory.currentItem = newSlot;
        player.rotationPitch = Utils.updateRotation(player.rotationPitch, (82.0f - r1), 15.0f);
        int currentCPS = Utils.random(3, 4);
        if(timer.isDelay(1000 / currentCPS)) {
            RobotUtils.clickMouse(1);
            Utils.swingMainHand();
            timer.setLastMS();
        }
        isBridging = true;
        KeyBinding.setKeyBindState(Wrapper.INSTANCE.mcSettings().keyBindSneak.getKeyCode(),
                BlockUtils.isBlockMaterial(new BlockPos(player).down(), Blocks.AIR));
    }

    void Simple() {
        blockDown = new BlockPos(Wrapper.INSTANCE.player()).down();
        if (!BlockUtils.getBlock(blockDown).getMaterial(BlockUtils.getBlock(blockDown).getDefaultState()).isReplaceable()) return;
        int newSlot = findSlotWithBlock();
        if (newSlot == -1) return;
        final int oldSlot = Wrapper.INSTANCE.inventory().currentItem;
        Wrapper.INSTANCE.inventory().currentItem = newSlot;
        Utils.placeBlockScaffold(blockDown);
        Wrapper.INSTANCE.inventory().currentItem = oldSlot;
    }

    public int findSlotWithBlock() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Wrapper.INSTANCE.inventory().getStackInSlot(i);
            if(stack != null && stack.getItem() instanceof ItemBlock) {
                Block block = Block.getBlockFromItem(stack.getItem()).getDefaultState().getBlock();
                if(block.isFullBlock(BlockUtils.getBlock(blockDown).getDefaultState()) && block != Blocks.SAND && block != Blocks.GRAVEL){
                    return i;
                }
            }
        }
        return -1;
    }

    boolean check() {
        RayTraceResult object = Wrapper.INSTANCE.mc().objectMouseOver;
        EntityPlayerSP player = Wrapper.INSTANCE.player();
        ItemStack stack = player.inventory.getCurrentItem();
        if(object == null || stack == null) {
            return false;
        }
        if(object.typeOfHit != RayTraceResult.Type.BLOCK) {
            return false;
        }
        if(player.rotationPitch <= 70 || !player.onGround || player.isOnLadder() || player.isInLava() || player.isInWater()) {
            return false;
        }
        if(!Wrapper.INSTANCE.mcSettings().keyBindBack.isKeyDown()) {
            return false;
        }
        return true;
    }

}
