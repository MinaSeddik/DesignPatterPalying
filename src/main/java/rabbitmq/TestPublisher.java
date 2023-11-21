package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //Basic publish
        String message = "Turn on home appliances";
//        channel.basicPublish("my-direct-exchange", "homeAppliance", null, message.getBytes());
        channel.basicPublish("my-direct-exchange", "homeAppliance", MessageProperties.PERSISTENT_BASIC, message.getBytes());
//        channel.basicPublish("my-fanout-exchange", "", null, message.getBytes());
//        channel.basicPublish("my-topic-exchange", "health.education", null, message.getBytes());

//        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("h1", "Header1");
//        headerMap.put("h3", "Header3");
//        BasicProperties properties = new BasicProperties()
//                .builder().headers(headerMap).build();
//        channel.basicPublish("my-headers-exchange", "", properties, message.getBytes());

        // exchange to exchange bind publish test
//        channel.basicPublish("home-direct-exchange", "homeAppliance", null, message.getBytes());

        /*
            The default exchange in RabbitMQ Exchanges is a pre-declared (empty string) direct exchange with no name.
            The default exchange will deliver the message to the queue whose name matches the routing key
         */
//        channel.basicPublish("", "products_queue", null, message.getBytes());


        //Close the channel and connection
        channel.close();
        connection.close();
    }

}
