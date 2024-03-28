//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import twitter4j.JSONObject;

public class MyKafkaConsumer {
    public MyKafkaConsumer() {
    }

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9092");
        kafkaProps.put("group.id", "CountryCounter");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.setProperty("enable.auto.commit", "true");
        kafkaProps.setProperty("auto.commit.interval.ms", "5000");
        KafkaConsumer<String, String> consumer = new KafkaConsumer(kafkaProps);
        consumer.subscribe(Collections.singletonList("customerCountries"));
        HashMap<String, Integer> custCountryMap = new HashMap();

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(100L);
                Iterator var5 = records.iterator();

                while(var5.hasNext()) {
                    ConsumerRecord<String, String> record = (ConsumerRecord)var5.next();
                    System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n %n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    int updatedCount = 1;
                    if (custCountryMap.containsValue(record.value())) {
                        updatedCount = (Integer)custCountryMap.get(record.value()) + 1;
                    }

                    custCountryMap.put((String)record.value(), updatedCount);
                    JSONObject json = new JSONObject(custCountryMap);
                    System.out.println(json.toString(4));
                }

                try {
                    consumer.commitSync();
                } catch (CommitFailedException var12) {
                    System.out.println("commit failed" + var12);
                }

                consumer.commitAsync(new OffsetCommitCallback() {
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception e) {
                        if (e != null) {
                            System.out.println("Commit failed for offsets :" + offsets + " Exception: " + e);
                        }

                    }
                });
            }
        } finally {
            consumer.close();
        }
    }
}
