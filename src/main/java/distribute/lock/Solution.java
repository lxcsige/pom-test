package distribute.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author lxcfour
 * @date 2018/4/11
 */

public class Solution {
    private static final String LOCK_SUCCESS = "OK";

    private static final Long RELEASE_SUCCESS = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "EX";

    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        System.out.println(result);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new Thread("thread-1") {
            @Override
            public void run() {
                Jedis jedis = new Jedis("127.0.0.1", 6379);
                boolean result = tryGetDistributedLock(jedis, "Test:lock", "thread-1", 5);
                System.out.println("thread-1, lock result = " + result);
                result = releaseDistributedLock(jedis, "Test:lock", "thread-1");
                System.out.println("thread-1, unlock result = " + result);
            }
        }.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread("thread-2") {
            @Override
            public void run() {
                boolean result = tryGetDistributedLock(new Jedis("127.0.0.1", 6379), "Test:lock", "thread-2", 5);
                System.out.println("thread-2, result = " + result);
            }
        }.start();
        new Thread("thread-3") {
            @Override
            public void run() {
                boolean result = tryGetDistributedLock(new Jedis("127.0.0.1", 6379), "Test:lock", "thread-3", 5);
                System.out.println("thread-3, result = " + result);
            }
        }.start();
    }
}
