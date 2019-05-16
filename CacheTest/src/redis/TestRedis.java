package redis;
import redis.clients.jedis.Jedis;
public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("hello", "world");
        String value = jedis.get("hello");
        System.out.println(value);
    }
}
