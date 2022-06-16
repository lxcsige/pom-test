package rateLimiter.count;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口计数
 *
 * @author xucheng.liu
 * @since 2022/2/28
 */
public class FixedWindowCountRateLimiter {

    /**
     * 阈值
     */
    private int threshold;

    /**
     * 计数器
     */
    private AtomicInteger counter;

    /**
     * 窗口开始时间
     */
    private long start;

    /**
     * 窗口周期，单位为毫秒
     */
    private long period;

    public FixedWindowCountRateLimiter(int threshold) {
        // 默认1秒
        this(threshold, 1000);
    }

    public FixedWindowCountRateLimiter(int threshold, long period) {
        this.threshold = threshold;
        this.period = period;
        counter = new AtomicInteger(0);
        start = System.currentTimeMillis() / period;
    }

    public boolean tryAcquire() {
        long cur = System.currentTimeMillis() / 1000;
        if (cur == start) {
            return counter.incrementAndGet() <= threshold;
        } else {
            // 重置
            start = cur;
            counter.set(0);
            return true;
        }
    }
}
