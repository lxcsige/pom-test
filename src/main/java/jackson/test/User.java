package jackson.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xucheng.liu
 * @date 2019/7/25
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 3845059419777772585L;

    private int id;

    private int age;

    private String name;
}
