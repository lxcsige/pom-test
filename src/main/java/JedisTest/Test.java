package JedisTest;

import redis.clients.jedis.Jedis;

/**
 * User: lxcfour
 * Date: 2018/4/1
 * Time: 下午4:54
 */
public class Test {
    public static Jedis jedis = new Jedis("localhost", 6379);

    public static void main(String[] args) {
        long m1 = Long.valueOf(getMemory());
        insert();
        long m2 = Long.valueOf(getMemory());
        System.out.println(m2 - m1);
    }

    public static void insert() {
        for (int i = 10000; i < 100000; i++) {
            jedis.del("aaa" + i);
            jedis.del("aa" + i);
        }
    }

    public static String getMemory() {
        String memoryAllLine = jedis.info("memory");
        System.out.println(memoryAllLine);
        String usedMemoryLine = memoryAllLine.split("\r\n")[1];
        String memory = usedMemoryLine.substring(usedMemoryLine.indexOf(':') + 1);
        return memory;
    }
}
