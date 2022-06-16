package rateLimiter.leakyBucket;

/**
 * 漏桶算法，仅提供了是否被限流的接口，未实现固定速率的请求漏出
 *
 * @author xucheng.liu
 * @since 2022/3/1
 */
public class LeakyBucketRateLimiter {

    /**
     * 漏桶容量
     */
    private final int capacity;

    /**
     * 漏出速率
     */
    private final int permitsPerSecond;

    /**
     * 剩余水量
     */
    private long leftWater;

    /**
     * 上次注入时间
     */
    private long timeStamp = System.currentTimeMillis();

    public LeakyBucketRateLimiter(int permitsPerSecond, int capacity) {
        this.capacity = capacity;
        this.permitsPerSecond = permitsPerSecond;
    }

    public synchronized boolean tryAcquire() {
        // 计算剩余水量
        long cur = System.currentTimeMillis();
        leftWater = Math.max(0, leftWater - (cur - timeStamp) / 1000 * permitsPerSecond);
        // 更新注水时间
        timeStamp = cur;

        if (leftWater < capacity) {
            leftWater++;
            return true;
        }
        return false;
    }
}
