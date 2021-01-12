package lambda;

import java.util.function.Predicate;

/**
 * @author xucheng.liu
 * @date 2019/9/6
 */
public class PredicateDemo {

    public static void main(String[] args) {
        boolean res = test(3, (x) -> x % 3 == 0);
        System.out.println(res);
    }

    private static boolean test(int value, Predicate<Integer> predicate) {
        return predicate.negate().test(value);
    }
}
