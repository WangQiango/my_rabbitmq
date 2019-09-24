package basicconnector;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author WXQ
 */
public class Sender extends BaseConnection{

    public Sender (String queueName) throws IOException{
        super(queueName);
    }
    public void sendMessage(Serializable object) throws IOException{
        channel.basicPublish("",queueName,null, SerializationUtils.serialize(object));
    }
}
