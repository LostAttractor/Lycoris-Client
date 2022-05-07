package com.flower.team.auth.client.WebSocket;

import com.flower.team.auth.client.Packet.Server.ResponsePacket;
import com.flower.team.auth.client.java_websocket.client.WebSocketClient;
import com.flower.team.auth.client.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import rbq.wtf.lycoris.client.LycorisClient;
import sun.misc.Unsafe;

import javax.swing.*;
import java.lang.reflect.Field;
import java.net.URI;

public class WebSocket extends WebSocketClient {
    public String sendData;
    public boolean verifyed;
    public WebSocket(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        //System.out.println("open");
        this.send(sendData);
    }

    @Override
    public void onMessage(String s) {
        //System.out.println(s);
        //JOptionPane.showConfirmDialog(null,"6msg","DXPSL",JOptionPane.PLAIN_MESSAGE);
        Gson gson = new Gson();
        ResponsePacket responsePacket = gson.fromJson(s,ResponsePacket.class);
        LycorisClient.responsePacket = responsePacket;
        verifyed = true;
        LycorisClient.verify = true;
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        //JOptionPane.showConfirmDialog(null,"6","DXPSL",JOptionPane.PLAIN_MESSAGE);

        if (!verifyed) {
            //JOptionPane.showConfirmDialog(null,"7","DXPSL",JOptionPane.PLAIN_MESSAGE);
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
        //System.out.println("close");
    }

    @Override
    public void onError(Exception e) {
//        JOptionPane.showConfirmDialog(null,e.getMessage(),"DXPSL",JOptionPane.PLAIN_MESSAGE);
//        e.printStackTrace();
//        try {
//            Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
//            F.setAccessible(true);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(14514, 191810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(11414, 191910);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(114514, 191810);
//            ((Unsafe) F.get(null)).putAddress(11454, 1919810);
//            ((Unsafe) F.get(null)).putAddress(11454, 199810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(11414, 191810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//        } catch (NoSuchFieldException es) {
//            //  System.exit(0);//e.printStackTrace();
//        } catch (IllegalAccessException ess) {
//            // System.exit(0);//e.printStackTrace();
//        }
//
//        try {
//            Field F = Unsafe.class.getDeclaredField("t"+"he"+"U"+"nsa"+"fe");
//            F.setAccessible(true);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(14514, 191810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(11414, 191910);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(114514, 191810);
//            ((Unsafe) F.get(null)).putAddress(11454, 1919810);
//            ((Unsafe) F.get(null)).putAddress(11454, 199810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//            ((Unsafe) F.get(null)).putAddress(11414, 191810);
//            ((Unsafe) F.get(null)).putAddress(114514, 1919810);
//        } catch (NoSuchFieldException es) {
//            //  System.exit(0);//e.printStackTrace();
//        } catch (IllegalAccessException ess) {
//            // System.exit(0);//e.printStackTrace();
//        }

    }

    public void setSendData (String s) {
        sendData = s;
    }
}
