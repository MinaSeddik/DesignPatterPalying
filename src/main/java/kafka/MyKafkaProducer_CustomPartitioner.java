
package kafka;

import java.util.Properties;
import java.util.concurrent.Future;
import kafka.model.Client;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MyKafkaProducer_CustomPartitioner {
    public MyKafkaProducer_CustomPartitioner() {
    }

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", CustomerSerializer.class);
        kafkaProps.put("acks", "all");
        kafkaProps.put("buffer.memory", 33554432);
        kafkaProps.put("compression.type", "snappy");
        kafkaProps.put("retries", 0);
        kafkaProps.put("batch.size", 16384);
        kafkaProps.put("linger.ms", 1);
        kafkaProps.put("client.id", "my_application_name");
        kafkaProps.put("partitioner.class", BananaPartitioner.class);
        KafkaProducer<String, Client> producer = new KafkaProducer(kafkaProps);
        Client customer = new Client(1, "France");
        ProducerRecord<String, Client> record = new ProducerRecord("CustomerCountry", "Precision Products", customer);

        try {
            producer.send(record);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        try {
            Future<RecordMetadata> future = producer.send(record);
            future.get();
        } catch (Exception var6) {
            var6.printStackTrace();
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
