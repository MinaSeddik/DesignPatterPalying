package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


//Reference: https://www.youtube.com/watch?v=wcnb9EpKTDA&list=PL1xVF1dBM4bnc-NeY-yBMvJuKXY6IY9yP
public class ExchangeToExchangeBinding {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();



        try (Channel channel = connection.createChannel()) {

            //Create 2 exchanges
            channel.exchangeDeclare("linked-direct-exchange", BuiltinExchangeType.DIRECT, true);
            channel.exchangeDeclare("home-direct-exchange", BuiltinExchangeType.DIRECT, true);

            //Create the Queues for linked-direct-exchange
            channel.queueDeclare("MobileQ", true, false, false, null);
            channel.queueDeclare("ACQ", true, false, false, null);
            channel.queueDeclare("LightQ", true, false, false, null);

            //Create the Queues for home-direct-exchange
            channel.queueDeclare("FanQ", true, false, false, null);
            channel.queueDeclare("LaptopQ", true, false, false, null);


            //Create bindings - (queue, exchange, routingKey)
            channel.queueBind("MobileQ", "linked-direct-exchange", "personalDevice");
            channel.queueBind("ACQ", "linked-direct-exchange", "homeAppliance");
            channel.queueBind("LightQ", "linked-direct-exchange", "homeAppliance");

            //Create bindings - (queue, exchange, routingKey)
            channel.queueBind("FanQ", "home-direct-exchange", "homeAppliance");
            channel.queueBind("LaptopQ", "home-direct-exchange", "personalDevice");


            // Bind the source Exchange with a distinction Exchange
            channel.exchangeBind("linked-direct-exchange", "home-direct-exchange", "homeAppliance");

        }
        connection.close();

    }
}
