package rbq.wtf.lycoris.client.transformer;

import rbq.wtf.lycoris.client.LycorisClient;

public class OBFMap {
    public static String getString(String string) {
        if (LycorisClient.isVanilla) {
            switch (string) {
                case "net.minecraft.client.gui.GuiIngame":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "biq";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bbv";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bdm";
                    }
                    return "avo";
                case "net.minecraft.network.NetworkManager":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "gw";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ej";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "eo";
                    }
                    return "ek";
                case "net.minecraft.entity.Entity":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ol";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "mn";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "lp";
                    }
                    return "la";
                case "net.minecraft.client.entity.EntityPlayerSP":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bud";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "blk";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bnn";
                    }
                    return "bew";
                case "net.minecraft.client.renderer.EntityRenderer":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "buq";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "blt";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bnz";
                    }
                    return "bfk";
                case "net.minecraft.client.settings.KeyBinding":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bhy";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bal";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bcu";
                    }
                    return "avb";
                case "net.minecraft.client.gui.FontRenderer":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bip";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bbu";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bdl";
                    }
                    return "avn";
                case "net.minecraft.client.gui.GuiScreen":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bki";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bcq";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ber";
                    }
                    return "awr";
                case "net.minecraft.client.Minecraft":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bib";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bao";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bcx";
                    }
                    return "ave";
                case "field_71462_r":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "m";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "n";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "m";
                    }
                    return "m";
                case "net.minecraft.client.gui.GuiChat":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bkn";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bct";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bew";
                    }
                    return "awv";
                case "net.minecraft.entity.player.EntityPlayer":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "oq";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "mw";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "lu";
                    }
                    return "lf";
                case "net.minecraft.item.ItemPotion":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ajd";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "adp";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aem";
                    }
                    return "aai";
                case "net.minecraft.enchantment.EnchantmentHelper":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "alm";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "afv";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "agx";
                    }
                    return "ack";
                case "net.minecraft.entity.EnumCreatureAttribute":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "vu";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "sz";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "sk";
                    }
                    return "pw";
                case "net.minecraft.item.ItemStack":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aip";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "add";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "adz";
                    }
                    return "zx";
                case "net.minecraft.client.renderer.entity.RenderManager":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bzf";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bnn";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bsh";
                    }
                    return "biu";
                case "net.minecraft.block.Block":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ev";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ags";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ahu";
                    }
                    return "ade";
                case "net.minecraft.client.gui.GuiPlayerTabOverlay":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bjq";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "beh";
                    }
                    return "awh";
                case "net.minecraft.client.network.NetworkPlayerInfo":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bsc";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "blo";
                    }
                    return "bdc";
                case "net.minecraft.util.EnumFacing":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "fa";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "cr";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ct";
                    }
                    return "cq";
                case "net.minecraft.util.Session":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bii";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bbs";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bde";
                    }
                    return "avm";
                case "net.minecraft.client.renderer.Tessellator":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bve";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bmh";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bon";
                    }
                    return "bfx";
                case "net.minecraft.client.renderer.vertex.DefaultVertexFormats":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "cdy";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bwm";
                    }
                    return "bms";
                case "net.minecraft.client.renderer.vertex.VertexFormat":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "cea";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bwo";
                    }
                    return "bmu";
                case "net.minecraft.world.World":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "oi";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ahb";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aid";
                    }
                    return "adm";
                case "net.minecraft.entity.EntityLivingBase":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "vp";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "sv";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "sf";
                    }
                    return "pr";
                case "net.minecraft.client.settings.GameSettings":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bid";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bbj";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bcz";
                    }
                    return "avh";
                case "net.minecraft.client.multiplayer.PlayerControllerMP":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bsa";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bje";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "blm";
                    }
                    return "bda";
                case "net.minecraft.entity.player.InventoryPlayer":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aec";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "yx";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "zr";
                    }
                    return "wm";
                case "net.minecraft.entity.ai.attributes.AttributeModifier":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "we";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "tj";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "st";
                    }
                    return "qd";
                case "net.minecraft.block.state.IBlockState":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "awt";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ars";
                    }
                    return "alz";
                case "net.minecraft.util.MovementInput":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bub";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bli";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bnl";
                    }
                    return "beu";
                case "net.minecraft.inventory.Container":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "afr";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "aaa";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "abd";
                    }
                    return "xi";
                case "net.minecraft.inventory.Slot":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "agc";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "aak";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "abp";
                    }
                    return "xt";
                case "net.minecraft.network.play.server.S45PacketTitle":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "hv";
                case "net.minecraft.network.play.server.SPacketTitle":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "kp";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ib";
                    }
                    return "";
                case "net.minecraft.entity.item.EntityItem":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "acb";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "st";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "xz";
                    }
                    return "uo";
                case "net.minecraft.util.ChatComponentText":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "fq";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "fa";
                case "net.minecraft.util.text.TextComponentString":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ho";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "fe";
                    }
                    return "";
                case "net.minecraft.util.IChatComponent":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "fj";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "eu";
                case "net.minecraft.util.text.ITextComponent":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "hh";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ey";
                    }
                    return "";
                case "net.minecraft.util.EnumHand":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ub";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "qr";
                    }
                    return "";
                case "net.minecraft.client.renderer.WorldRenderer":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "blo";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "bfd";
                case "net.minecraft.client.renderer.BufferBuilder":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "buk";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "";
                case "net.minecraft.util.BlockPos":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "cj";
                case "net.minecraft.util.math.BlockPos":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "et";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "cm";
                    }
                    return "";
                case "net.minecraft.util.AxisAlignedBB":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "azt";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "aug";
                case "net.minecraft.util.math.AxisAlignedBB":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bhb";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bby";
                    }
                    return "";
                case "net.minecraft.util.MovingObjectPosition":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "azu";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "auh";
                case "net.minecraft.util.MovingObjectPosition$MovingObjectType":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "azv";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "auh$a";
                case "net.minecraft.util.math.RayTraceResult":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bhc";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bbz";
                    }
                    return "";
                case "net.minecraft.util.math.RayTraceResult$Type":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bhc$a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bbz$a";
                    }
                    return "";
                case "net.minecraft.util.Vec3":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "azw";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "aui";
                case "net.minecraft.util.math.Vec3d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bhe";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bcb";
                    }
                    return "";
                case "field_71466_p":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "k";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "l";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "k";
                    }
                    return "k";
                case "func_175063_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "field_181705_e":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "e";
                    }
                    return "e";
                case "func_111164_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "field_72340_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "field_72338_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "field_72339_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "c";
                    }
                    return "c";
                case "field_72336_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "field_72337_e":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "e";
                    }
                    return "e";
                case "field_72334_f":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "f";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "f";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "f";
                    }
                    return "f";
                case "func_178781_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "a";
                case "func_72317_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "c";
                    }
                    return "c";
                case "func_75139_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_152377_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "field_70177_z":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "v";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "y";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "v";
                    }
                    return "y";
                case "field_70125_A":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "w";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "z";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "w";
                    }
                    return "z";
                case "field_70165_t":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "p";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "s";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "p";
                    }
                    return "s";
                case "field_70163_u":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "q";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "t";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "q";
                    }
                    return "t";
                case "field_70161_v":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "r";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "u";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "r";
                    }
                    return "u";
                case "field_70159_w":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "s";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "v";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "s";
                    }
                    return "v";
                case "field_70181_x":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "t";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "w";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "t";
                    }
                    return "w";
                case "field_70179_y":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "u";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "x";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "u";
                    }
                    return "x";
                case "field_70143_R":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "L";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "R";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "L";
                    }
                    return "O";
                case "field_70122_E":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "z";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "D";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "z";
                    }
                    return "C";
                case "func_70093_af":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aU";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "an";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aM";
                    }
                    return "av";
                case "func_110143_aJ":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "cd";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "aS";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bT";
                    }
                    return "bn";
                case "field_70142_S":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "M";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "S";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "M";
                    }
                    return "P";
                case "field_70137_T":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "N";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "T";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "N";
                    }
                    return "Q";
                case "field_70136_U":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "O";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "U";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "O";
                    }
                    return "R";
                case "field_70130_N":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "G";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "M";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "G";
                    }
                    return "J";
                case "field_70131_O":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "H";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "N";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "H";
                    }
                    return "K";
                case "func_82150_aj":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aX";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ap";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aP";
                    }
                    return "ax";
                case "func_70089_S":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aC";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "Z";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "au";
                    }
                    return "ai";
                case "func_145748_c_":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "i_";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c_";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "i_";
                    }
                    return "f_";
                case "func_70051_ag":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aV";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ao";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aN";
                    }
                    return "aw";
                case "func_70031_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "f";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "f";
                    }
                    return "d";
                case "func_70047_e":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "by";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "g";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bq";
                    }
                    return "aS";
                case "func_174813_aQ":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bw";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bo";
                    }
                    return "aR";
                case "field_70737_aN":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ay";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ax";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "az";
                    }
                    return "au";
                case "field_71071_by":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bv";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bm";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bt";
                    }
                    return "bi";
                case "field_71069_bz":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "bx";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "bn";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "bu";
                    }
                    return "bj";
                case "func_70005_c_":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "h_";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "b_";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "h_";
                    }
                    return "e_";
                case "field_71158_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "e";
                    }
                    return "b";
                case "func_71165_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "g";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "g";
                    }
                    return "e";
                case "func_145747_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "UNDEFINED":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "field_74351_w":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "T";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "U";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "S";
                    }
                    return "Y";
                case "field_74368_y":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "V";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "W";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "U";
                    }
                    return "aa";
                case "field_74370_x":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "U";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "V";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "T";
                    }
                    return "Z";
                case "field_74366_z":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "W";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "X";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "V";
                    }
                    return "ab";
                case "field_74312_F":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ae";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ad";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ad";
                    }
                    return "ai";
                case "field_74313_G":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ad";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ab";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ac";
                    }
                    return "ag";
                case "field_74311_E":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "Y";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "Z";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "X";
                    }
                    return "ad";
                case "field_74314_A":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "X";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "Y";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "W";
                    }
                    return "ac";
                case "field_151444_V":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "Z";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "af";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "Y";
                    }
                    return "ae";
                case "field_74333_Y":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aE";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "aG";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aA";
                    }
                    return "aJ";
                case "func_177230_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "u";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "t";
                    }
                    return "c";
                case "func_150260_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "c";
                    }
                    return "c";
                case "field_70461_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "c";
                case "func_70301_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_77831_g":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "g";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "f";
                case "func_111283_C":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "D";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "B";
                case "func_150997_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_77973_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "func_77960_j":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "j";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "k";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "i";
                    }
                    return "i";
                case "func_74507_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_74510_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_151470_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "e";
                    }
                    return "d";
                case "func_151463_i":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "j";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "i";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "j";
                    }
                    return "i";
                case "field_71439_g":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "h";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "h";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "h";
                    }
                    return "h";
                case "field_71441_e":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "f";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "f";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "f";
                    }
                    return "f";
                case "field_71474_y":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "t";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "u";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "u";
                    }
                    return "t";
                case "field_71442_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "c";
                    }
                    return "c";
                case "field_71476_x":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "s";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "t";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "t";
                    }
                    return "s";
                case "field_71467_ac":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "as";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ad";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aq";
                    }
                    return "ap";
                case "field_71415_G":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "x";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "x";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "x";
                    }
                    return "w";
                case "func_71410_x":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "z";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "B";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "z";
                    }
                    return "A";
                case "func_175598_ae":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ac";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ac";
                    }
                    return "af";
                case "field_71429_W":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "ai";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "U";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "ag";
                    }
                    return "ag";
                case "field_78902_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "field_78900_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "field_78901_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "g";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "g";
                    }
                    return "c";
                case "field_78899_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "h";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "h";
                    }
                    return "d";
                case "field_72308_g":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "g";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "net.minecraft.network.play.server.S05PacketSpawnPosition":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ig";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "ht";
                case "net.minecraft.network.play.server.SPacketSpawnPosition":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "kn";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "hz";
                    }
                    return "";
                case "net.minecraft.network.play.server.S08PacketPlayerPosLook":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "fu";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "fi";
                case "net.minecraft.network.play.server.SPacketPlayerPosLook":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "jq";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "he";
                    }
                    return "";
                case "field_78770_f":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "g";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "e";
                    }
                    return "e";
                case "field_178895_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "c";
                    }
                    return "c";
                case "func_78753_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "a";
                case "field_78725_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "o";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "o";
                    }
                    return "o";
                case "field_78726_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "p";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "p";
                    }
                    return "p";
                case "field_78723_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "q";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "q";
                    }
                    return "q";
                case "func_179805_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "func_75211_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "func_75216_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "e";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "e";
                    }
                    return "e";
                case "func_178181_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_178180_c":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "c";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "c";
                    }
                    return "c";
                case "func_78381_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "field_73010_i":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "i";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "h";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "i";
                    }
                    return "j";
                case "func_72910_y":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "L";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "D";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "J";
                    }
                    return "E";
                case "func_180495_p":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "o";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "o";
                    }
                    return "p";
                case "func_175623_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "func_72945_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "";
                    }
                    return "a";
                case "func_181668_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_181662_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "func_181675_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "func_70091_d":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "d";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "d";
                    }
                    return "d";
                case "func_70111_Y":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "aI";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "af";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "aA";
                    }
                    return "ao";
                case "func_70071_h_":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "B_";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "h";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "m";
                    }
                    return "t_";
                case "func_175161_p":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "N";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "A";
                    }
                    return "p";
                case "func_175068_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_78473_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_180455_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "func_175281_b":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "b";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "b";
                    }
                    return "b";
                case "func_71411_J":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "az";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "ak";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "av";
                    }
                    return "av";
                case "func_179290_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "net.minecraft.network.Packet":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "gy";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "et";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "eq";
                    }
                    return "em";
                case "channelRead0":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
                case "func_175180_a":
                    if(LycorisClient.game_version.equals("1.12.2")){
                        return "a";
                    }
                    if(LycorisClient.game_version.equals("1.7.10")){
                        return "";
                    }
                    if(LycorisClient.game_version.equals("1.10.2")){
                        return "a";
                    }
                    return "a";
            }
        } else {
            if(LycorisClient.game_version.startsWith("1.12")) {
                switch (string) {
                    case "net.minecraft.util.BlockPos":
                        return "net.minecraft.util.math.BlockPos";
                    case "field_78900_b":
                        return "field_70701_bs";
                }
            }
            return string;
        }
        try {
            throw new Exception("Not Found String:" + string);
        } catch (Exception e) {
        }
        return "";
    }
}
