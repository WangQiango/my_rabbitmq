package redistest;

import redis.clients.jedis.Jedis;

public class Test01 {
    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("01","Hello world!");
        String value = jedis.get("01");
        System.out.println(value);
    }
}
