package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

// Reference: https://www.rabbitmq.com/tutorials/tutorial-seven-java.html
public class TestPublisherWithConfirmationChannel {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Enabling Publisher Confirms on a Channel
        channel.confirmSelect();

        ConcurrentNavigableMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

        //Basic publish
        String message = "Turn on home appliances";
//        long publishMessageId = channel.getNextPublishSeqNo();
        outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        channel.basicPublish("my-direct-exchange", "homeAppliance", MessageProperties.PERSISTENT_BASIC, message.getBytes());
//        System.out.println("publishMessageId: " + publishMessageId);
        // Handle Confirmation

//        Strategy #1: Publishing Messages Individually
        channel.waitForConfirmsOrDie(5_000);

//        Strategy #2: Publishing Messages in Batches
        int batchSize = 100;
        int outstandingMessageCount = 0;
        while (thereAreMessagesToPublish()) {
            channel.basicPublish("my-direct-exchange", "homeAppliance", MessageProperties.PERSISTENT_BASIC, message.getBytes());
            outstandingMessageCount++;
            if (outstandingMessageCount == batchSize) {
                channel.waitForConfirmsOrDie(5_000);
                outstandingMessageCount = 0;
            }
        }
        if (outstandingMessageCount > 0) {
            channel.waitForConfirmsOrDie(5_000);
        }

//        Strategy #3: Handling Publisher Confirms Asynchronously
        ConfirmCallback cleanOutstandingConfirms = (sequenceNumber, multiple) -> {
            if (multiple) {
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(
                        sequenceNumber, true
                );
                confirmed.clear();
            } else {
                outstandingConfirms.remove(sequenceNumber);
            }
        };

        channel.addConfirmListener(cleanOutstandingConfirms, (sequenceNumber, multiple) -> {
            String body = outstandingConfirms.get(sequenceNumber);
            System.err.format("Message with body %s has been nack-ed. Sequence number: %d, multiple: %b%n",
                    body, sequenceNumber, multiple);
            cleanOutstandingConfirms.handle(sequenceNumber, multiple);
        });


        //Close the channel and connection
        channel.close();
        connection.close();
    }

    private static boolean thereAreMessagesToPublish() {

        // some logic

        return false;
    }

}
