package klass.layout.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author xucheng.liu
 * @since 2022/6/1
 */
public class ObjectSizeTest {

    public static void main(String[] args) throws InterruptedException {
        int[] a = new int[]{1,2,3,4,5,6};
        char[] b = new char[]{'1','2','3','4','5','6'};
        Query query = new Query(123456, 123456);
        query.setC("123456_123456");
        Thread.sleep(600000);
    }
}
