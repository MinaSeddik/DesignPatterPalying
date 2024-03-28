package kafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;

public class WordCountExample {
    public WordCountExample() {
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("application.id", "wordcountX");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("default.key.serde", Serdes.String().getClass().getName());
        props.put("default.value.serde", Serdes.String().getClass().getName());
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream("wordcount-input");
        Pattern pattern = Pattern.compile("\\W+");

        KStream<String, String> counts = source.peek((k, v) -> {
            System.out.println("Observed event: " + v);
        }).flatMapValues((value) -> {
            return Arrays.asList(pattern.split(value.toLowerCase()));
        }).map((key, value) -> {
            return new KeyValue(value, value);
        }).filter((key, value) -> {
            return !value.equals("the");
        }).groupByKey().count(Named.as("CountStore")).mapValues((value) -> {
            return Long.toString((Long) value);
        }).toStream();

        counts.to("wordcount-output");
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp();
        streams.start();
        Thread.sleep(5000L);
        streams.close();
    }
}
