package Snowflake;

/**
 * User: lxcfour
 * Date: 2018/4/22
 * Time: 下午7:44
 */
public class Test {

    public static void main(String[] args) {
        long i = 7;
        for (int j = 8; j < 13; j++) {
            System.out.println(j & i);
        }

        System.out.println(i);
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 << 12));
    }
}
