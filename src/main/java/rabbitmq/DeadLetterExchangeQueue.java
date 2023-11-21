package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class DeadLetterExchangeQueue {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();



        try (Channel channel = connection.createChannel()) {

            //Create a dead letter exchange
            channel.exchangeDeclare("dead-letter-exchange", BuiltinExchangeType.DIRECT, true);

            //Create a dead letter Queue
            channel.queueDeclare("dead-letter-queue", true, false, false, null);

            //Create a binding - (queue, exchange, routingKey)
            channel.queueBind("dead-letter-queue", "dead-letter-exchange", "DeadLetterKey");


            //Create a normal exchange
            channel.exchangeDeclare("normal-exchange", BuiltinExchangeType.DIRECT, true);

            //Create a normal Queue
            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-dead-letter-exchange", "dead-letter-exchange");
            arguments.put("x-dead-letter-routing-key", "DeadLetterKey");
            arguments.put("x-message-ttl", 60000);   // 60 seconds
            channel.queueDeclare("normal-queue", true, false, false, arguments);

            //Create a binding
            channel.queueBind("normal-queue", "normal-exchange", "SomeKey");

            // testing .....
            String message = "Hello from Normal Queue!";
            channel.basicPublish("normal-exchange", "SomeKey", MessageProperties.PERSISTENT_BASIC, message.getBytes());
        }
        connection.close();

    }


}
