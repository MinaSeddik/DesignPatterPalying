package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class DirectBinding {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();



        try (Channel channel = connection.createChannel()) {

            //Create an exchange
            channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);

            //Create the Queues
            channel.queueDeclare("MobileQ", true, false, false, null);
            channel.queueDeclare("ACQ", true, false, false, null);
            channel.queueDeclare("LightQ", true, false, false, null);

            // Create mirrored queue
            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-ha-policy", "all");
            channel.queueDeclare("hello-queue", true, false, false, arguments);

            //Create bindings - (queue, exchange, routingKey)
            channel.queueBind("MobileQ", "my-direct-exchange", "personalDevice");
            channel.queueBind("ACQ", "my-direct-exchange", "homeAppliance");
            channel.queueBind("LightQ", "my-direct-exchange", "homeAppliance");
        }
        connection.close();

    }

}
