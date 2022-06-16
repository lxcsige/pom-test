package rateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xucheng.liu
 * @since 2022/3/1
 */
public class GuavaRateLimiterTest {

//    private static final RateLimiter rateLimiter = RateLimiter.create(5);

    private static final RateLimiter rateLimiter = RateLimiter.create(10, 5, TimeUnit.SECONDS);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 50; i++) {
            executor.submit(() -> {
                long begin = System.currentTimeMillis();
                System.out.println(rateLimiter.tryAcquire(1, 5, TimeUnit.SECONDS));
                System.out.println((System.currentTimeMillis() - begin) / 1000.0);
            });
        }
    }
}
