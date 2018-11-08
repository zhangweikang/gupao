package com.gupao.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 消息发送客户端
 *
 * @author ZhangWeiKang
 * @create 2018/11/2
 */
public class Client {

    private static final int port = 6666;
    private static final String ip = "127.0.0.1";

    private static Socket socket = null;

    public static void main(String[] args) throws IOException {
        socket = new Socket(ip,port);
        while (true) {
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            String consoleInString = consoleIn.readLine();
            BufferedReader in = null;
            PrintWriter out = null;

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(consoleInString);
            String s = in.readLine();
            System.out.println("收到服务端消息 = " + s);

            in.close();
            in = null;
            out.close();
            out = null;
        }
    }
}
