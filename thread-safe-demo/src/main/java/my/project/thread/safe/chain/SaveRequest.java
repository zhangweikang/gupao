package my.project.thread.safe.chain;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 保存 Request
 *
 * @author ZhangWeiKang
 * @create 2018/8/24
 */
public class SaveRequest extends Thread implements RequestProcessor {
    LinkedBlockingQueue<Request> linkedBlockingQueue=new LinkedBlockingQueue();

    @Override
    public void run() {
        while(true){
            try {
                Request request=linkedBlockingQueue.take();
                System.out.println("save data:"+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processorRequest(Request request) {
        linkedBlockingQueue.add(request);
    }

}
