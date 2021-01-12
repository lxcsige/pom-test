package lambda;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author xucheng.liu
 * @date 2019/9/9
 */
@Getter
@Setter
@ToString
public class Person {

    private int id;

    private String name;

    private Date birthday;

    public Person() {

    }

    public Person(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
}
