package rbq.wtf.lycoris.client.module.Modules.Render;


import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRender3D;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.GlStateManager;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class NameTags extends Module {
    Minecraft mc = Minecraft.getMinecraft();
    public NameTags() {
        super("NameTags",ModuleCategory.Render,0);
    }
    @EventTarget
    public void onRender3D(EventRender3D eventRender3D) {
        if (this.mc.theWorld == null)
            return;

        for (EntityPlayer entity : WorldUtil.getLivingPlayers()) {
            if (this.isValid(entity)) {
                final double yOffset = entity.isSneaking() ? -0.25 : 0.0;

                final double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - this.mc.getRenderManager().getRenderPosX();
                final double posY = (entity.lastTickPosY + yOffset) + ((entity.posY + yOffset) - (entity.lastTickPosY + yOffset)) * mc.timer.renderPartialTicks - this.mc.getRenderManager().getRenderPosY();
                final double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - this.mc.getRenderManager().getRenderPosZ();

                mc.entityRenderer.setupCameraTransform(eventRender3D.getPartialTicks(), 0);

                this.renderNameTag(entity, posX, posY, posZ, event.getPartialTicks());
            }
        }
    }

    public boolean isValid(EntityLivingBase entity) {
        if (ModuleManager.noRenderMod.isEnabled() && NoRender.players.getValueState() && entity instanceof EntityPlayer) return false;

        if (entity.isInvisible() && !invisible.getValue())
            return false;

        if (!AntiBots.isInTablist(entity) && !invisible.getValue())
            return false;

        return true;
    }

    private int getDisplayColour(EntityPlayer player) {
        int colour = new Color(0xFFFFFF).getRGB();

        if (Flux.INSTANCE.getFriendManager().isFriend(player.getName())) {
            return -11157267;
        } else {
            if (player.isInvisible()) {
                colour = -1113785;
            }

            return colour;
        }
    }

    private double interpolate(double previous, double current, float delta) {
        return previous + (current - previous) * (double) delta;
    }

    private void renderNameTag(EntityPlayer player, double x, double y, double z, float delta) {
        double tempY = y + 0.7D;

        Entity camera = this.mc.getRenderViewEntity();
        double originalPositionX = camera.posX;
        double originalPositionY = camera.posY;
        double originalPositionZ = camera.posZ;
        camera.posX = this.interpolate(camera.prevPosX, camera.posX, delta);
        camera.posY = this.interpolate(camera.prevPosY, camera.posY, delta);
        camera.posZ = this.interpolate(camera.prevPosZ, camera.posZ, delta);


        double distance = camera.getDistance(x + this.mc.getRenderManager().viewerPosX, y + this.mc.getRenderManager().viewerPosY, z + this.mc.getRenderManager().viewerPosZ);

        float width = FontManager.tahomaArrayList.getStringWidth(this.getDisplayName(player)) / 2;

        double scale = (double) (0.004F * NameTags.scale.getValue()) * distance;

        if (scale < 0.01)
            scale = 0.01;

        GlStateManager.pushMatrix();

        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0F, -1500000.0F);

        GlStateManager.disableLighting();

        GlStateManager.translate((float) x, (float) tempY + 1.4F, (float) z);
        GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        float var10001 = this.mc.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F;
        GlStateManager.rotate(this.mc.getRenderManager().playerViewX, var10001, 0.0F, 0.0F);
        GlStateManager.scale(-scale, -scale, scale);
        if(NameTags.background.getValue()) {
            WorldRenderUtils.drawBorderedRectReliant(-width - 2, -(FontManager.tahomaArrayList.FONT_HEIGHT + 3), (float) width + 2.0F, 2.0F, (float) scale, new Color(HackerDetector.isHacker(player) ? 255 : 25, 25, 25, 160).getRGB(), new Color(0, 0, 0, 180).getRGB());
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        //Fix Color Glitch
        GL11.glDepthMask(false);
        FontManager.tahomaArrayList.drawStringWithSuperShadow(this.getDisplayName(player), -width, -(FontManager.tahomaArrayList.FONT_HEIGHT), this.getDisplayColour(player));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDepthMask(true);

        if (renderArmor.getValue()) {
            this.renderArmor(player);

            //No Paper item
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
        }

        GlStateManager.disableLighting();

        camera.posX = originalPositionX;
        camera.posY = originalPositionY;
        camera.posZ = originalPositionZ;

        GlStateManager.doPolygonOffset(1.0F, 1500000.0F);
        GlStateManager.disablePolygonOffset();

        GlStateManager.popMatrix();
    }

    private void renderArmor(EntityPlayer player) {
        int xOffset = 0;

        int index;
        ItemStack stack;
        for (index = 3; index >= 0; --index) {
            stack = player.inventory.armorInventory[index];
            if (stack != null) {
                xOffset -= 8;
            }
        }

        if (player.getCurrentEquippedItem() != null) {
            xOffset -= 8;
            ItemStack var27 = player.getCurrentEquippedItem().copy();
            if (var27.hasEffect() && (var27.getItem() instanceof ItemTool || var27.getItem() instanceof ItemArmor)) {
                var27.stackSize = 1;
            }

            this.renderItemStack(var27, xOffset, -26);
            xOffset += 16;
        }

        for (index = 3; index >= 0; --index) {
            stack = player.inventory.armorInventory[index];
            if (stack != null) {
                ItemStack armourStack = stack.copy();
                if (armourStack.hasEffect() && (armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor)) {
                    armourStack.stackSize = 1;
                }

                this.renderItemStack(armourStack, xOffset, -26);
                xOffset += 16;
            }
        }
    }

    private void renderItemStack(ItemStack stack, int x, int y) {
        GlStateManager.pushMatrix();

        GlStateManager.disableAlpha();
        this.mc.getRenderItem().zLevel = -150.0F;

        GlStateManager.disableCull();

        this.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
        this.mc.getRenderItem().renderItemOverlays(this.mc.fontRendererObj, stack, x, y);

        GlStateManager.enableCull();

        this.mc.getRenderItem().zLevel = 0;

        GlStateManager.disableBlend();

        GlStateManager.scale(0.5F, 0.5F, 0.5F);

        GlStateManager.disableDepth();
        GlStateManager.disableLighting();

        this.renderEnchantmentText(stack, x, y);

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();

        GlStateManager.scale(2.0F, 2.0F, 2.0F);

        GlStateManager.enableAlpha();

        GlStateManager.popMatrix();
    }

    private static final String pro = "pro";
    private static final String sha = "sha";


    private String getDisplayName(EntityLivingBase entity) {
        String drawTag = entity.getDisplayName().getFormattedText();


        EnumChatFormatting color;

        final double health = MathUtils.roundToPlace(entity.getHealth() / 2.00, 2);

        if (health >= 6.0) {
            color = EnumChatFormatting.GREEN;
        } else if (health >= 2.0) {
            color = EnumChatFormatting.YELLOW;
        } else {
            color = EnumChatFormatting.RED;
        }

        String clientTag = "";


        drawTag = (NameTags.distance.getValue() ?  EnumChatFormatting.GRAY + "[" + (int)entity.getDistanceToEntity(this.mc.thePlayer) + "m] " : "") + EnumChatFormatting.RESET + clientTag + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + drawTag + " " + (NameTags.health.getValue() ? "" + color + health : "");

//        DrawTextEvent drawTextEvent = new DrawTextEvent(drawTag);
//        EventManager.call(drawTextEvent);
//        drawTag = drawTextEvent.text;

        return drawTag;
    }


    public static List<EntityPlayer> getLivingPlayers() {
        return Arrays.asList(
                ((List<Entity>) mc.theWorld.loadedEntityList).stream()
                        .filter(entity -> entity instanceof EntityPlayer)
                        .filter(entity -> entity != mc.thePlayer)
                        .map(entity -> (EntityPlayer) entity)
                        .toArray(EntityPlayer[]::new)
        );
    }
}
