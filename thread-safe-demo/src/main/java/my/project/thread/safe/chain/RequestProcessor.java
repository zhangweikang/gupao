package my.project.thread.safe.chain;

/**
 * request 执行方法
 *
 * @author ZhangWeiKang
 * @create 2018/8/24
 */
public interface RequestProcessor {

    void processorRequest(Request request);
}
