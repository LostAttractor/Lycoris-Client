package rbq.wtf.lycoris.client.module.Modules.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRenderWorldLast;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.module.Modules.Combat.AntiBot;
import rbq.wtf.lycoris.client.utils.*;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.awt.*;
import java.lang.reflect.Field;

public class NameTags extends Module {
    Minecraft mc = Minecraft.getMinecraft();
    public NumberValue scale = new NumberValue( "Scale", 1.0f, 0.1f, 1.0f, 0.1f,this);
    public BooleanValue invisible = new BooleanValue( "Bot & Invis", false,this);
    public BooleanValue health = new BooleanValue( "Health", true,this);
    public BooleanValue distance = new BooleanValue( "Distance", true,this);
    public BooleanValue renderArmor = new BooleanValue( "Armor", true,this);
    public BooleanValue background = new BooleanValue( "Background", true,this);
    public BooleanValue invisibles = new BooleanValue("Invisibles", false,this);
    public BooleanValue players = new BooleanValue("Players", false,this);
    public BooleanValue mobs = new BooleanValue("Mobs", false,this);

    public NameTags() {
        super("NameTags",ModuleCategory.Render,0);
        this.addNumberValue(scale);
        this.addBooleanValue(invisible);
        this.addBooleanValue(health);
        this.addBooleanValue(distance);
        this.addBooleanValue(renderArmor);
        this.addBooleanValue(background);
        this.addBooleanValue(invisibles);
        this.addBooleanValue(players);
        this.addBooleanValue(mobs);
    }
    @EventTarget
    public void onRender3D(EventRenderWorldLast event) {
//        System.out.println("WorldRender");
        if (this.mc.world == null)
            return;

        for (EntityPlayer entity : WorldUtil.getLivingPlayers()) {
//            System.out.println("Draw" + entity.getDisplayName());
//            if (true) {
            if (this.isValid(entity)) {

                final double yOffset = entity.isSneaking() ? -0.25 : 0.0;

                 try {


                     Field field = ReflectionHelper.findField(Minecraft.class, new String[]{"renderManager", "field_175616_W"});
                     field.setAccessible(true);

                     Field F_renderPosX = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosX", "field_78725_b"});
                     F_renderPosX.setAccessible(true);
                     double renderPosX = F_renderPosX.getDouble(field.get(Minecraft.getMinecraft()));

                     Field F_renderPosY = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosY", "field_78726_c"});
                     F_renderPosY.setAccessible(true);
                     double renderPosY = F_renderPosY.getDouble(field.get(Minecraft.getMinecraft()));

                     Field F_renderPosZ = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosZ", "field_78723_d"});
                     F_renderPosZ.setAccessible(true);
                     double renderPosZ = F_renderPosZ.getDouble(field.get(Minecraft.getMinecraft()));

                     double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) *
                             event.getEvent().getPartialTicks()  -
                             renderPosX;

                     double posY = (entity.lastTickPosY + yOffset) +
                             ((entity.posY + yOffset) - (entity.lastTickPosY + yOffset)) *
                                     event.getEvent().getPartialTicks() - renderPosY;

                     double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) *
                             event.getEvent().getPartialTicks() -
                             renderPosZ;

                     this.renderNameTag(entity, posX, posY, posZ, event.getEvent().getPartialTicks());
                 } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    public boolean isValid(EntityLivingBase entity) {

        if(mobs.getValue()) {
            if (ValidUtils.isMob(entity)) {
                return true;
            }
        }
        if(players.getValue()) {
            if (ValidUtils.isPlayer(entity)) {
                return true;
            }
        }

        if(invisibles.getValue()) {
            if (ValidUtils.isInvisible(entity)) {
                return true;
            }
        }


        return false;
    }

    private int getDisplayColour(EntityPlayer player) {
        int colour = new Color(0xFFFFFF).getRGB();
        if (player.isInvisible()) {
            colour = -1113785;
        }

        return colour;

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

        float width = this.mc.fontRenderer.getStringWidth(this.getDisplayName(player)) / 2;

        double scale = (double) (0.004F * this.scale.getValue()) * distance;

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
        if(this.background.getValue()) {
            WorldRenderUtils.drawBorderedRectReliant(-width - 2, -(this.mc.fontRenderer.FONT_HEIGHT + 3), (float) width + 2.0F, 2.0F, (float) scale, new Color(25, 25, 25, 160).getRGB(), new Color(0, 0, 0, 180).getRGB());
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        //Fix Color Glitch
        GL11.glDepthMask(false);
        this.mc.fontRenderer.drawStringWithShadow(this.getDisplayName(player), -width, -(mc.fontRenderer.FONT_HEIGHT), this.getDisplayColour(player));
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
            stack = player.inventory.armorInventory.get(index);
            if (stack != null) {
                xOffset -= 8;
            }
        }

        if (player.getHeldItemMainhand() != null) {
            xOffset -= 8;
            ItemStack var27 = player.getHeldItemMainhand().copy();
            if (var27.hasEffect() && (var27.getItem() instanceof ItemTool || var27.getItem() instanceof ItemArmor)) {
//                ObfuscationReflectionHelper.setPrivateValue(var27.getClass(),var27,1,"field_77994_a");
//                var27.
            }

            this.renderItemStack(var27, xOffset, -26);
            xOffset += 16;
        }

        for (index = 3; index >= 0; --index) {
            stack = player.inventory.armorInventory.get(index);
            if (stack != null) {
                ItemStack armourStack = stack.copy();
                if (armourStack.hasEffect() && (armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor)) {
//                    armourStack.stackSize = 1;
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
        this.mc.getRenderItem().renderItemOverlays(this.mc.fontRenderer, stack, x, y);

        GlStateManager.enableCull();

        this.mc.getRenderItem().zLevel = 0;

        GlStateManager.disableBlend();

        GlStateManager.scale(0.5F, 0.5F, 0.5F);

        GlStateManager.disableDepth();
        GlStateManager.disableLighting();

//        this.renderEnchantmentText(stack, x, y);

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();

        GlStateManager.scale(2.0F, 2.0F, 2.0F);

        GlStateManager.enableAlpha();

        GlStateManager.popMatrix();
    }

    private static final String pro = "pro";
    private static final String sha = "sha";
//
//    private void renderEnchantmentText(ItemStack stack, int x, int y) {
//        try {
//            int enchantmentY = y - 24;
//            int color = new Color(0xFFFFFF).getRGB();
//            if (stack.getItem() instanceof ItemArmor) {
//                int protection = EnchantmentHelper.getEnchantmentLevel(Enchantment..effectId, stack);
//                int projectileProtection = EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, stack);
//                int blastProtection = EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, stack);
//                int fireProtectionLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, stack);
//                int thornsLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack);
//                int featherFallingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, stack);
//                int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
//
//                if (protection > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(pro + protection, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (unbreakingLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.unbreaking.getName().substring(0, 3) + unbreakingLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (projectileProtection > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.projectileProtection.getName().substring(0, 3) + projectileProtection, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (blastProtection > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.blastProtection.getName().substring(0, 3) + blastProtection, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (fireProtectionLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.fireAspect.getName().substring(0, 3) + fireProtectionLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (thornsLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.thorns.getName().substring(0, 3) + thornsLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (featherFallingLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.featherFalling.getName().substring(0, 3) + featherFallingLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                /*
//                boolean dura = false;
//                if(dura && stack.getMaxDamage() - stack.getItemDamage() < stack.getMaxDamage()) {
//                    this.mc.fontRenderer.drawStringWithShadow(stack.getMaxDamage() - stack.getItemDamage() + "", x * 2, enchantmentY + 2, -26215);
//                    enchantmentY += 8;
//                }*/
//            }
//
//            if (stack.getItem() instanceof ItemBow) {
//                int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
//                int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
//                int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack);
//
//                if (powerLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.power.getName().substring(0, 3) + powerLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (punchLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.punch.getName().substring(0, 3) + punchLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (flameLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.flame.getName().substring(0, 3) + flameLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//            }
//
//            if (stack.getItem() instanceof ItemPickaxe) {
//                int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);
//                int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack);
//
//                if (efficiencyLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.efficiency.getName().substring(0, 3) + efficiencyLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (fortuneLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.fortune.getName().substring(0, 3) + fortuneLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//            }
//
//            if (stack.getItem() instanceof ItemAxe) {
//                int sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack);
//                int fireAspect = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack);
//                int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);
//                if (sharpnessLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.sharpness.getName().substring(0, 3) + sharpnessLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (fireAspect > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.fireAspect.getName().substring(0, 3) + fireAspect, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (efficiencyLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.efficiency.getName().substring(0, 3) + efficiencyLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//            }
//
//            if (stack.getItem() instanceof ItemSword) {
//                int sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack);
//                int knockbackLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack);
//                int fireAspectLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack);
//                if (sharpnessLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(sha + sharpnessLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (knockbackLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.knockback.getName().substring(0, 3) + knockbackLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//
//                if (fireAspectLevel > 0) {
//                    this.mc.fontRenderer.drawStringWithShadow(Enchantment.fireAspect.getName().substring(0, 3) + fireAspectLevel, x * 2, enchantmentY, color);
//                    enchantmentY += 8;
//                }
//            }
//
//           /* if(stack.getItem() == Items.golden_apple && stack.hasEffect()) {
//                this.mc.fontRenderer.drawStringWithShadowWithShadow("god", (float)(x * 2), (float)enchantmentY, -3977919);
//            }*/
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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



        drawTag = (this.distance.getValue() ?  EnumChatFormatting.GRAY + "[" + (int)entity.getDistance(this.mc.player) + "m] " : "") + EnumChatFormatting.GRAY + drawTag + " " + (this.health.getValue() ? "" + color + health : "");

//        DrawTextEvent drawTextEvent = new DrawTextEvent(drawTag);
//        EventManager.call(drawTextEvent);
//        drawTag = drawTextEvent.text;

        return drawTag;
    }
}
