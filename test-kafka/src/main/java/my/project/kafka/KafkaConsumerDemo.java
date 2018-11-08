package my.project.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * KafkaConsumer
 *
 * @author ZhangWeiKang
 * @create 2018/9/17
 */
public class KafkaConsumerDemo{

    public static void main(String[] args) {

        Properties props = new Properties();
        //集群地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "slave2:9092,slave1:9092,slave3:9092");
        //当前消费组
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo4");
        //自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        //自动提交时间
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        //key反序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //value反序列化类
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //offset位置
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //创建消费端
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Arrays.asList("test"));
        TopicPartition topicPartition = new TopicPartition("test",2);
        consumer.assign(Arrays.asList(topicPartition));
        while (true) {
            //消费消息
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1000));
            for (ConsumerRecord<Integer, String> record : records){
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                System.out.println("offset -> " + record.offset() + ",partition -> " + record.partition());
            }
            //consumer.commitAsync();
        }

    }
}
