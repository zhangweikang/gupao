package com.gupao.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 处理server accept
 *
 * @author ZhangWeiKang
 * @create 2018/11/2
 */
public class ServerAccept implements Runnable {

    private Socket sendSocket = null;

    private Socket otherSocket = null;

    public ServerAccept setSendSocket(Socket sendSocket) {
        this.sendSocket = sendSocket;
        return this;
    }

    public ServerAccept setOtherSocket(Socket otherSocket) {
        this.otherSocket = otherSocket;
        return this;
    }

    public void run() {
        if (sendSocket == null) return;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(sendSocket.getInputStream()));
            String sendMessage = in.readLine();
            System.out.println("收到客户端消息 = " + sendMessage);
            if (sendMessage.contains("-")) {
                String[] split = sendMessage.split("-");
                String otherPort = split[0];
                if (Server.clients.containsKey(otherPort)) {
                    out = new PrintWriter(Server.clients.get(otherPort).getOutputStream(), true);
                    out.println(split[1]);
                } else {
                    out = new PrintWriter(sendSocket.getOutputStream(), true);
                    out.println("未找到指定用户,请重新输入");
                }

            } else {
                out = new PrintWriter(sendSocket.getOutputStream(), true);
                out.println("未找到指定用户,请重新输入");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null) {
                out.close();
                out = null;
            }
        }
    }
}
