package optional.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author xucheng.liu
 * @date 2019/9/5
 */

@Getter
@Setter
@ToString
public class User {

    private int id;

    private String name;

    private Date birthday;

    public User() {

    }

    public User(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
}
