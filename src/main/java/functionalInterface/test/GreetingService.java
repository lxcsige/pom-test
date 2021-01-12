package functionalInterface.test;

/**
 * @author xucheng.liu
 * @date 2019/9/5
 */
@FunctionalInterface
public interface GreetingService {

    void sayHello();

    default void doSomething1() {

    }

    static void printHello() {
        System.out.println("Hello");
    }

    @Override
    boolean equals(Object obj);
}
