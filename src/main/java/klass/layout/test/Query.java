package klass.layout.test;

/**
 * @author xucheng.liu
 * @since 2022/6/1
 */
public class Query {

    private int a;

    private int b;

    private String c;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public Query(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
