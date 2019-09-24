package basicconnector;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.List;


public class SendToDmsReceiver extends Receiver {

    /**
     * @author WXQ
     * @deprecated 将自核请求消息发送到DMS系统去
     */
    public SendToDmsReceiver(String queueName) throws IOException{
        super(queueName);
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) {
        List msgList = (List) SerializationUtils.deserialize(bytes);
        System.out.println("将自核消息:"+msgList.toString()+"发送到DMS自核系统进行自核");
        System.out.println("咔咔咔，自核系统处理完了，将自核结果public到自核返回消息队列中去了");
    }
}
