package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicRabbitMq {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
//        Connection connection = factory.newConnection();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        //Create an exchange
        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);
        //TODO - Uncomment the below lines and see
        //channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);
        //channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);


        // Other exchange type
//        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.TOPIC, true);
//        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.FANOUT, true);
//        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.HEADERS, true);


        //Create the Queues
        channel.queueDeclare("MobileQ", true, false, false, null);
        channel.queueDeclare("ACQ", true, false, false, null);
        channel.queueDeclare("LightQ", true, false, false, null);

        channel.close();
        connection.close();

//        TimeUnit.MINUTES.sleep(5);
    }

}
