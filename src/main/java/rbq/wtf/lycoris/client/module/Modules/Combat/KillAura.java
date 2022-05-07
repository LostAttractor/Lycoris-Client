package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventPacket;
import rbq.wtf.lycoris.client.event.events.EventPostUpdate;
import rbq.wtf.lycoris.client.event.events.EventPreUpdate;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.module.Modules.World.Teams;
import rbq.wtf.lycoris.client.utils.*;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;


import javax.vecmath.Vector3d;
import java.awt.*;
import java.util.*;

public class KillAura extends Module {

    static Minecraft mc = Minecraft.getMinecraft();

    public static Random random = new Random();

    static double healthBarTarget = 0, healthBar = 0;
    float lastHealth = 0;
    double delay = 0;
    boolean step = false;

    // 实体变量
    public static EntityLivingBase curtarget = null;
    // 转头
    private boolean AIMed;
    float[] lastRotations;
    float upAndDownPitch = 0;
    public static float[] serverRotation;
    float cYaw;
    float cPitch;
    float rotationYawHead;
    public static Angles serverAngles = new Angles(0f, 0f);
    public static AnglesUtils aimUtil = new AnglesUtils(15, 30, 15, 30);
    // 开关
    public static boolean isBlocking = false;
    public int index;
    // TimeHelper
    public TimeHelper attacktimer = new TimeHelper();
    private TimeHelper switchTimer = new TimeHelper();
    private TimeHelper critTimer = new TimeHelper();
    // Utils
    public static ArrayList<EntityLivingBase> targets = new ArrayList<EntityLivingBase>();
    public static ArrayList<EntityLivingBase> attacked = new ArrayList<EntityLivingBase>();

    public static BooleanValue autoBlock = new BooleanValue("AutoBlock", true);
    public static NumberValue  reach = new NumberValue ("Range", 4.2, 3.0, 6.0, 1.0f);
    public static NumberValue  switchsize = new NumberValue ("MaxTargets", 1.0, 1.0, 5.0, 1f);
    public static NumberValue cps = new NumberValue("CPS", 10.0, 1.0, 20.0, 1.0);
    public static NumberValue blockReach = new NumberValue("BlockRange", 0.5, 0.0, 6.0, 1.0);
    public static BooleanValue attackPlayers = new BooleanValue("Players", true);
    public static BooleanValue attackAnimals = new BooleanValue("Animals", false);
    public static BooleanValue attackMobs = new BooleanValue("Mobs", false);
    public static BooleanValue throughblock = new BooleanValue("ThroughBlock", true);
    public static BooleanValue invisible = new BooleanValue("Invisible", false);
    public NumberValue switchDelay = new NumberValue("SwitchDelay", 50d, 0d, 2000d, 0.1d);
    public static NumberValue rotation2Value = new NumberValue("YawOffset", 200d, 0d, 400d, 10d);
    public static NumberValue rotationValue = new NumberValue("PitchOffset", 200d, 0d, 400d, 10d);
    public static BooleanValue randoms2 = new BooleanValue("RandomPitch", true);
    public BooleanValue autodisable = new BooleanValue("AutoDisable", false);
    public BooleanValue targetHUD = new BooleanValue("TargetHUD", true);
    public BooleanValue forcerise = new BooleanValue("Slient", true);
    public BooleanValue esp = new BooleanValue("ESP", true);
    public BooleanValue morekb = new BooleanValue("MoreKnockBack", false);

    public static NumberValue turn = new NumberValue("TurnHeadSpeed", 15.0, 5.0, 360.0, 1.0);

    public ModeValue AimMode = new ModeValue( "AimMode", new String[]{"Client","ClientLock","Packet"},0,2);


    public ModeValue rotation = new ModeValue( "Rotations", new String[]{"Null","LiquidBounce","Legit","Instant","Instant2","Custom","New"},0,6);
    public ModeValue priority = new ModeValue("Priority", new String[]{"Angle","Range","Armor","Health","Fov"},3,4);
    public ModeValue EspMode = new ModeValue("EspMode", new String[]{"Circle","None"},0,1);
    public ModeValue hudMode = new ModeValue( "HudMode", new String[]{"Simple","Debug","Fancy","Skid","Ice","Latest"},0,5);
    public ModeValue FixMode = new ModeValue( "FixMode", new String[]{"None","Old","New"},0,2);


    public KillAura() {
        super("KillAura", ModuleCategory.Combat,0);
        this.addBooleanValue(autoBlock);
        this.addBooleanValue(attackPlayers);
        this.addBooleanValue(attackAnimals);
        this.addBooleanValue(attackMobs);
        this.addBooleanValue(invisible);
        this.addBooleanValue(throughblock);
        this.addBooleanValue(randoms2);
        this.addBooleanValue(autodisable);
        this.addBooleanValue(forcerise);
        this.addBooleanValue(morekb);

        this.addNumberValue(cps);
        this.addNumberValue(reach);
        this.addNumberValue(blockReach);
        this.addNumberValue(switchsize);
        this.addNumberValue(switchDelay);
        this.addNumberValue(rotation2Value);
        this.addNumberValue(rotationValue);
        this.addNumberValue(turn);

        this.addModeValue(rotation);
        this.addModeValue(priority);
        this.addModeValue(FixMode);
        this.addModeValue(AimMode);

    }
    @Override
    public void onEnable() {
        attacked = new ArrayList<EntityLivingBase>();
        healthBar = new ScaledResolution(mc).getScaledWidth() / 2 - 41;
        if (mc.player != null) {
            lastRotations = new float[]{mc.player.rotationYaw, mc.player.rotationPitch};
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        healthBar = new ScaledResolution(mc).getScaledWidth() / 2 - 41;
        if (isBlocking) { // 格挡
            unBlock(true);
        }
        targets.clear();
        curtarget = null; // 清空目标 (AutoBlock动画修复)
        super.onDisable();
    }


    private void doAttack() {
        // 算CPS - Delay
        if (ShouldAttack()) { // 攻击Timer
            boolean isInRange = mc.player.getDistance(curtarget) <= reach.getValue();
            if (isInRange) {
                attack(); // 攻击传参miss
                attacktimer.reset();
            }
        }
    }

    private void attack() {
        //EventCaller.call(new EventAttack(target));
        if(!isValidEntity(curtarget)) {
            targets.remove(curtarget);
            curtarget = null;
        }
//        System.out.println(curtarget);
        if (mc.player.getFoodStats().getFoodLevel() > 6 && morekb.getValue()) {
            Wrapper.INSTANCE.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
        //Critical
        //   if (critTimer.isDelayComplete(Criticals.delay.getValue().floatValue().floatValue())) {
//        Critical.doCrit();
        //  critTimer.reset();
        // }

        mc.player.swingArm(EnumHand.MAIN_HAND);
        Wrapper.INSTANCE.sendPacket(new CPacketUseEntity(curtarget)); // 攻击

        if (mc.player.getFoodStats().getFoodLevel() > 6 && morekb.getValue()) {
            Wrapper.INSTANCE.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        }
        // AutoSword.publicItemTimer.reset();
        if (!attacked.contains(curtarget) && curtarget instanceof EntityPlayer) {
            attacked.add(curtarget);
        }

    }

    private void doBlock(boolean setItemUseInCount) {
        //   if (setItemUseInCount)
        mc.playerController.onStoppedUsingItem(mc.player);

//        mc.playerController.item((EntityPlayer)mc.player, mc.world, mc.player.getCurrentEquippedItem());

        //    mc.player.setItemInUseCount(mc.player.getHeldItem().getMaxItemUseDuration());

        Wrapper.INSTANCE.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        isBlocking = true;
    }

    private void unBlock(boolean setItemUseInCount) {
        //  if (setItemUseInCount)
        mc.playerController.onStoppedUsingItem(mc.player);
        // mc.player.setItemInUseCount(0);
        Wrapper.INSTANCE.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
        isBlocking = false;
    }


    @EventTarget
    public void onPre(EventPreUpdate event) {

        rotationYawHead = mc.player.rotationYawHead;

        // 初始化变量
        if (!targets.isEmpty() && index >= targets.size())
            index = 0; // 超过Switch限制

        try {
            for (EntityLivingBase ent : targets) { // 添加实体
                if (isValidEntity(ent)) {
                    continue;
                } else {
                    targets.remove(ent);
                }
            }
        }catch (ConcurrentModificationException ignored){}
        // Switch结束

        getTarget(); // 拿实体

        if (targets.size() == 0) { // 实体数量为0停止攻击
            curtarget = null;
        } else {
            curtarget = targets.get(index);// 设置攻击的Target
            if (mc.player.getDistance( curtarget) > reach.getValue()) {
                curtarget = targets.get(0);
            }
        }

        // Switch开始
        if ( curtarget != null) {
            setAngles( curtarget);

            // Switch开始
            if ( curtarget.hurtTime == 10 && switchTimer.isDelayComplete(switchDelay.getValue())
                    && targets.size() > 1) {
                switchTimer.reset();

                ++index;
            }


            if (rotation.isCurrentMode("Null")) {
                lastRotations = AimUtil.getRotations(targets.get(index));
            } else if (rotation.isCurrentMode("New")) {
                Vec3d playerVec = new Vec3(event.getX(), event.getY() + (double) mc.player.getEyeHeight(), event.getZ()).mc();
                Vec3d targetVec =  curtarget.getPositionVector();
                targetVec = targetVec.y - 0.5 > event.getY() ? targetVec.addVector(0.0, (double) ( curtarget.getEyeHeight() / 2.2f), 0.0) : (targetVec.y > event.getY() ? targetVec.addVector(0.0, (double) ( curtarget.getEyeHeight() / 1.19f), 0.0) : targetVec.addVector(0.0, (double)  curtarget.getEyeHeight(), 0.0));
                targetVec = targetVec.addVector( curtarget.posX -  curtarget.lastTickPosX, 0.0,  curtarget.posZ -  curtarget.lastTickPosZ);
                lastRotations = RotationUtil.getNeededFacing(targetVec,playerVec);
            } else if (rotation.isCurrentMode("Custom")) {
                lastRotations = RotationUtil.getNeededRotations(AimUtil.getCenter(targets.get(index).getEntityBoundingBox()));
            } else if (rotation.isCurrentMode("Legit")) {
                lastRotations = AimUtil.faceTarget( curtarget, Math.max(10, this.getFoVDistance(event.getYaw(),  curtarget) * 0.8f), 40, false);
            } else if (rotation.isCurrentMode("Instant")) {
                lastRotations = AimUtil.getRotationsToEntity(targets.get(index));
            }
            float sens = getSensitivityMultiplier();
            float yaw = (float) (lastRotations[0] + KillAura.getRandomDoubleInRange(-0.15F, 0.15F));
            float pitch = (float) (lastRotations[1] + KillAura.getRandomDoubleInRange(-1.0F, 1.0F));
            float yawGCD = (Math.round(yaw / sens) * sens);
            float pitchGCD = (Math.round(pitch / sens) * sens);
            if (Math.abs(pitchGCD) > 90) {
                pitchGCD = 90;
            }
            if (Math.abs(pitchGCD) == Math.round(Math.abs(pitchGCD))) {
                pitchGCD += (Math.round(Math.random() * 2 / sens) * sens);
            }
            if (lastRotations[1] > 90) {
                lastRotations[1] = 90;
            } else if (lastRotations[1] < -90) {
                lastRotations[1] = -90;
            }

            float rotationSpeed = turn.getValue();
            float rotationYaw = MathHelper.clamp((RotationUtil.getYawChangeGiven(targets.get(index).posX, targets.get(index).posZ, lastRotations[0])), -90.0f, 90.0f);
            rotationYaw = rotationYaw > rotationSpeed ? rotationSpeed : Math.max(rotationYaw, -rotationSpeed);

            if (lastRotations[1] < -90) {
                lastRotations[1] = -90;
            }


            if (FixMode.isCurrentMode("Old")) {
                lastRotations = NormalRes(new float[]{(float) ((lastRotations[0] += rotationYaw / 1.2f) + KillAura.getRandomDoubleInRange(-3, 3)), (float) (lastRotations[1] / 1.2f + KillAura.getRandomDoubleInRange(-5, 5))});
            } else if (FixMode.isCurrentMode("New")) {
                lastRotations = new float[]{yawGCD, pitchGCD};
            }

            if (!forcerise.getValue()) {
                mc.player.rotationYaw = lastRotations[0];
                mc.player.rotationPitch = lastRotations[1];
            } else {
                event.setYaw(lastRotations[0]);
                event.setPitch(lastRotations[1]);
            }

            if (mc.gameSettings.thirdPersonView == 1) {
                mc.player.rotationYawHead = event.getYaw();
                mc.player.renderYawOffset = event.getYaw();
            }

            if (mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword && autoBlock.getValue() && isBlocking) { // 格挡
                unBlock(true);
            }

        } else {
            targets.clear();
            lastRotations = new float[]{mc.player.rotationYaw, mc.player.rotationPitch};
            if (mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword
                    && autoBlock.getValue() && isBlocking) {
                unBlock(true);
            }
        }
        event.setCancel(true);
    }

    @EventTarget
    public void onPakcet(EventPacket event){
        final Packet<?> packet = (Packet<?>) event.getPacket();

        if(packet instanceof CPacketPlayer) {
            final CPacketPlayer packetPlayer = (CPacketPlayer) packet;
            serverRotation = new float[]{packetPlayer.getYaw(lastRotations[0]),packetPlayer.getPitch(lastRotations[1])};
        }
    }
    @EventTarget
    public void onPost(EventPostUpdate event) {
        if (curtarget != null)
            doAttack(); // 攻击
        // 开格挡
//        if (curtarget != null && (mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword
//                && autoBlock.getValue()  && !isBlocking)) { // 格挡
//            doBlock(true);
//        }
    }

//    @EventTarget
//    public void onWorldChange(EventWorldChange e) {
//        if (autodisable.getState()) {
//            this.setState(false);
//        }
//    }

    private void getTarget() {
        int maxSize = (int)switchsize.getValue().floatValue(); // 最大实体数量

//        if (maxSize > 1) {
//            this.setSuffix("Switch");
//        } else {
//            this.setSuffix("Single");
//        }

        for (Entity o3 : WorldUtil.getEntities()) { // 遍历实体
            EntityLivingBase curEnt;
            if (o3 instanceof EntityLivingBase && isValidEntity(curEnt = (EntityLivingBase) o3)
                    && !targets.contains(curEnt)) {

                targets.add(curEnt);
            }
            if (targets.size() >= maxSize)
                break; // 超过了限制跳出
        }

        // 排序目标实体
//        if (priority.getCurrentSelectionName().equals("Armor")) {
//            targets.sort(Comparator.comparingInt((o) -> {
//                return o instanceof EntityPlayer ? ((EntityPlayer) o).inventory.getbe()
//                        : (int) o.getHealth();
//            }));
//        }

        if (priority.getCurrentSelectionName().equals("Range")) {
            targets.sort((o1, o2) -> (int) (o1.getDistance(mc.player) - o2.getDistance(mc.player)));
        }
        if (priority.getCurrentSelectionName().equals("Fov")) {
            targets.sort(Comparator.comparingDouble(o -> RotationUtil.getDistanceBetweenAngles(mc.player.rotationPitch,
                    RotationUtil.getRotationToEntityF(o)[0])));
        }
        if (priority.getCurrentSelectionName().equals("Angle")) {
            targets.sort((o1, o2) -> {
                float[] rot1 = RotationUtil.getRotationToEntityF(o1);
                float[] rot2 = RotationUtil.getRotationToEntityF(o2);
                return (int) (mc.player.rotationYaw - rot1[0] - (mc.player.rotationYaw - rot2[0]));
            });
        }
        if (priority.getCurrentSelectionName().equals("Health")) {
            targets.sort((o1, o2) -> (int) (o1.getHealth() - o2.getHealth()));
        }
    }
    public static void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 255) / 255.0f;
        float red = (float) (hex >> 16 & 255) / 255.0f;
        float green = (float) (hex >> 8 & 255) / 255.0f;
        float blue = (float) (hex & 255) / 255.0f;
        GL11.glColor4f((float) red, (float) green, (float) blue, (float) (alpha == 0.0f ? 1.0f : alpha));
    }
    public static void enableSmoothLine(float width) {
        GL11.glDisable((int) 3008);
        GL11.glEnable((int) 3042);
        GL11.glBlendFunc((int) 770, (int) 771);
        GL11.glDisable((int) 3553);
        GL11.glDisable((int) 2929);
        GL11.glDepthMask((boolean) false);
        GL11.glEnable((int) 2884);
        GL11.glEnable((int) 2848);
        GL11.glHint((int) 3154, (int) 4354);
        GL11.glHint((int) 3155, (int) 4354);
        GL11.glLineWidth((float) width);
    }
    public static void disableSmoothLine() {
        GL11.glEnable((int) 3553);
        GL11.glEnable((int) 2929);
        GL11.glDisable((int) 3042);
        GL11.glEnable((int) 3008);
        GL11.glDepthMask((boolean) true);
        GL11.glCullFace((int) 1029);
        GL11.glDisable((int) 2848);
        GL11.glHint((int) 3154, (int) 4352);
        GL11.glHint((int) 3155, (int) 4352);
    }
    private float random(float min, float max) {
        return (float) ((double) min + (double) (max - min) * Math.random());
    }
    public static int getHealthColor(final EntityLivingBase player) {
        final float f = player.getHealth();
        final float f2 = player.getMaxHealth();
        final float f3 = Math.max(0.0f, Math.min(f, f2) / f2);
        return Color.HSBtoRGB(f3 / 3.0f, 1.0f, 0.75f) | 0xFF000000;
    }
    public float[] mouseFix(float yaw, float pitch) {
        float k = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float k1 = k * k * k * 8.0F;
        yaw -= yaw % k1;
        pitch -= pitch % k1;
        return new float[]{yaw, pitch};
    }
    private float getFoVDistance(final float yaw, final Entity e) {
        return ((Math.abs(RotationUtil.getRotations((EntityLivingBase) e)[0] - yaw) % 360.0f > 180.0f) ? (360.0f - Math.abs(RotationUtil.getRotations((EntityLivingBase) e)[0] - yaw) % 360.0f) : (Math.abs(RotationUtil.getRotations((EntityLivingBase) e)[0] - yaw) % 360.0f));
    }
    public static float getSensitivityMultiplier() {
        float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6F + 0.2F;
        return (f * f * f * 8.0F) * 0.15F;
    }
    public boolean canBlock() {
        return autoBlock.getValue() && mc.player.inventory.getCurrentItem() != null
                && mc.player.inventory.getCurrentItem().getItem() instanceof ItemSword;
    }
    private int randomNumber(int max, int min) {
        return (int) (Math.random() * (double) (max - min)) + min;
    }
    public boolean ShouldAttack() {
        float aps = cps.getValue();
        return attacktimer.isDelayComplete(randomClickDelay(aps - 2, aps + 2));
    }
    public static void setAngles(EntityLivingBase targ) {
        if (targ != null) {
            Vector3d enemy = new Vector3d(targ.posX, targ.posY, targ.posZ);
            Vector3d me = new Vector3d(mc.player.posX, mc.player.posY, mc.player.posZ);
            Angles dstAngle = aimUtil.calculateAngle(enemy, me);
            Angles srcAngle = new Angles(serverAngles.getYaw(), serverAngles.getPitch());
            serverAngles = aimUtil.smoothAngle(srcAngle, dstAngle);
        }
    }
    public static double getRandomDoubleInRange(double minDouble, double maxDouble) {
        return minDouble >= maxDouble ? minDouble : new Random().nextDouble() * (maxDouble - minDouble) + minDouble;
    }
    public static float getDistanceBetweenAngles(float angle1, float angle2) {
        float angle = Math.abs(angle1 - angle2) % 360.0f;
        if (angle > 180.0f) {
            angle = 360.0f - angle;
        }
        return angle;
    }
    public static long randomClickDelay(final double minCPS, final double maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
    private static boolean isValidEntity(Entity entity) {
        if (entity instanceof EntityLivingBase) {
            if (entity.isDead || ((EntityLivingBase) entity).getHealth() <= 0f) {
                return false;
            }
            if (mc.player.getDistance(entity) < (reach.getValue() + blockReach.getValue())) {
                if (entity != mc.player && !mc.player.isDead
                        && !(entity instanceof EntityArmorStand || entity instanceof EntitySnowman)) {
                    if (entity instanceof EntityPlayer && attackPlayers.getValue()) {
                        if (!mc.player.canEntityBeSeen(entity) && !throughblock.getValue())
                            return false;
                        if (entity.isInvisible() && !invisible.getValue())
                            return false;
                        if (!ValidUtils.isTeam((EntityLivingBase) entity)) {
                            return false;
                        }
                    }

                    if ((entity instanceof EntityMob || entity instanceof EntitySlime) && !attackMobs.getValue()) {
                        return false;
                    }

                    if ((entity instanceof EntityAnimal || entity instanceof EntityVillager)
                            && !attackAnimals.getValue()) {

                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
    double normalise(double value, double start, double end) {
        double width = end - start;   //
        double offsetValue = value - start;   // value relative to 0

        return (offsetValue - (Math.floor(offsetValue / width) * width)) + start;
    }
    private void drawFace(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight, AbstractClientPlayer target) {
        try {
            ResourceLocation skin = target.getLocationSkin();
            mc.getTextureManager().bindTexture(skin);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1, 1, 1, 1);
            Gui.drawScaledCustomSizeModalRect(x, y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
            GL11.glDisable(GL11.GL_BLEND);
        } catch (Exception ignored) {
        }
    }
    public float randomFloat(float min, float max) {
        return min + (this.random.nextFloat() * (max - min));
    }
    public float[] NormalRes(float[] rot) {
        float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float f1 = f * f * f * 1.2F;
        return new float[]{rot[0] - (rot[0] % f1), rot[1] - (rot[1] % f1)};
    }
    public float[] PrefetFakeNormalRes(float[] rot) {
        float yaw = rot[0];
        float pitch = rot[1];
        float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float pos = f * f * f * 8.0F;
        yaw += pos * 0.15F;
        pitch -= pos * 0.15F;
        return new float[]{yaw, pitch};
    }
    public float[] getRotations(Entity theEntity) {
        double xDistance = theEntity.posX - this.mc.player.posX;
        double zDistance = theEntity.posZ - this.mc.player.posZ;
        double yDistance = theEntity.posY + ((double) theEntity.getEyeHeight() - 0.1D) / 1.4D - this.mc.player.posY - (double) this.mc.player.getEyeHeight() / 1.4D;
        double angleHelper = (double) MathHelper.sqrt(xDistance * xDistance + zDistance * zDistance);
        float newYaw = (float) Math.toDegrees(-Math.atan(xDistance / zDistance));
        float newPitch = (float) (-Math.toDegrees(Math.atan(yDistance / angleHelper)));
        if (zDistance < 0.0D && xDistance < 0.0D) {
            newYaw = (float) (90.0D + Math.toDegrees(Math.atan(zDistance / xDistance)));
        } else if (zDistance < 0.0D && xDistance > 0.0D) {
            newYaw = (float) (-90.0D + Math.toDegrees(Math.atan(zDistance / xDistance)));
        }

        return new float[]{newYaw, newPitch};
    }
    public static float getAngleDifference(float a, float b) {
        float dist = (a - b + 360.0F) % 360.0F;
        if (dist > 180.0F) {
            dist = 360.0F - dist;
        }

        return Math.abs(dist);
    }

    public static class AimUtil {
        public static float set(float f1, float f2, float f3) {
            float f = MathHelper.wrapDegrees(f2 - f1);
            if (f > f3) {
                f = f3;
            }
            if (f < -f3) {
                f = -f3;
            }
            return f1 + f;
        }
        private static float[] getRotations(EntityLivingBase ent) {
            final double x = ent.posX - mc.player.posX;
            double y = ent.posY - mc.player.posY;
            final double z = ent.posZ - mc.player.posZ;
            y /= mc.player.getDistance(ent);
            final float yaw = (float) (-(Math.atan2(x, z) * 57.29577951308232));
            final float pitch = (float) (-(Math.asin(y) * 57.29577951308232));
            return new float[]{yaw, pitch};
        }
        public static Vec3d getCenter(AxisAlignedBB bb) {
            double value = Math.random();
            return new Vec3d(bb.minX + (bb.maxX - bb.minX) * ((rotation2Value.getValue() / 400)),
                    bb.minY + (bb.maxY - bb.minY) * (randoms2.getValue() ? value : (rotationValue.getValue() / 400)), bb.minZ + (bb.maxZ - bb.minZ) * ((rotation2Value.getValue() / 400)));
        }
        public static float[] faceTarget(final Entity target, final float p_706252, final float p_706253, final boolean miss) {
            final double var4 = target.posX - mc.player.posX;
            final double var5 = target.posZ - mc.player.posZ;
            double var7;
            if (target instanceof EntityLivingBase) {
                final EntityLivingBase var6 = (EntityLivingBase) target;
                var7 = var6.posY + var6.getEyeHeight() - (mc.player.posY + mc.player.getEyeHeight());
            } else {
                var7 = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0
                        - (mc.player.posY + mc.player.getEyeHeight());
            }
            final Random rnd = new Random();
            final double var8 = MathHelper.sqrt(var4 * var4 + var5 * var5);
            final float var9 = (float) (Math.atan2(var5, var4) * 180.0 / 3.141592653589793) - 90.0f;
            final float var10 = (float) (-(Math.atan2(var7 - ((target instanceof EntityPlayer) ? 0.25 : 0.0), var8) * 180.0
                    / 3.141592653589793));
            final float pitch = changeRotation(mc.player.rotationPitch, var10, p_706253);
            final float yaw = changeRotation(mc.player.rotationYaw, var9, p_706252);
            return new float[]{yaw, pitch};
        }
        private static float changeRotation(final float var1, final float var2, final float var3) {
            float var4 = MathHelper.wrapDegrees(var2 - var1);
            if (var4 > var3) {
                var4 = var3;
            }
            if (var4 < -var3) {
                var4 = -var3;
            }
            return var1 + var4;
        }
        public static EntityPlayerSP getPlayer() {
            return mc.getMinecraft().player;
        }
        public static float[] getRotationsToEntity(Entity entity) {
            final EntityPlayerSP player = getPlayer();
            double xDist = entity.posX - player.posX;
            double zDist = entity.posZ - player.posZ;
            double entEyeHeight = entity.getEyeHeight();
            double yDist = ((entity.posY + entEyeHeight) - Math.min(Math.max(entity.posY - player.posY, 0), entEyeHeight)) -
                    (player.posY + player.getEyeHeight());
            double fDist = MathHelper.sqrt(xDist * xDist + zDist * zDist);
            float rotationYaw = getPlayer().rotationYaw;
            float value = (float) (Math.atan2(zDist, xDist) * 180.0D / Math.PI) - 90.0F;
            float yaw = rotationYaw + MathHelper.wrapDegrees(value - rotationYaw);
            float rotationPitch = getPlayer().rotationPitch;
            float yoffset = (float) (-(Math.atan2(yDist, fDist) * 180.0D / Math.PI));
            float pitch = rotationPitch + MathHelper.wrapDegrees(yoffset - rotationPitch);
            return new float[]{(float) (yaw), (float) (MathHelper.clamp(pitch, -180.0f, 180.0f))};
        }
        public static float changeRotation(final float p_706631, final float p_706632) {
            float var4 = MathHelper.wrapDegrees(p_706632 - p_706631);
            if (var4 > 1000F) {
                var4 = 1000F;
            }
            if (var4 < -1000F) {
                var4 = -1000F;
            }
            return p_706631 + var4;
        }
        public static float getYawChangeToEntity(Entity entity) {
            double deltaX = entity.posX - mc.player.posX;
            double deltaZ = entity.posZ - mc.player.posZ;
            double yawToEntity;
            if ((deltaZ < 0.0D) && (deltaX < 0.0D)) {
                yawToEntity = 90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
            } else {
                if ((deltaZ < 0.0D) && (deltaX > 0.0D)) {
                    yawToEntity = -90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
                } else {
                    yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
                }
            }
            return MathHelper.wrapDegrees(-(mc.player.rotationYaw - (float) yawToEntity));
        }
        public static float[] limitAngleChange(final float[] currentRotation, final float[] targetRotation, final float turnSpeed) {
            final float yawDifference = getAngleDifference(targetRotation[0], currentRotation[1]);
            final float pitchDifference = getAngleDifference(targetRotation[0], currentRotation[1]);
            return new float[]{(currentRotation[0] + (yawDifference > turnSpeed ? turnSpeed : Math.max(yawDifference, -turnSpeed))),
                    (currentRotation[1] + (pitchDifference > turnSpeed ? turnSpeed : Math.max(pitchDifference, -turnSpeed)))};

        }
        public static float getPitchChangeToEntity(Entity entity) {
            double deltaX = entity.posX - mc.player.posX;
            double deltaZ = entity.posZ - mc.player.posZ;
            double deltaY = entity.posY - 1.6D + entity.getEyeHeight() - 0.4D - mc.player.posY;
            double distanceXZ = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);

            double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));

            return -MathHelper.wrapDegrees(mc.player.rotationPitch - (float) pitchToEntity);
        }
        private static float updateRotation(float currentRotation, float intendedRotation, float increment) {
            float f = MathHelper.wrapDegrees(intendedRotation - currentRotation);

            if (f > increment)
                f = increment;

            if (f < -increment)
                f = -increment;

            return currentRotation + f;
        }
    }

}
