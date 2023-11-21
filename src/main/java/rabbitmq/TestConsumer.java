package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestConsumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("Inside DeliverCallback ...");
            System.out.println(consumerTag);

            // acknowledge receiving of the massage if it is NOT auto-ack
//            channel.basicAck(consumerTag, false);

//            boolean isJsonMessage = message.getProperties().getContentType().equals("application/json");
            if (message.getBody().equals("quit")) {
                channel.basicCancel(consumerTag);
            }else {
                System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
            }

        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("Inside CancelCallback ...");
            System.out.println(consumerTag);
        };

//        channel.basicConsume("LightQ", true, deliverCallback, cancelCallback);
//        channel.basicConsume("LightQ", false, deliverCallback, cancelCallback);

//        channel.basicConsume("ACQ", true, deliverCallback, cancelCallback);


        channel.basicConsume("MobileQ", true, deliverCallback, cancelCallback);
        channel.basicConsume("ACQ", true, deliverCallback, cancelCallback);
        channel.basicConsume("LightQ", true, deliverCallback, cancelCallback);
        channel.basicConsume("HealthQ", true, deliverCallback, cancelCallback);
        channel.basicConsume("SportsQ", true, deliverCallback, cancelCallback);
        channel.basicConsume("EducationQ", true, deliverCallback, cancelCallback);

        TimeUnit.MINUTES.sleep(15);
    }


}
