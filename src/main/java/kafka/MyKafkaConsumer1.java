package kafka;

import java.util.Map;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

class MyKafkaConsumer1 implements OffsetCommitCallback {
    MyKafkaConsumer1() {
    }

    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception e) {
        if (e != null) {
            System.out.println("Commit failed for offsets :" + offsets + " Exception: " + e);
        }

    }
}
