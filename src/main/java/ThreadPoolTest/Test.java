package ThreadPoolTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xucheng.liu
 * @date 2019/7/17
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // 创建线程池
        ThreadPoolExecutor service = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        // 监控线程池执行情况的代码
        new Thread(() -> {
            while (true) {
                System.out.println();

                int queueSize = service.getQueue().size();
                System.out.println("当前排队线程数：" + queueSize);

                int activeCount = service.getActiveCount();
                System.out.println("当前活动线程数：" + activeCount);

                long completedTaskCount = service.getCompletedTaskCount();
                System.out.println("执行完成线程数：" + completedTaskCount);

                long taskCount = service.getTaskCount();
                System.out.println("总线程数：" + taskCount);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 启动的任务数量
        int counts = 1224;
        for (int i = 0; i < counts; i++) {
            TimeUnit.SECONDS.sleep(1);
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
