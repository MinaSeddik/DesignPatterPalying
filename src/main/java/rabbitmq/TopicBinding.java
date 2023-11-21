package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicBinding {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();


        try (Channel channel = connection.createChannel()) {

            //Create an exchange
            channel.exchangeDeclare("my-topic-exchange", BuiltinExchangeType.TOPIC, true);

            //Create the Queues
            channel.queueDeclare("HealthQ", true, false, false, null);
            channel.queueDeclare("SportsQ", true, false, false, null);
            channel.queueDeclare("EducationQ", true, false, false, null);

            /*

                Topic Routing key (Routing pattern)
                ------------------------------------
                * : exactly one word allowed
                # : zero or number of words
                . : is a word delimiter

                examples:
                ----------
                health.*
                #.sports.*
                #.education

             */

            //Create bindings - (queue, exchange, routingKey)
            channel.queueBind("HealthQ", "my-topic-exchange", "health.*");
            channel.queueBind("SportsQ", "my-topic-exchange", "#.sports.*");
            channel.queueBind("EducationQ", "my-topic-exchange", "#.education");
        }
        connection.close();

    }

}
