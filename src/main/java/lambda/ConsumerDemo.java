package lambda;

import java.util.function.Consumer;

/**
 * @author xucheng.liu
 * @date 2019/9/6
 * 接收一个参数，不返回任何结果
 */
public class ConsumerDemo {

    private static final Consumer<Integer> DOUBLE = (x) -> System.out.println(x * 2);

    public static void main(String[] args) {
        modifyValue1(1, (x) -> System.out.println(x * 3));
        modifyValue2(1, (x) -> System.out.println(x * 3));
    }

    private static void modifyValue1(int value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }

    private static void modifyValue2(int value, Consumer<Integer> consumer) {
        consumer.andThen(DOUBLE).accept(value);
    }
}
