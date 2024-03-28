package kafka;

import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

public class BananaPartitioner extends DefaultPartitioner implements Partitioner {
    public BananaPartitioner() {
    }

    public void configure(Map<String, ?> configs) {
    }

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (keyBytes != null && key instanceof String) {
            return key.equals("Banana") ? numPartitions : Math.abs(Utils.murmur2(keyBytes)) % (numPartitions - 1);
        } else {
            return super.partition(topic, key, keyBytes, value, valueBytes, cluster);
        }
    }

    public void close() {
    }
}
