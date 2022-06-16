package rateLimiter.distributed;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * User: lxcfour
 * Date: 2018/4/10
 * Time: 下午7:33
 */
public class RateLimiter {

    private JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    /**
     * 向令牌桶中加入令牌的速率
     */
    private double rate;

    /**
     * 令牌桶的最大容量
     */
    private long limit;

    // 时间片大小, 秒
    private int timeout;

    private RateLimiter(double rate, long limit, int timeout) {
        this.rate = rate;
        this.limit = limit;
        this.timeout = timeout;
    }

    public boolean access(String userId) {
        Jedis jedis = jedisPool.getResource();
        String key = genKey(userId);
        Map<String, String> counter = jedis.hgetAll(key);
        if (counter.size() == 0) { // key不存在则新增
            TokenBucket tokenBucket = new TokenBucket(System.currentTimeMillis(), limit - 1);
            jedis.hmset(key, tokenBucket.toHash());
            return true;
        } else {
            TokenBucket tokenBucket = TokenBucket.fromHash(counter);

            long lastRefillTime = tokenBucket.getLastRefillTime();
            long refillTime = System.currentTimeMillis();
            long intervalSinceLast = refillTime - lastRefillTime;

            long currentTokensRemaining;
            if (intervalSinceLast > timeout) { // 间隔大于一个时间片的时间，直接将桶中的令牌数设为最大值
                currentTokensRemaining = limit;
            } else{
                long grantedTokens = (long) (intervalSinceLast * rate);
                currentTokensRemaining = Math.min(grantedTokens + tokenBucket.getTokensRemaining(), limit);
            }

            tokenBucket.setLastRefillTime(refillTime);
            if (currentTokensRemaining == 0) {
                tokenBucket.setTokensRemaining(currentTokensRemaining);
                return false;
            } else {
                tokenBucket.setTokensRemaining(currentTokensRemaining - 1);
                return true;
            }
        }
    }

    private String genKey(String userId) {
        return "rate:limiter:" + userId;
    }

    public static class TokenBucket {
        private long lastRefillTime;
        private long tokensRemaining;

        public TokenBucket(long lastRefillTime, long tokensRemaining) {
            this.lastRefillTime = lastRefillTime;
            this.tokensRemaining = tokensRemaining;
        }

        public static TokenBucket fromHash(Map<String, String> hash) {
            long lastRefillTime = Long.parseLong(hash.get("lastRefillTime"));
            int tokensRemaining = Integer.parseInt(hash.get("tokensRemaining"));
            return new TokenBucket(lastRefillTime, tokensRemaining);
        }

        public Map<String, String> toHash() {
            Map<String, String> hash = new HashMap<String, String>();
            hash.put("lastRefillTime", String.valueOf(lastRefillTime));
            hash.put("tokensRemaining", String.valueOf(tokensRemaining));
            return hash;
        }

        public long getLastRefillTime() {
            return lastRefillTime;
        }

        public void setLastRefillTime(long lastRefillTime) {
            this.lastRefillTime = lastRefillTime;
        }

        public long getTokensRemaining() {
            return tokensRemaining;
        }

        public void setTokensRemaining(long tokensRemaining) {
            this.tokensRemaining = tokensRemaining;
        }
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(0.5, 3, 1000);

        boolean flag;
        for (int i = 0; i < 3; i++) {
            flag = rateLimiter.access("lxc");
            System.out.println(flag);
        }

        flag = rateLimiter.access("lxc");
        System.out.println(flag);

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = rateLimiter.access("lxc");
        System.out.println(flag);
    }
}
