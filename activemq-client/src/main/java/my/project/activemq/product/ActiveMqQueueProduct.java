package my.project.activemq.product;

import my.project.activemq.commons.Constions;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * activemq客户端
 *
 * @author ZhangWeiKang
 * @create 2018/7/8
 */
public class ActiveMqQueueProduct {

    private Session session;
    private Connection connection;

    public ActiveMqQueueProduct() {
        init();
    }

    private void init(){
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(Constions.mq_userName,Constions.mq_password,Constions.mq_url);

            connection = factory.createConnection();

            connection.start();

            //true:事务性消息;(事务性消息,必须手动commit才会发送到消息中心)
            // false:非事务性消息;(非事务性消息,只要发送就会发送到消息中心)

            //Session.AUTO_ACKNOWLEDGE:当客户端receive返回后,或者MessageListenner.onMessage返回后,会话会确认客户收到消息
            //session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String queueName,String StringMessage){
        try {
            Queue myQueue = session.createQueue(queueName);

            MessageProducer producer =
                        session.createProducer(myQueue);

            /*for (int i=0;i<10;i++){
                TextMessage message = session.createTextMessage(StringMessage+i);
                producer.send(message);
            }*/
            TextMessage message = session.createTextMessage(StringMessage);
            producer.send(message);

            session.commit();
            System.out.println(" 成功发送消息 ");
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
