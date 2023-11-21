package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DeadLetterExchangeQueueConsumerTest {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//        channel.basicConsume("normal-queue", false, deliverCallback, cancelCallback);
        channel.basicConsume("normal-queue", false, "consumertag", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                // simulate rejection
                System.out.println("Rejected: " + new String(body, "UTF-8") +
                        ", deliveryTag: " + envelope.getDeliveryTag() + ", messageId: " + properties.getMessageId());
                channel.basicReject(envelope.getDeliveryTag(), false);
            }
        });

        TimeUnit.MINUTES.sleep(15);
    }


}
