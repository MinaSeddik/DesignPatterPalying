package kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MyKafkaProducer {
    public MyKafkaProducer() {
    }

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("acks", "all");
        kafkaProps.put("buffer.memory", 33554432);
        kafkaProps.put("compression.type", "snappy");
        kafkaProps.put("retries", 0);
        kafkaProps.put("batch.size", 16384);
        kafkaProps.put("linger.ms", 1);
        kafkaProps.put("client.id", "my_application_name");
        KafkaProducer<String, String> producer = new KafkaProducer(kafkaProps);
        ProducerRecord<String, String> record = new ProducerRecord("CustomerCountry", "Precision Products", "France");

        try {
            producer.send(record);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        try {
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata response = (RecordMetadata)future.get();
            Map<Integer, List<Integer>> partitionMap = new HashMap();
            int partition = response.partition();
            List<Integer> list = (List)partitionMap.getOrDefault(partition, new ArrayList());
            list.add(partition);
            partitionMap.put(partition, list);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        producer.send(record, new DemoProducerCallback());
        producer.close();
    }


    private static class DemoProducerCallback implements Callback {
        private DemoProducerCallback() {
        }

        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
            }

        }
    }
}
