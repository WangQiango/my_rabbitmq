package basicconnector;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author WXQ
 * 这里，接收者实现了，Runnable接口和com.rabbitmq.client.Consumer接口。
 * 实现Runnable接口的目的是为了实现多线程
 * java实现多线程的方式有两种：一种是继承Thread类，一种是实现Runnable接口
 */
public class Receiver extends BaseConnection implements Runnable ,Consumer{
    public Receiver( ){

    }
    public Receiver(String queueName) throws IOException{
        super(queueName);
    }

    /**
     * 实现Runnable的run方法
     */
    public void run() {
        try {
            channel.basicConsume(queueName,true,this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //下面这些方法都是实现conusme接口的

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer" +consumerTag +"registered");
    }

    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) {
        List messageList = (List) SerializationUtils.deserialize(bytes);
        System.out.println("Receive:"+messageList.toString());
    }

    public void handleCancelOk(String s) {

    }

    public void handleCancel(String s) {

    }

    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    public void handleRecoverOk(String s) {

    }
}
