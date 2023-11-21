package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutBinding {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();



        try (Channel channel = connection.createChannel()) {

            //Create an exchange
            channel.exchangeDeclare("my-fanout-exchange", BuiltinExchangeType.FANOUT, true);

            //Create the Queues
            channel.queueDeclare("MobileQ", true, false, false, null);
            channel.queueDeclare("ACQ", true, false, false, null);
            channel.queueDeclare("LightQ", true, false, false, null);


            //Create bindings - (queue, exchange, routingKey)
            channel.queueBind("MobileQ", "my-fanout-exchange", "");
            channel.queueBind("ACQ", "my-fanout-exchange", "");
            channel.queueBind("LightQ", "my-fanout-exchange", "");
        }
        connection.close();

    }

}
