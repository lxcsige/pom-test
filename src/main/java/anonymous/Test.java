package anonymous;

/**
 * @author xucheng.liu
 * @date 2019/9/6
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.test(new Person("jackson") {
            @Override
            public double getHeight() {
                return 1.81d;
            }
        });

        EchoService echoService = new EchoService() {
            @Override
            public void sayHello() {
                System.out.println("hello");
            }

            @Override
            public void sayHi() {
                System.out.println("hi");
            }
        };
    }

    public void test (Person p){
        System.out.printf("姓名为%s;%n身高为%.2f;%n", p.getName(),p.getHeight());
    }
}
