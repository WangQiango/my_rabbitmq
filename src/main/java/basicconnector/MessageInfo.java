package basicconnector;

import java.io.Serializable;

/**
 * 关于序列化，序列化就是将一个对象的状态（各个属性量）保存起来，然后在适当的时候再获得。
 * 序列化分为两大部分：序列化和反序列化。
 * 序列化是这个过程的第一部分，将数据分解成字节流，以便存储在文件中或在网络上传输。
 * 反序列化就是打开字节流并重构对象。
 * 对象序列化不仅要将基本数据类型转换成字节表示，有时还要恢复数据。
 * 恢复数据要求有恢复数据的对象实例。
 */

/**
 * @author WXQ
 */
public class MessageInfo implements Serializable {
    private static final Long SERIVAL_VERSION_UID = 1L;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 来源
     */
    private String content;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
