package anonymous;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xucheng.liu
 * @date 2019/9/6
 */

@Getter
@Setter
public abstract class Person {

    private int id;

    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public abstract double getHeight();

    public void sayHello() {
        System.out.println("hello");
    }
}
