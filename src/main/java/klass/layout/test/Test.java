package klass.layout.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author xucheng.liu
 * @date 2020/9/22
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        // 必须睡眠4秒以上，否则会跳过偏向直接进入轻量锁
        Thread.sleep(5000);
        A a = new A();
        ClassLayout layout = ClassLayout.parseInstance(a);
        // 匿名偏向状态
        System.out.println(layout.toPrintable());

//        synchronized (a) {
//            System.out.println("main thread");
//        }
//        // 已偏向状态，解锁时并未将mark word中的thread id改成0
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());
//
//        Thread t1 = new Thread(() -> {
//            synchronized (a) {
//                System.out.println("thread1");
//
//                // 轻量级锁
//                System.out.println(ClassLayout.parseInstance(a).toPrintable());
//
//                a.hashCode();
//                // 重量级锁
//                System.out.println(ClassLayout.parseInstance(a).toPrintable());
//            }
//        });
//        t1.start();
//        t1.join();

        Thread t2 = new Thread(() -> {
            synchronized (a) {
                System.out.println("thread2");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        t3.start();
    }

    public static class A {
        // no fields
    }
}
