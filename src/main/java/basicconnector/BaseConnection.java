package basicconnector;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author WXQ
 */
public class BaseConnection {
    protected String queueName;
    protected Connection connection ;
    protected Channel channel;

    public BaseConnection(){

    }
    public BaseConnection(String queueName) throws IOException{
        this.queueName = queueName;
        //打开连接和创建频道
        ConnectionFactory  factory = new ConnectionFactory();
        //设置RabbitMQ的端口号为本机
        factory.setHost("127.0.0.1");
        //创建连接
        connection = factory.newConnection();
        //创建频道
        channel = connection.createChannel();
        //声明创建队列
        channel.queueDeclare(queueName,false,false,false,null);
    }
}
