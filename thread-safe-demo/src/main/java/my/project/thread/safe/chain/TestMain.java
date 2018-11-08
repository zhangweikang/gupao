package my.project.thread.safe.chain;

/**
 * Test
 *
 * @author ZhangWeiKang
 * @create 2018/8/24
 */
public class TestMain {
    PrintRequest printRequest;
    public TestMain() {
        SaveRequest saveRequest = new SaveRequest();
        saveRequest.start();

        printRequest = new PrintRequest(saveRequest);
        printRequest.start();
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("zhang wei kang");

        new TestMain().doTest(request);
    }

    public void doTest(Request request){
        printRequest.processorRequest(request);
    }
}
