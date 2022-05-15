package rbq.wtf.lycoris.client;

import com.flower.team.auth.client.Client.Client;
import com.flower.team.auth.client.Packet.Common.BanInfo;
import com.flower.team.auth.client.Packet.Common.UserInfo;
import com.flower.team.auth.client.Packet.Server.ResponsePacket;
import com.flower.team.auth.client.Utils.Crypto_Utils;
import com.google.gson.Gson;
import net.minecraft.client.gui.GuiMainScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.input.Keyboard;
import rbq.wtf.lycoris.client.event.EventHandler;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.gui.NewClickGUI.NewClickGUI;
import rbq.wtf.lycoris.client.manager.CommandManager;
import rbq.wtf.lycoris.client.manager.ModuleManager;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.utils.EventRegister;
import rbq.wtf.lycoris.client.utils.HWIDUtil;
import sun.misc.Unsafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

@Mod(modid = "lycorisclient", name = "lycorisClient", version = "1", acceptableRemoteVersions = "*")

public class LycorisClient {

    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public ClickGUI clickGUI;
    public NewClickGUI newClickGUI;
    public static LycorisClient instance;
    public static EventHandler eventsHandler;

    public static String Username;
    public static String Password;
    public static String Nick;
    public static String UID;
    public static String Rank;
    public static boolean verifyed;
    public static boolean verify = false;
    public static ResponsePacket responsePacket = null;

    @Mod.EventHandler
    public void init(FMLInitializationEvent E) {
        new LycorisClient();
    }
    public LycorisClient() {
        try {
            Socket socket = new Socket("127.0.0.1",12123);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Username = is.readLine();
            pw.close();
            is.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
        } catch (IOException e) {

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
        }
        //JOptionPane.showConfirmDialog(null,"2","DXPSL",JOptionPane.PLAIN_MESSAGE);
        try {
            Socket socket = new Socket("127.0.0.1",12122);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Password = is.readLine();
            pw.close();
            is.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            System.out.println("Pls Login Launch");
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
        }
        //JOptionPane.showConfirmDialog(null,"3","DXPSL",JOptionPane.PLAIN_MESSAGE);
        String password = "114514";
        try {
            password = Crypto_Utils.HMACSHA256(Password, "114514");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //JOptionPane.showConfirmDialog(null,"4","DXPSL",JOptionPane.PLAIN_MESSAGE);
        Client.Login("1.01",Username,password.substring(0,17), HWIDUtil.getHWID().substring(0,5));
        int nmsl = 1;
        //JOptionPane.showConfirmDialog(null,"5","DXPSL",JOptionPane.PLAIN_MESSAGE);

        while(!verify) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            //System.out.println(verify);
        }
        //JOptionPane.showConfirmDialog(null,responsePacket.Code,"DXPSL",JOptionPane.PLAIN_MESSAGE);
        if (Objects.equals(responsePacket.Code, "404")) {
            Gson gson = new Gson();

            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");

            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");


            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
        }
        if (Objects.equals(responsePacket.Code, "403")) {
            Gson gson = new Gson();

            BanInfo banInfo = gson.fromJson(responsePacket.Data, BanInfo.class);
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");

            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");
            System.out.println("Count Login to the server");


            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
        }
        if (Objects.equals(responsePacket.Code, "405")) {
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");
            System.out.println("Change HWID cooling not finished ");


            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }

            try {
                Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
                F.setAccessible(true);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(14514, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191910);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(114514, 191810);
                ((Unsafe) F.get(null)).putAddress(11454, 1919810);
                ((Unsafe) F.get(null)).putAddress(11454, 199810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
                ((Unsafe) F.get(null)).putAddress(11414, 191810);
                ((Unsafe) F.get(null)).putAddress(114514, 1919810);
            } catch (NoSuchFieldException es) {
                //  System.exit(0);//e.printStackTrace();
            } catch (IllegalAccessException ess) {
                // System.exit(0);//e.printStackTrace();
            }
        }
        //System.out.println(responsePacket.Code);
        if (Objects.equals(responsePacket.Code, "406")) {
            Gson gson = new Gson();

            BanInfo banInfo = gson.fromJson(responsePacket.Data, BanInfo.class);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            System.out.println("user was been bannd reason:"+banInfo.Reason);
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                Unsafe unsafe = null;
                try {
                    unsafe = (Unsafe) field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Class<?> cacheClass = null;
                try {
                    cacheClass = Class.forName("java.lang.Integer$IntegerCache");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Field cache = cacheClass.getDeclaredField("cache");
                long offset = unsafe.staticFieldOffset(cache);

                unsafe.putObject(Integer.getInteger("do what you want"), offset, null);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                Unsafe unsafe = null;
                try {
                    unsafe = (Unsafe) field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Class<?> cacheClass = null;
                try {
                    cacheClass = Class.forName("java.lang.Integer$IntegerCache");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Field cache = cacheClass.getDeclaredField("cache");
                long offset = unsafe.staticFieldOffset(cache);

                unsafe.putObject(Integer.getInteger("do what you want"), offset, null);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                Unsafe unsafe = null;
                try {
                    unsafe = (Unsafe) field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Class<?> cacheClass = null;
                try {
                    cacheClass = Class.forName("java.lang.Integer$IntegerCache");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Field cache = cacheClass.getDeclaredField("cache");
                long offset = unsafe.staticFieldOffset(cache);

                unsafe.putObject(Integer.getInteger("do what you want"), offset, null);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (Objects.equals(responsePacket.Code, "200")) {
            Gson gson = new Gson();
            UserInfo userInfo = gson.fromJson(responsePacket.Data, UserInfo.class);
            Username = userInfo.UserName;
            Rank = userInfo.Rank;
//            UID = userInfo.ID;
//            Username = "GSCSDSUN";
//            Rank = "Beta";
//            UID = "123";
//

            verifyed = true;

            instance = this;
            moduleManager = new ModuleManager();
            commandManager = new CommandManager();
            clickGUI = new ClickGUI();
            newClickGUI = new NewClickGUI();
            eventsHandler =  new EventHandler();

            EventRegister.register(MinecraftForge.EVENT_BUS, eventsHandler);
            EventManager.register(this);

            return;
        }
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = null;
            try {
                unsafe = (Unsafe) field.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Class<?> cacheClass = null;
            try {
                cacheClass = Class.forName("java.lang.Integer$IntegerCache");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Field cache = cacheClass.getDeclaredField("cache");
            long offset = unsafe.staticFieldOffset(cache);

            unsafe.putObject(Integer.getInteger("do what you want"), offset, null);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = null;
            try {
                unsafe = (Unsafe) field.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Class<?> cacheClass = null;
            try {
                cacheClass = Class.forName("java.lang.Integer$IntegerCache");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Field cache = cacheClass.getDeclaredField("cache");
            long offset = unsafe.staticFieldOffset(cache);

            unsafe.putObject(Integer.getInteger("do what you want"), offset, null);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ClickGUI getClickGUI() {
        return clickGUI;
    }

    public NewClickGUI getNewClickGUI() {
        return newClickGUI;
    }

    public void setNewClickGUI(NewClickGUI newClickGUI) {
        this.newClickGUI = newClickGUI;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @EventTarget
    public void EventKeyPress(EventKey e) {
        for(Module module : this.moduleManager.getModules()){
            if(module.getKey() == Keyboard.getEventKey()){
                module.toggle();
            }
        }

    }
}
