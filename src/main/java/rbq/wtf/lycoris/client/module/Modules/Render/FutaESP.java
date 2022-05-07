package rbq.wtf.lycoris.client.module.Modules.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRenderWorldLast;
import rbq.wtf.lycoris.client.gui.ClickGUI.RenderUtil;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.awt.*;

import static org.lwjgl.util.glu.GLU.GLU_FILL;
import static org.lwjgl.util.glu.GLU.GLU_SILHOUETTE;

public class FutaESP extends Module {
    public static Minecraft mc = Minecraft.getMinecraft();
    public FutaESP() {
        super("FutaESP",ModuleCategory.Render,0);
    }
    float cache = 0,cache2 = 0;
    @EventTarget
    public void on3d(EventRenderWorldLast e) {


        for(Entity en : mc.world.getLoadedEntityList()){
            if(!(en instanceof EntityPlayer)){
                return;
            }
            EntityPlayer ep = ((EntityPlayer) en);

            double x =-Math.sin(Math.toRadians(ep.renderYawOffset)) ;//* Math.cos(Math.toRadians(mc.player.rotationPitch));
            double z=Math.cos(Math.toRadians(ep.renderYawOffset)) ;//* Math.cos(Math.toRadians(mc.player.rotationPitch));
            double rx =-Math.sin(Math.toRadians(ep.rotationYawHead)) * Math.cos(Math.toRadians(ep.rotationPitch));
            double ry =Math.sin(Math.toRadians(ep.rotationPitch));//* Math.cos(Math.toRadians(mc.player.rotationPitch));
            double rz=Math.cos(Math.toRadians(ep.rotationYawHead)) * Math.cos(Math.toRadians(ep.rotationPitch));
            BYT(e.getEvent());

            double off = 0.7;
            GL11.glPushMatrix();
            drawHAT(ep , new Color(255,255,255).getRGB(),e.getEvent());
            double h = ep.getHealth();
            GL11.glRotated(180,x,1,z);
            for (int i = 0;i<16;i++){
                nesp(ep, e.getEvent().getPartialTicks(), 0.1,x*off,i*0.05,z*off);
            }
            drawESPR(ep , new Color(255,255,255).getRGB(),e.getEvent(),x*off,-1.4,z*off);
            double offf =1.2;
            int im = 16;
            GL11.glPushMatrix();
            GL11.glRotated(8,x,2,z);
            for (int i = 0;i<im;i++){
                nesp(ep, e.getEvent().getPartialTicks(), (im-i)*0.008,x*offf,0.1+i*0.008,z*offf);

            }GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotated(-8,x,2,z);
            for (int i = 0;i<im;i++){
                nesp(ep, e.getEvent().getPartialTicks(), (im-i)*0.008,x*offf,0.1+i*0.008,z*offf);

            }

            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }


        if(true){
            return;
        }



        double x =-Math.sin(Math.toRadians(mc.player.renderYawOffset)) ;//* Math.cos(Math.toRadians(mc.player.rotationPitch));
        double z=Math.cos(Math.toRadians(mc.player.renderYawOffset)) ;//* Math.cos(Math.toRadians(mc.player.rotationPitch));

        double rx =-Math.sin(Math.toRadians(mc.player.rotationYawHead)) * Math.cos(Math.toRadians(mc.player.rotationPitch));

        double ry =Math.sin(Math.toRadians(mc.player.rotationPitch));//* Math.cos(Math.toRadians(mc.player.rotationPitch));

        double rz=Math.cos(Math.toRadians(mc.player.rotationYawHead)) * Math.cos(Math.toRadians(mc.player.rotationPitch));


        //  double x =-Math.sin(Math.toRadians(mc.player.rotationYaw)) ;//* Math.cos(Math.toRadians(mc.player.rotationPitch));
        //double z=Math.cos(Math.toRadians(mc.player.rotationYaw)) ;//* Math.cos(Math.toRadians(mc.player.rotationPitch));


        //cache = mc.player.rotationYaw;
        //cache2=mc.player.rotationYawHead;


        BYT(e.getEvent());

        double off = 0.7;
        //   GL11.glPushMatrix();
        //  GL11.glRotated(180,x*0.8,1.5,z*0.8);
        //    BYT(e);
        //GL11.glPopMatrix();

        GL11.glPushMatrix();
        drawHAT(mc.player , new Color(255,255,255).getRGB(),e.getEvent());
        ///   GL11.glPushMatrix();
        //  GL11.glRotated(180,mc.player.posX,mc.player.posY,mc.player.posZ);//*(float)mc.player.posZ
        ///    FontLoaders.default16.drawString("123123123",(float)rz,(float)mc.player.posY,-1);
        //    GL11.glPopMatrix();
        double h = mc.player.getHealth();
        GL11.glRotated(180,x,1,z);
        // nesp(mc.player, e.getPartialTicks(), 0.6,x,1,z);
        // nesp(mc.player, e.getPartialTicks(), 0.6,x*off,0.1,z*off);
        //BYT(e);
        for (int i = 0;i<16;i++){
            nesp(mc.player, e.getEvent().getPartialTicks(), 0.1,x*off,i*0.05,z*off);
        }


        // GL11.glRotated(180,x* 0.6,1,z* Random.nextFloat(0.5f,0.8f));
        // drawESP(mc.player ,-1,e);



        // GL11.glRotated(180,x*0.5,1,z* 0.4);
        // drawESP(mc.player ,-1,e);
        // GL11.glRotated(180,x*0.4,1,z* 0.5);


        drawESPR(mc.player , new Color(255,255,255).getRGB(),e.getEvent(),x*off,-1.4,z*off);

        // drawESPR(mc.player , new Color(255,255,255).getRGB(),e,x*off*-1,-2.4,z*off*-1);

        //  drawESPR(mc.player , new Color(255,255,255).getRGB(),e,x*off*0.2,-1.8,z*off*0.2);
        // GL11.glRotated(90,x,1,z);

        double offf =1.2;
        int im = 16;

        GL11.glPushMatrix();
        GL11.glRotated(8,x,2,z);
        //  drawESPRR(mc.player , new Color(255,255,255).getRGB(),e,x*1.2,-2.1,z*1.2);
        // GL11.glTranslated(-x, -2.1, -z);

        for (int i = 0;i<im;i++){
            nesp(mc.player, e.getEvent().getPartialTicks(), (im-i)*0.008,x*offf,0.1+i*0.008,z*offf);

        }GL11.glPopMatrix();
        GL11.glPushMatrix();
        //GL11.glTranslated(-x, -2.1, -z);
        GL11.glRotated(-8,x,2,z);
        // double offf =1.2;
        //int im = 16;
        for (int i = 0;i<im;i++){
            nesp(mc.player, e.getEvent().getPartialTicks(), (im-i)*0.008,x*offf,0.1+i*0.008,z*offf);

        }

        //drawESPRR(mc.player , new Color(255,255,255).getRGB(),e,x*1.2,-2.1,z*1.2);
        //drawESPR(mc.player , new Color(255,255,255).getRGB(),e,x*off,-2.1,z*off);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        //  nesp(mc.player, e.getPartialTicks(), 0.01,0.6+1.2+0.5 );
    }

    public void niuzi1(RenderWorldLastEvent e, double x, double y, double z, double off){



        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.2,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.3,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.4,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.5,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.6,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.7,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.8,z*off);
        nesp(mc.player, e.getPartialTicks(), 0.1,x*off,0.9,z*off);


    }
    public void drawESP(EntityLivingBase entity, int color, RenderWorldLastEvent e) {
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) e.getPartialTicks()
                - Wrapper.getRenderPosX();
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) e.getPartialTicks()
                - Wrapper.getRenderPosY();
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) e.getPartialTicks()
                - Wrapper.getRenderPosZ();
        float radius = 0.2f;
        int side = 6;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.width), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        c.setDrawStyle(100012);
        c.draw(0, radius, 0.3f, side, 1);
        glColor(color);
        c.setDrawStyle(100012);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0, 0.3f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }
    public void drawHAT(EntityLivingBase entity, int color, RenderWorldLastEvent e) {
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) e.getPartialTicks()
                - Wrapper.getRenderPosX();
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) e.getPartialTicks()
                - Wrapper.getRenderPosY()-0.5;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) e.getPartialTicks()
                - Wrapper.getRenderPosZ();

        float radius = 1f;
        int side = 16;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.width), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);

        Cylinder c = new Cylinder();
        Sphere s = new Sphere();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100012);
        //c.draw(0, radius, 0.3f, side, 1);
        //glColor(color);
        //c.setOrientation(GLU_FLAT);
        GL11.glPushMatrix();

        //glColor(color);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 20).getRGB());

        c.setDrawStyle(GLU_FILL );
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0.5f, 0.4f, side, 1);



        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 100).getRGB());


        c.setDrawStyle(GLU_SILHOUETTE );
        //   GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0.5f, 0.4f, side, 1);
        // c.draw(radius+0.01f, 0.51f, 0.41f, side, 1);

        GL11.glTranslated(0, 0, 0.4);
        c.draw(0.5f, 0, 0.1f, side, 1);
        // c.draw(0.51f, 0, 0.11f, side, 1);
        c.draw(0.5f, 0, -0.4f, side, 1);

        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 20).getRGB());
        c.setDrawStyle(GLU_FILL );
        c.draw(0.5f, 0, 0.1f, side, 1);

        GL11.glPopMatrix();

        //glColor(color);

        c.setDrawStyle(100010 );
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0.5f, 0.4f, side, 1);
        GL11.glTranslated(0, 0, 0.4);
        c.draw(0.5f, 0, 0.1f, side, 1);
        //  c.draw(0.5f, 0, -0.4f, side, 1);



        //GL11.glPushMatrix();
        //c.setDrawStyle(GLU_SILHOUETTE );
        //GL11.glTranslated(0, 0, 0.3);
        // c.draw(radius+0.05f, 0.55f, 0.45f, side, 1);
        //GL11.glPopMatrix();
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 20).getRGB());
        s.setDrawStyle(GLU_SILHOUETTE );
        GL11.glTranslated(0, 0, -0.5);
        s.draw(0.7f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawESP(EntityLivingBase entity, int color, RenderWorldLastEvent e,double x,double y,double z) {

        float radius = 0.2f;
        int side = 6;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.width), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        c.setDrawStyle(100012);
        c.draw(0, radius, 0.3f, side, 1);
        glColor(color);
        c.setDrawStyle(100012);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0, 0.3f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }
    public void drawESPR(EntityLivingBase entity, int color, RenderWorldLastEvent e,double x,double y,double z) {

        float radius = 0.2f;
        int side = 32;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.width), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100011);
        //  c.setDrawStyle(100013);
        //c.draw(0, radius, 0.1f, side, 1);
        glColor(color);
        // c.setDrawStyle(100011);
        c.setDrawStyle(GLU_SILHOUETTE );
        GL11.glTranslated(0, 0, 0.2);
        c.draw(radius, 0.05f, 0.2f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawHAT(EntityLivingBase entity, int color, RenderWorldLastEvent e,double x,double y,double z) {

        float radius = 0.2f;
        int side = 32;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.width), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100011);
        //  c.setDrawStyle(100013);
        //c.draw(0, radius, 0.1f, side, 1);
        glColor(color);
        // c.setDrawStyle(100011);



        c.setDrawStyle(GLU_SILHOUETTE );
        GL11.glTranslated(0, 0, 0.2);
        c.draw(radius, 0, 0.4f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawESPRR(EntityLivingBase entity, int color, RenderWorldLastEvent e,double x,double y,double z) {

        float radius = 0.2f;
        int side = 6;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.width), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100011);
        //  c.setDrawStyle(100013);
        //c.draw(0, radius, 0.1f, side, 1);
        glColor(color);
        // c.setDrawStyle(100011);
        c.setDrawStyle(GLU_SILHOUETTE );
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0, 0.2f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public static void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 255) / 255.0f;
        float red = (float) (hex >> 16 & 255) / 255.0f;
        float green = (float) (hex >> 8 & 255) / 255.0f;
        float blue = (float) (hex & 255) / 255.0f;
        GL11.glColor4f((float) red, (float) green, (float) blue, (float) (alpha == 0.0f ? 1.0f : alpha));
    }


    public void  BYT(RenderWorldLastEvent e){
        float offset=0;




        nesp(mc.player, e.getPartialTicks(), 0.6,0.2);
        nesp(mc.player, e.getPartialTicks(), 0.55,0.3);
        nesp(mc.player, e.getPartialTicks(), 0.5,0.4);
        nesp(mc.player, e.getPartialTicks(), 0.45,0.5);
        for(int i = 0;i<12;i++) {
            nesp(mc.player, e.getPartialTicks(), 0.4,0.6+i*0.1);
        }
        nesp(mc.player, e.getPartialTicks(), 0.35,0.6+1.2 );
        nesp(mc.player, e.getPartialTicks(), 0.3,0.6+1.2+0.1 );
        nesp(mc.player, e.getPartialTicks(), 0.25,0.6+1.2+0.15 );
        nesp(mc.player, e.getPartialTicks(), 0.2,0.6+1.2+0.2 );
        nesp(mc.player, e.getPartialTicks(), 0.15,0.6+1.2+0.25 );
        nesp(mc.player, e.getPartialTicks(), 0.1,0.6+1.2+0.3 );
        nesp(mc.player, e.getPartialTicks(), 0.1,0.6+1.2+0.35 );
        nesp(mc.player, e.getPartialTicks(), 0.1,0.6+1.2+0.4 );
        nesp(mc.player, e.getPartialTicks(), 0.05,0.6+1.2+0.45 );
    }


    public void nesp(Entity entity, float partialTicks, double rad,double x,double y,double z) {
        float points = 90F;
        GlStateManager.enableDepth();
        for (double il = 0; il < 4.9E-324; il += 4.9E-324) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glEnable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
            GL11.glHint(3153, 4354);
            GL11.glDisable(2929);
            GL11.glLineWidth(1f);
            GL11.glBegin(3);
            //  final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - mc.getRenderManager().viewerPosX;
            //final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - mc.getRenderManager().viewerPosY+xx;
            //final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - mc.getRenderManager().viewerPosZ;
            final double pix2 = 6.283185307179586;
            float speed = 5000f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;
            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i + (float) (il * 8)) / points;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                int color = Color.WHITE.getRGB();
                GL11.glColor3f(r, g, b);
                GL11.glVertex3d(x + rad * Math.cos(i * pix2 / points), y + il, z + rad * Math.sin(i * pix2 / points));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
            GlStateManager.color(255, 255, 255);
        }


    }

    public void nesp(Entity entity, float partialTicks, double rad,double xx) {
        float points = 90F;
        GlStateManager.enableDepth();
        for (double il = 0; il < 4.9E-324; il += 4.9E-324) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glEnable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
            GL11.glHint(3153, 4354);
            GL11.glDisable(2929);
            GL11.glLineWidth(1f);
            GL11.glBegin(3);
            final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - mc.getRenderManager().viewerPosX;
            final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - mc.getRenderManager().viewerPosY+xx;
            final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - mc.getRenderManager().viewerPosZ;
            final double pix2 = 6.283185307179586;
            float speed = 5000f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;
            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i + (float) (il * 8)) / points;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                int color = Color.WHITE.getRGB();
                GL11.glColor3f(r, g, b);
                GL11.glVertex3d(x + rad * Math.cos(i * pix2 / points), y + il, z + rad * Math.sin(i * pix2 / points));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
            GlStateManager.color(255, 255, 255);
        }


    }
    public void nesp(Entity entity, float partialTicks, double rad) {
        float points = 90F;
        GlStateManager.enableDepth();
        for (double il = 0; il < 4.9E-324; il += 4.9E-324) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glEnable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
            GL11.glHint(3153, 4354);
            GL11.glDisable(2929);
            GL11.glLineWidth(1f);
            GL11.glBegin(3);
            final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - mc.getRenderManager().viewerPosX;
            final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - mc.getRenderManager().viewerPosY;
            final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - mc.getRenderManager().viewerPosZ;
            final double pix2 = 6.283185307179586;
            float speed = 5000f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;
            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i + (float) (il * 8)) / points;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                int color = Color.WHITE.getRGB();
                GL11.glColor3f(r, g, b);
                GL11.glVertex3d(x + rad * Math.cos(i * pix2 / points), y + il, z + rad * Math.sin(i * pix2 / points));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
            GlStateManager.color(255, 255, 255);
        }


    }
}
