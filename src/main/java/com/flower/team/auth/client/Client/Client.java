package com.flower.team.auth.client.Client;

import com.flower.team.auth.client.Packet.Client.LoginPacket;
import com.flower.team.auth.client.Packet.Message;
import com.flower.team.auth.client.Packet.Server.ResponsePacket;
import com.flower.team.auth.client.WebSocket.WebSocket;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class Client {
    public static ResponsePacket Login (String Version,String Username,String Password,String HWID) {
        LoginPacket Packet = new LoginPacket(Version,Username,Password,HWID);
        Gson gson = new Gson();
        String PacketJson = gson.toJson(new Message("LoginMessage",gson.toJson(Packet)));
        char[] len = {0,0,0,(char) PacketJson.getBytes().length};
        String data = null;
        try {
            WebSocket websocket = new WebSocket(new URI("ws://lycoris.rbq.wtf/auth"));
            websocket.connect();
            websocket.setSendData(PacketJson);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        ResponsePacket responsePacket = gson.fromJson(data,ResponsePacket.class);
        return responsePacket;
    }
}
