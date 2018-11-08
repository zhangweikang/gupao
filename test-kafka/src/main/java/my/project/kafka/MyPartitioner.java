package my.project.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 分区发送
 *
 * @author ZhangWeiKang
 * @create 2018/9/27
 */
public class MyPartitioner implements Partitioner {

    private static final Random random = new Random();
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int partitionerNum;
        List<PartitionInfo> partitionInfos =
                cluster.availablePartitionsForTopic(topic);

        if (key == null){
            partitionerNum = random.nextInt(partitionInfos.size());
        } else {
            partitionerNum = Math.abs((key.hashCode())%(partitionInfos.size()));
        }
        return partitionerNum;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
