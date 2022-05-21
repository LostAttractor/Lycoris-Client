package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;
import paulscode.sound.Vector3D;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.event.events.EventRenderWorldLast;
import rbq.wtf.lycoris.client.gui.ClickGUI.RenderUtil;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.*;
import rbq.wtf.lycoris.client.utils.Render.GLUtil;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TpAura extends Module {

    static Minecraft mc = Minecraft.getMinecraft();

    private double dashDistance = 5;
    private ArrayList<Vec3> path = new ArrayList<>();
    private List<EntityLivingBase> targets = new CopyOnWriteArrayList<>();
    private TimerUtil cps = new TimerUtil();
    public static TimerUtil timer = new TimerUtil();
    private List<Vec3>[] test = new ArrayList[50];





    //public static BooleanValue autoBlock = new BooleanValue("AutoBlock", true);
    public static NumberValue RANGE = new NumberValue ("Range", 4.2, 3.0, 50.0, 1.0f);
    public static NumberValue  MAXTARGETS = new NumberValue ("MaxTargets", 1.0, 1.0, 25.0, 0.1f);
    public static NumberValue APS = new NumberValue("CPS", 10.0, 1.0, 20.0, 1.0);

    public static BooleanValue attackPlayers = new BooleanValue("Players", true);
    public static BooleanValue attackAnimals = new BooleanValue("Animals", false);
    public static BooleanValue attackMobs = new BooleanValue("Mobs", false);
    // public static BooleanValue throughblock = new BooleanValue("ThroughBlock", true);
    public static BooleanValue invisible = new BooleanValue("Invisible", false);
    public BooleanValue pathESP = new BooleanValue("PathESP", true);
    public BooleanValue noSwing = new BooleanValue("NoSwing", true);

    public ModeValue priority = new ModeValue("Priority", new String[]{"HighestHealth", "LowestHealth", "MostArmor", "LeastArmor", "Furthest", "Closest"},1,5);
    public ModeValue espMode = new ModeValue("PathESPmode", new String[]{"Flat", "Box"},0,1);


    public TpAura(){
        super("TPAura", ModuleCategory.Combat,0);

        this.addBooleanValue(attackPlayers);
        this.addBooleanValue(attackAnimals);
        this.addBooleanValue(attackMobs);
        this.addBooleanValue(invisible);

        this.addBooleanValue(pathESP);
        this.addBooleanValue(noSwing);
        this.addNumberValue(APS);
        this.addNumberValue(RANGE);

        this.addNumberValue(MAXTARGETS);

        this.addModeValue(priority);
    }

    @EventTarget
    public void onUpdate(EventClientTick event) {
        int delayValue = (20 / getAPS() * 50);
        int maxtTargets = (int) MAXTARGETS.getValue().floatValue();
        targets = getTargets();
        if (cps.reach(delayValue)) {
            if (targets.size() > 0) {
                test = new ArrayList[50];
                for (int i = 0; i < (targets.size() > maxtTargets ? maxtTargets : targets.size()); i++) {
                    EntityLivingBase T = targets.get(i);
                    Vec3 topFrom = new Vec3(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ);
                    Vec3 to = new Vec3(T.posX, T.posY, T.posZ);

                                            path = computePath(topFrom, to);
                    test[i] = path;
                    for (Vec3 pathElm : path) {
                        this.mc.getConnection().getNetworkManager().sendPacket(new CPacketPlayer.Position(pathElm.getX(), pathElm.getY(), pathElm.getZ(), true));
                    }
                    if (!noSwing.getValue()) {
                        this.mc.player.swingArm(EnumHand.MAIN_HAND);//.swingItem();
                    }
                    Wrapper.INSTANCE.sendPacket(new CPacketUseEntity(T)); // 攻击

                    this.mc.getConnection().getNetworkManager().sendPacket(new CPacketUseEntity(T, EnumHand.MAIN_HAND));
                    Collections.reverse(path);
                    for (Vec3 pathElm : path) {
                        this.mc.getConnection().getNetworkManager().sendPacket(new CPacketPlayer.Position(pathElm.getX(), pathElm.getY(), pathElm.getZ(), true));
                    }
                }
                cps.reset();
            }
        }
    }

    @EventTarget
    public void onRender3D(EventRenderWorldLast event) {
        if (!path.isEmpty() && pathESP.getValue()) {




            for (int i = 0; i < targets.size(); i++) {
                try {
                    if (test != null)
                        for (Vec3 pos : test[i]) {
                            if (pos != null) {
                                double width = mc.player.getEntityBoundingBox().maxX - mc.player.getEntityBoundingBox().minX;
                                double height = mc.player.getEntityBoundingBox().maxY - mc.player.getEntityBoundingBox().minY;
                                double x = pos.getX() - Wrapper.getRenderPosX();
                                double y = pos.getY() - Wrapper.getRenderPosY();
                                double z = pos.getZ() - Wrapper.getRenderPosZ();
                                WorldRenderUtils.drawOutlinedEntityESP(x, y, z, width, height, 255f, 255f, 255f, 1);

                            }

                        }
                } catch (Exception e) {

                }
            }
            if (cps.reach(1000)) {
                test = new ArrayList[50];
                path.clear();
            }
        }
    }

    public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        RenderUtil.drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        RenderUtil.drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }




    private ArrayList<Vec3> computePath(Vec3 topFrom, Vec3 to) {
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


    private boolean canPassThrow(BlockPos pos) {
        Block block = Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock();
        return block.getMaterial(Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ()))) == Material.AIR || block.getMaterial(Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ()))) == Material.PLANTS || block.getMaterial(Minecraft.getMinecraft().world.getBlockState(new net.minecraft.util.math.BlockPos(pos.getX(), pos.getY(), pos.getZ()))) == Material.VINE || block == Blocks.LADDER || block == Blocks.WATER ||
                block == Blocks.FLOWING_WATER || block == Blocks.WALL_SIGN || block == Blocks.STANDING_SIGN;
    }
    public static double getRandomDoubleInRange(double minDouble, double maxDouble) {
        return minDouble >= maxDouble ? minDouble : new Random().nextDouble() * (maxDouble - minDouble) + minDouble;
    }
    private int getAPS() {
        return (int) getRandomDoubleInRange(APS.getValue(), APS.getValue()+1f);
    }
    public void drawPath(Vec3 vec) {
        double x = vec.getX() - Wrapper.getRenderPosX();
        double y = vec.getY() - Wrapper.getRenderPosY();
        double z = vec.getZ() - Wrapper.getRenderPosZ();
        double width = 0.3;
        GLUtil.pre3D();
        GL11.glLoadIdentity();
        Wrapper.setupCameraTransform(this.mc, 2);

        int colors[] = {Colors.getColor(Color.black), Colors.getColor(Color.white)};
        for (int i = 0; i < 2; i++) {
            GLUtil.glColor(colors[i]);
            GL11.glLineWidth(3 - i * 2);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GL11.glVertex3d(x - width, y, z - width);
            GL11.glVertex3d(x - width, y, z - width);
            GL11.glVertex3d(x + width, y, z - width);
            GL11.glVertex3d(x - width, y, z - width);
            GL11.glVertex3d(x - width, y, z + width);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GL11.glVertex3d(x + width, y, z + width);
            GL11.glVertex3d(x - width, y, z + width);
            GL11.glVertex3d(x + width, y, z + width);
            GL11.glVertex3d(x + width, y, z - width);
            GL11.glEnd();
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        GLUtil.post3D();
    }

    private boolean validEntity(EntityLivingBase entity) {
        float range = RANGE.getValue();

        if ((this.mc.player.isEntityAlive())
                && !(entity instanceof EntityPlayerSP)) {
            if (this.mc.player.getDistance(entity) <= range) {
//                if (AntiBot.isBot((EntityPlayer) entity)) {
//                    return false;
//                }
                if (entity.isPlayerSleeping()) {
                    return false;
                }
                if (entity instanceof EntityPlayer) {
                    if (attackPlayers.getValue()) {
                        EntityPlayer player = (EntityPlayer) entity;
                        if (!player.isEntityAlive()
                                && player.getHealth() == 0.0) {
                            return false;
                        } else if (!ValidUtils.isTeam(player)) {
                            return false;
                        } else if (player.isInvisible()&& !invisible.getValue()) {
                            return false;

                            //} else if (Client.INSTANCE.getFriendManager().isFriend(player.getName())) {
                            //   return false;
                        } else
                            return true;
                    }
                } else {
                    if (!entity.isEntityAlive()) {

                        return false;
                    }
                }

                if (entity instanceof EntityMob && attackMobs.getValue()) {
                    return true;
                }
                if ((entity instanceof EntityAnimal || entity instanceof EntityVillager)
                        && attackAnimals.getValue()) {
                    if (entity.getName().equals("Villager")) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    private List<EntityLivingBase> getTargets() {
        List<EntityLivingBase> targets = new ArrayList<EntityLivingBase>();

        for (Object o : new CopyOnWriteArrayList(this.mc.world.getLoadedEntityList())) {
            if (o instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) o;
                if (validEntity(entity)) {
                    targets.add(entity);
                }
            }
        }
        switch (priority.getCurrentSelectionName()) {
            case "HighestHealth":
                targets.sort(Comparator.comparingDouble(EntityLivingBase::getHealth));
                break;
            case "LowestHealth":
                targets.sort(Comparator.comparingDouble(EntityLivingBase::getHealth).reversed());
                break;
            case "MostArmor":
                targets.sort(Comparator.comparingInt(EntityLivingBase::getTotalArmorValue).reversed());
                break;
            case "LeastArmor":
                targets.sort(Comparator.comparingInt(EntityLivingBase::getTotalArmorValue));
                break;
            case "Furthest":
                targets.sort(Comparator.comparingDouble((o) -> {
                    return ((EntityLivingBase) o).getDistanceSq(this.mc.player);
                }).reversed());
                break;
            case "Closest":
                targets.sort(Comparator.comparingDouble((o) -> {
                    return o.getDistanceSq(this.mc.player);
                }));
                break;
        }
        return targets;
    }
}
