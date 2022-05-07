package com.flower.team.auth.client.Packet.Client;

public class LoginPacket {
    public String Version;
    public String UserName;
    public String Password;
    public String HWID;
    public LoginPacket(String version,String userName, String password, String hWID) {
        this.Version = version;
        this.UserName = userName;
        this.Password = password;
        this.HWID = hWID;
    }
}
