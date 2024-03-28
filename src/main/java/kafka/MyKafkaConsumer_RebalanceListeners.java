
package kafka;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import twitter4j.JSONObject;

public class MyKafkaConsumer_RebalanceListeners {
    private static KafkaConsumer<String, String> consumer;
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap();

    public MyKafkaConsumer_RebalanceListeners() {
    }

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9092");
        kafkaProps.put("group.id", "CountryCounter");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(kafkaProps);
        consumer.subscribe(Collections.singletonList("customerCountries"), new HandleRebalance());
        HashMap<String, Integer> custCountryMap = new HashMap();

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(100L);
                Iterator var4 = records.iterator();

                while(var4.hasNext()) {
                    ConsumerRecord<String, String> record = (ConsumerRecord)var4.next();
                    System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n %n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    int updatedCount = 1;
                    if (custCountryMap.containsValue(record.value())) {
                        updatedCount = (Integer)custCountryMap.get(record.value()) + 1;
                    }

                    custCountryMap.put((String)record.value(), updatedCount);
                    JSONObject json = new JSONObject(custCountryMap);
                    System.out.println(json.toString(4));
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1L, "no metadata"));
                }

                consumer.commitAsync(new OffsetCommitCallback() {
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception e) {
                        if (e != null) {
                            System.out.println("Commit failed for offsets :" + offsets + " Exception: " + e);
                        }

                    }
                });
            }
        } catch (WakeupException var37) {
        } catch (Exception var38) {
            System.out.println("Unexpected error: " + var38);
        } finally {
            try {
                consumer.commitSync(currentOffsets);
            } finally {
                consumer.close();
                System.out.println("Closed consumer and we are done");
            }
        }

    }

    private static class HandleRebalance implements ConsumerRebalanceListener {
        private HandleRebalance() {
        }

        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        }

        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            System.out.println("Lost partitions in rebalance. Committing current offsets:" + MyKafkaConsumer_RebalanceListeners.currentOffsets);
            MyKafkaConsumer_RebalanceListeners.consumer.commitSync(MyKafkaConsumer_RebalanceListeners.currentOffsets);
        }
    }
}
