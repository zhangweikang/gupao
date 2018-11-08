package my.project.activemq.consumer;

import my.project.activemq.commons.Constions;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * 消费者
 *
 * @author ZhangWeiKang
 * @create 2018/7/8
 */
public class ActiveMqTopicConsumer {

    private Connection connection;
    private Session session;

    public ActiveMqTopicConsumer() {
        init();
    }

    private void init() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(Constions.mq_userName,Constions.mq_password,Constions.mq_url);

            connection = factory.createConnection();

            connection.setClientID("my-001");

            connection.start();

            //true:事务性消息;(事务性消息,必须手动commit才会发送到消息中心)
            // false:非事务性消息;(非事务性消息,只要发送就会发送到消息中心)

            //Session.AUTO_ACKNOWLEDGE:当客户端receive返回后,或者MessageListenner.onMessage返回后,会话会确认客户收到消息
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String getStringMessage(String topicName){
        try {

            Topic topic = session.createTopic(topicName);

            /*MessageConsumer consumer =
                    session.createConsumer(topic);*/

            TopicSubscriber consumer = session.createDurableSubscriber(topic, "my-001");


            TextMessage receive =
                    (TextMessage)consumer.receive();

            System.out.println(" topic------成功获取消息 ");
            return receive.getText();
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
