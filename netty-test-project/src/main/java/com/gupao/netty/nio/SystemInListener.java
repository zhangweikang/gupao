package com.gupao.netty.nioIMeaagae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * system.in 监听器
 *
 * @author ZhangWeiKang
 * @create 2018/11/8
 */
public class SystemInListener implements Runnable {

    private SelectableChannel selectableChannel;

    private Selector selector;

    private Object object;

    public SystemInListener(SelectableChannel selectableChannel, Selector selector, Object object) {
        this.selectableChannel = selectableChannel;
        this.selector = selector;
        this.object = object;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //当前阻塞,监听system.in,当控制台有数据输入时,结束阻塞
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                //获取控制台输入的值,并赋值给成员变量
                if (object instanceof Client) {
                    System.out.println("TestClient控制台触发");
                    Client.writerMessage = bufferedReader.readLine();
                } else if (object instanceof Server) {
                    System.out.println("TestServer控制台触发");
                    Server.writerMessage = bufferedReader.readLine();
                } else {
                    return;
                }
                //为当前通道注册,read和writer事件
                selectableChannel.register(selector, SelectionKey.OP_WRITE);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
