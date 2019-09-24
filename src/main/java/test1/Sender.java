package test1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Sender {
    private static final String SEND_QUEUE = "sendQueue";
    
    public static void main(String[] args) throws IOException{
        //创建连接连接到本地的RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.queueDeclare(SEND_QUEUE,false,false,false,null);
        String message = "Hello world!";
        //往队列中发入一条消息
        channel.basicPublish("",SEND_QUEUE,null,message.getBytes());
        System.out.println("Send:"+message);
        //关闭频道和连接
        channel.close();
        connection.close();

    }
}
