package rateLimiter;

/**
 * 令牌桶算法
 *
 * @author xucheng.liu
 * @since 2022/3/1
 */
public class TokenBucketRateLimiter {

    /**
     * 令牌桶的容量「限流器允许的最大突发流量」
     */
    private final long capacity;

    /**
     * 令牌发放速率
     */
    private final long permitsPerSeconds;

    /**
     * 最后一个令牌发放的时间
     */
    long lastTokenTime = System.currentTimeMillis();

    /**
     * 当前令牌数量
     */
    private long currentTokens;

    public TokenBucketRateLimiter(long permitsPerSeconds, int capacity) {
        this.permitsPerSeconds = permitsPerSeconds;
        this.capacity = capacity;
    }

    /**
     * 尝试获取令牌
     *
     * @return true表示获取到令牌，放行；否则为限流
     */
    public synchronized boolean tryAcquire() {
        /**
         * 计算令牌当前数量
         * 请求时间在最后令牌是产生时间相差大于等于1s（为啥时1s？因为生成令牌的最小时间单位是s），则
         * 1. 重新计算令牌桶中的令牌数
         * 2. 将最后一个令牌发放时间重置为当前时间
         */
        long now = System.currentTimeMillis();
        if (now - lastTokenTime >= 1000) {
            long newPermits = (now - lastTokenTime) / 1000 * permitsPerSeconds;
            currentTokens = Math.min(currentTokens + newPermits, capacity);
            lastTokenTime = now;
        }
        if (currentTokens > 0) {
            currentTokens--;
            return true;
        }
        return false;
    }
}
