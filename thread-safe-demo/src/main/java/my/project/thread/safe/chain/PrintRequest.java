package my.project.thread.safe.chain;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 打印执行
 *
 * @author ZhangWeiKang
 * @create 2018/8/24
 */
public class PrintRequest extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> linkedBlockingQueue=new LinkedBlockingQueue();

    private final RequestProcessor nextProcessor;

    public PrintRequest(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while(true){
            try {
                Request request=linkedBlockingQueue.take();
                System.out.println("print data:"+request);
                nextProcessor.processorRequest(request);
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
