package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersBinding {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();


        try (Channel channel = connection.createChannel()) {

            //Create an exchange
            channel.exchangeDeclare("my-headers-exchange", BuiltinExchangeType.HEADERS, true);

            //Create the Queues
            channel.queueDeclare("HealthQ", true, false, false, null);
            channel.queueDeclare("SportsQ", true, false, false, null);
            channel.queueDeclare("EducationQ", true, false, false, null);

            /*

                Headers matching algorithm
                ----------------------------

                1) x-match = "all"   Logical AND of the headers
                1) x-match = "any"   Logical OR of the headers

             */

            //Create bindings - (queue, exchange, routingKey, arguments)
            Map<String, Object> healthArgs = new HashMap<>();
            healthArgs.put("x-match", "any"); // match any header
            healthArgs.put("h1", "Header1");
            healthArgs.put("h2", "Header2");
            channel.queueBind("HealthQ", "my-headers-exchange", "", healthArgs);

            Map<String, Object> sportsArgs = new HashMap<>();
            sportsArgs.put("x-match", "all"); // match all the headers
            sportsArgs.put("h1", "Header1");
            sportsArgs.put("h2", "Header2");
            channel.queueBind("SportsQ", "my-headers-exchange", "", sportsArgs);

            Map<String, Object> educationArgs = new HashMap<>();
            educationArgs.put("x-match", "any"); // match any header
            educationArgs.put("h1", "Header1");
            educationArgs.put("h2", "Header2");
            channel.queueBind("EducationQ", "my-headers-exchange", "", educationArgs);
        }
        connection.close();

    }
}
