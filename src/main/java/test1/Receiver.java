package test1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * @author Administrator
 */
public class Receiver {
    private static final String SEND_QUEUE = "sendQueue";
    /**
     *     设置接收队列，
     *     接收者将接收到的消息进行处理之后保存到接收队列中，
     *     由另一个接收者对接收队列进行监听然后处理数据保存到redis中
     */
    private static final String RECEIVE_QUQUQ = "receiveQueue";

    public static void main(String[] args) throws IOException,InterruptedException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(SEND_QUEUE, false, false, false, null);
        System.out.println("Waiting for messages. To exit press CTRL+C");

        //创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //指定消费队列
        channel.basicConsume(SEND_QUEUE,true,consumer);
        while (true){
            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String receiveMessage = new String(delivery.getBody());
            System.out.println("Received Message ：" +receiveMessage);
            Channel channel2 = connection.createChannel();
            channel2.queueDeclare(RECEIVE_QUQUQ,false,false,false,null);
            receiveMessage = receiveMessage.toUpperCase();
            channel2.basicPublish("",RECEIVE_QUQUQ,null,receiveMessage.getBytes());
            System.out.println("after operate :" +receiveMessage);
            channel2.close();
        }
    }
}
