package rateLimiter.count;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 滑动窗口计数
 *
 * @author xucheng.liu
 * @since 2022/3/1
 */
@Slf4j
public class SlidingWindowCountRateLimiter implements Runnable {

    /**
     * 阈值
     */
    private int threshold;

    /**
     * 计数器
     */
    private AtomicInteger counter;

    /**
     * 窗口数
     */
    private int windowSize;

    /**
     * 每个窗口时间间隔大小
     */
    private long windowPeriod;
    private TimeUnit timeUnit;


    private Window[] windows;
    private volatile Integer windowIndex = 0;

    private Lock lock = new ReentrantLock();

    /**
     * 定时任务
     */
    private ScheduledExecutorService scheduledExecutorService;

    public SlidingWindowCountRateLimiter(int threshold) {
        // 默认统计qps, 窗口大小5
        this(threshold, 5, 200, TimeUnit.MILLISECONDS);
    }

    /**
     * 统计总时间 = windowSize * windowPeriod
     */
    public SlidingWindowCountRateLimiter(int threshold, int windowSize, long windowPeriod, TimeUnit timeUnit) {
        this.threshold = threshold;
        this.windowSize = windowSize;
        this.windowPeriod = windowPeriod;
        this.timeUnit = timeUnit;
        this.counter = new AtomicInteger(0);
        this.initWindows(windowSize);
        this.startResetTask();
    }

    public boolean tryAcquire() {
        lock.lock();
        if (counter.get() >= threshold) {
            return false;
        }
        // 当前窗口计数器和总计数器同时加1，为了原子性，需要加锁
        windows[windowIndex].counter.incrementAndGet();
        counter.incrementAndGet();
        lock.unlock();
        return true;
    }

    /**
     * 初始化窗口数组
     *
     * @param windowSize
     */
    private void initWindows(Integer windowSize) {
        windows = new Window[windowSize];
        for (int i = 0; i < windowSize; i++) {
            windows[i] = new Window();
        }
    }

    private void startResetTask() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this, windowPeriod, windowPeriod, timeUnit);
    }

    @Override
    public void run() {
        // 获取当前窗口索引
        Integer curIndex = (windowIndex + 1) % windowSize;
        log.info("info_reset_task, curIndex = {}", curIndex);
        // 重置当前窗口计数器，并获取上个周期的计数器值
        int count = windows[curIndex].counter.getAndSet(0);
        windowIndex = curIndex;
        // 总计数器减去当前窗口上个周期的计数器值
        counter.addAndGet(-count);
        log.info("info_reset_task, curOldCount = {}, passCount = {}, windows = {}", count, counter, windows);
    }

    static class Window {

        private AtomicInteger counter;

        public Window() {
            this.counter = new AtomicInteger(0);
        }
    }
}
