package com.gupao.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消息接受服务端
 *
 * @author ZhangWeiKang
 * @create 2018/11/2
 */
public class Server implements Runnable {

    private static final int port = 6666;

    private static ServerSocket serverSocket = null;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

    public static Map<String, Socket> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Server().run();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                clients.put(accept.getPort() + "", accept);
                System.out.println("clients = " + clients.keySet().toString());
                threadPoolExecutor.execute(new ServerAccept().setSendSocket(accept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
