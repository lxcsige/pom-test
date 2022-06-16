package rateLimiter.leakyBucket;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xucheng.liu
 * @since 2022/3/1
 */
public class LeakyBucketRateLimiter2 implements Runnable {

    /**
     * 出口限制qps
     */
    private int permitsPerSecond;
    /**
     * 漏桶队列
     */
    private BlockingQueue<Thread> leakyBucket;

    private ScheduledExecutorService scheduledExecutorService;

    public LeakyBucketRateLimiter2(int capacity, int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        this.leakyBucket = new LinkedBlockingDeque<>(capacity);

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        long interval = (1000 * 1000 * 1000) / permitsPerSecond;
        // 定时取出队列中的第一个线程，并唤醒，实现固定速率漏出请求
        scheduledExecutorService.scheduleAtFixedRate(this, 0, interval, TimeUnit.NANOSECONDS);
    }

    public boolean canPass() {
        // 桶已满
        if (leakyBucket.remainingCapacity() == 0) {
            return false;
        }
        // 放入桶中
        leakyBucket.offer(Thread.currentThread());
        // 阻塞线程，直到被定时任务唤醒
        LockSupport.park();
        return true;
    }

    @Override
    public void run() {
        // 取出队列中的第一个线程并唤醒
        Thread thread = leakyBucket.poll();
        if (Objects.nonNull(thread)) {
            LockSupport.unpark(thread);
        }
    }
}
