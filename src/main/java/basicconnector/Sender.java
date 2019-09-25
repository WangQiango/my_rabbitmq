package basicconnector;

import com.rabbitmq.client.AMQP;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author WXQ
 */
public class Sender extends BaseConnection{

//    private RabbitTemplate template = new RabbitTemplate();
    public Sender (String queueName) throws IOException{
        super(queueName);
    }
    public void sendMessage(Serializable object) throws IOException{
//        AMQP.BasicProperties
//        template.convertAndSend("sendQueue",object);
        channel.basicPublish("",queueName,null, SerializationUtils.serialize(object));
    }
}
