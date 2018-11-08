package my.project.activemq.product;

import my.project.activemq.commons.Constions;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * activemq客户端
 *
 * @author ZhangWeiKang
 * @create 2018/7/8
 */
public class ActiveMqTopicProduct {

    private Session session;
    private Connection connection;

    public ActiveMqTopicProduct() {
        init();
    }

    private void init(){
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

    public void sendMessage(String topicName,String StringMessage){
        try {
            Topic topic = session.createTopic(topicName);

            TextMessage message = session.createTextMessage(StringMessage);

            session.createProducer(topic).send(message);

            System.out.println(" topic------成功发送消息 ");
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
    }
}
