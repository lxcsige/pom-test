package lambda;

import java.util.function.Function;

/**
 * @author xucheng.liu
 * @date 2019/9/6
 * 接收一个参数，返回一个结果
 */
public class FunctionDemo {

    private static final Function<Integer, Integer> DOUBLE = (x) -> x * 2;

    public static void main(String[] args) {
        int res1 = modifyValue1(1, (x) -> x + 1);
        System.out.println(res1);

        int res2 = modifyValue2(1, (x) -> x + 1);
        System.out.println(res2);
    }

    private static int modifyValue1(int before, Function<Integer, Integer> function) {
        return function.compose(DOUBLE).apply(before);
    }

    private static int modifyValue2(int before, Function<Integer, Integer> function) {
        return function.andThen(DOUBLE).apply(before);
    }
}
