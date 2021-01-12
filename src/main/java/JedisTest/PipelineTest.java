package JedisTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * User: lxcfour
 * Date: 2018/5/4
 * Time: 下午7:40
 */
public class PipelineTest {
    public static Jedis jedis = new Jedis("localhost", 6379);

    public static void main(String[] args) {
        Pipeline p = jedis.pipelined();
        p.set("k1", "v1");
        p.set("k2", "v2");
        p.set("k3", "v3");
        p.set("k4", "v4");
        List<Object> res = p.syncAndReturnAll();
    }
}
