package guava.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author xucheng.liu
 * @date 2019/7/17
 */
public class Main {

    public static void main(String[] args) {
        test();
    }

    public static boolean test() {
        boolean result = false;

        Retryer<Boolean> retry = RetryerBuilder.<Boolean>newBuilder()
                .retryIfRuntimeException()
                .retryIfResult(Boolean.FALSE::equals)
                .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new RetryLogListener())
                .build();

        Callable<Boolean> callable = new Callable<Boolean>() {
            int times = 1;

            @Override
            public Boolean call() throws Exception {
                System.out.println("retry thread: " + Thread.currentThread().getName());
                System.out.println("call times = " + times);
                times++;

                if (times == 2) {
                    throw new NullPointerException();
                } else if (times == 3) {
                    throw new Exception();
                } else if (times == 4) {
                    throw new RuntimeException();
                } else if (times == 5) {
                    return false;
                } else {
                    return false;
                }
            }
        };

        try {
            System.out.println("main thread:" + Thread.currentThread().getName());
            result = retry.call(callable);
        } catch (ExecutionException e) {
            System.out.println("an error occurred in attempt, root cause: " + e.toString());
        } catch (RetryException e) {
            System.out.println(e.toString());
        }

        return result;
    }

    static class RetryLogListener implements RetryListener {

        @Override
        public <V> void onRetry(Attempt<V> attempt) {
            // ???????????????,(??????:???????????????????????????????????????)
            System.out.print("retry time=" + attempt.getAttemptNumber());

            // ??????????????????????????????
            System.out.print(", delay=" + attempt.getDelaySinceFirstAttempt());

            // ????????????: ???????????????, ??????????????????
            System.out.print(", hasException=" + attempt.hasException());
            System.out.print(", hasResult=" + attempt.hasResult());

            // ???????????????????????????
            if (attempt.hasException()) {
                System.out.print(", causeBy=" + attempt.getExceptionCause().toString());
            } else {
                // ????????????????????????
                System.out.print(", result=" + attempt.getResult());
            }

            System.out.println();
        }
    }
}
