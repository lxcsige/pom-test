package RateLimiterTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.file.Files;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: lxcfour
 * Date: 2018/4/10
 * Time: 下午9:03
 */
public class RateLimiter2 {
    private JedisPool jedisPool;

    private long intervalInMills;

    private long limit;

    private double rate;

    private String scriptSha1;

    public RateLimiter2() throws IOException {
        jedisPool = new JedisPool("127.0.0.1", 6379);
        intervalInMills = 10000;
        limit = 3;
        rate = 0.49;

        Path path = Paths.get("rate_limiter.lua");
        byte[] scriptBytes = Files.readAllBytes(path);
        String script = new String(scriptBytes);

        Jedis jedis = jedisPool.getResource();
        scriptSha1 = jedis.scriptLoad(script);
    }

    private String genKey(String userId) {
        return "rate:limiter:" + userId;
    }

    public boolean access(String user) {
        String key = genKey(user);

        Jedis jedis = jedisPool.getResource();
        long currentMillis = System.currentTimeMillis();
        System.out.println(currentMillis);
        // 将当前时间戳传到redis也存在问题，前后两次请求的时间戳可能会一样
        Object result = jedis.evalsha(scriptSha1, 1, key, String.valueOf(rate),
                String.valueOf(currentMillis), String.valueOf(limit), String.valueOf(intervalInMills));
        System.out.println(result);
        return true;
    }

    public static void main(String[] args) throws IOException {
        RateLimiter2 rateLimiter2 = new RateLimiter2();
//        boolean flag;
//        for (int i = 0; i < 4; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            flag = rateLimiter2.access("lxc");
//            System.out.println(flag);
//        }
        Jedis jedis = rateLimiter2.jedisPool.getResource();
        Object result = jedis.eval("local t = redis.call('TIME') return tonumber(t[1])");
        System.out.println(result);
    }
}
