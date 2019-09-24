package basicconnector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.api.scripting.JSObject;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasicTest {
    public static void main(String[] args) throws IOException,InterruptedException{
        String sendQueue = "sendQueue";
        //接受者
        SendToDmsReceiver receiver = new SendToDmsReceiver(sendQueue);
        Thread receiveThread = new Thread(receiver);
        receiveThread.start();
        //发送者
        Sender sender = new Sender(sendQueue);
        for (int i = 1;i <= 10; i++){
            ArrayList list = new ArrayList();
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(String.valueOf(i),"第"+i+"条消息");
            list.add(jsonObj);
            sender.sendMessage(list);
            System.out.println("send:"+ list.toString());
//            Thread.sleep(1000);
        }
    }
}
