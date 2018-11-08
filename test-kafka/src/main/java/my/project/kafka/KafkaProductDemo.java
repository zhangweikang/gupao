package my.project.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * Hello world!
 */
public class KafkaProductDemo extends Thread{
    Producer<String, String> producer = null;
    public KafkaProductDemo() {
        Properties props = new Properties();
        //集群地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "slave2:9092,slave1:9092,slave3:9092");
        //ask确认返回处理 -1不处理, -1:需要集群中的所有节点确认
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        //当前服务名称
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"KafkaProductDemo");
        //key序列化类
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //value序列化类
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"my.project.kafka.MyPartitioner");
        producer = new KafkaProducer<>(props);
    }

    public static void main(String[] args) {
        new KafkaProductDemo().start();
    }

    @Override
    public void run() {
        //创建producer端
        for (int i = 0; i < 100; i++){
            //发送数据
            producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("partition -> " + metadata.partition() + ";offset" + metadata.offset());
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}
