package serialization;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xucheng.liu
 * @since 2022/3/10
 */
@Data
public class User implements Serializable {

    private String name;

    private int id;

    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
