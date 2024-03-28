package kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

public class MyKafkaConsumer_commitOffset {
    public MyKafkaConsumer_commitOffset() {
    }

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9092");
        kafkaProps.put("group.id", "CountryCounter");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.setProperty("enable.auto.commit", "false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer(kafkaProps);
        consumer.subscribe(Collections.singletonList("customerCountries"));
        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap();
        int count = 0;

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(100L);

                for(Iterator var6 = records.iterator(); var6.hasNext(); ++count) {
                    ConsumerRecord<String, String> record = (ConsumerRecord)var6.next();
                    System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1L, "no metadata"));
                    if (count % 1000 == 0) {
                        consumer.commitAsync(currentOffsets, (OffsetCommitCallback)null);
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }
}
