package rbq.wtf.lycoris.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

import java.util.Arrays;
import java.util.List;

public class WorldUtil {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static List<EntityLivingBase> getLivingEntities() {
        return Arrays.asList(
                ((List<Entity>) mc.world.loadedEntityList).stream()
                        .filter(entity -> entity instanceof EntityLivingBase)
                        .filter(entity -> entity != mc.player)
                        .map(entity -> (EntityLivingBase) entity)
                        .toArray(EntityLivingBase[]::new)
        );
    }

    public static List<Entity> getEntities() {
        return Arrays.asList(
                ((List<Entity>) mc.world.loadedEntityList).stream()
                        .filter(entity -> entity != mc.player)
                        .toArray(Entity[]::new)
        );
    }

    public static List<EntityPlayer> getLivingPlayers() {
        return Arrays.asList(
                ((List<Entity>) mc.world.loadedEntityList).stream()
                        .filter(entity -> entity instanceof EntityPlayer)
                        .filter(entity -> entity != mc.player)
                        .map(entity -> (EntityPlayer) entity)
                        .toArray(EntityPlayer[]::new)
        );
    }

    public static List<TileEntity> getTileEntities() {
        return ((List<TileEntity>) mc.world.loadedTileEntityList);
    }

    public static List<TileEntityChest> getChestTileEntities() {
        return Arrays.asList(
                ((List<TileEntity>) mc.world.loadedTileEntityList).stream()
                        .filter(entity -> entity instanceof TileEntityChest)
                        .map(entity -> (TileEntityChest) entity)
                        .toArray(TileEntityChest[]::new)
        );
    }

    public static List<TileEntityEnderChest> getEnderChestTileEntities() {
        return Arrays.asList(
                ((List<TileEntity>) mc.world.loadedTileEntityList).stream()
                        .filter(entity -> entity instanceof TileEntityEnderChest)
                        .map(entity -> (TileEntityEnderChest) entity)
                        .toArray(TileEntityEnderChest[]::new)
        );
    }
}
