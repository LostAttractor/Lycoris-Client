package com.flower.team.auth.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class AuthClient {
    public static void main(String[] args) {

        try {
            //创建一个Socket，跟服务器的8080端口链接
            Socket socket = new Socket("127.0.0.1",12123);
            //使用PrintWriter和BufferedReader进行读写数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
            pw.println("msg");
            pw.flush();
            //接收数据
            String line = is.readLine();
            System.out.println("received from server" + line);
            //关闭资源
            pw.close();
            is.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
