package my.project.activemq.consumer;

import my.project.activemq.commons.Constions;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 监听器接受消息
 *
 * @author ZhangWeiKang
 * @create 2018/7/8
 */
public class ActiveMqQueueConsumerListenner {

    private Connection connection;
    private Session session;

    public ActiveMqQueueConsumerListenner() {
        init();
    }

    private void init() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(Constions.mq_userName,Constions.mq_password,Constions.mq_url);

            connection = factory.createConnection();

            connection.start();

            //true:事务性消息;(事务性消息,必须手动commit才会发送到消息中心)
            // false:非事务性消息;(非事务性消息,只要发送就会发送到消息中心)

            //Session.AUTO_ACKNOWLEDGE:当客户端receive返回后,或者MessageListenner.onMessage返回后,会话会确认客户收到消息
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String getStringMessage(String queueName){
        try {
            Queue myQueue = session.createQueue(queueName);

            MessageConsumer consumer =
                    session.createConsumer(myQueue);

            consumer.setMessageListener(message -> {
                TextMessage receive = (TextMessage)message;

                try {
                    String text = receive.getText();
                    System.out.println(" 成功获取消息 ------ " + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(" 成功获取消息 ");
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (session != null){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
