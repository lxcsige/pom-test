package optional.test;

import java.util.Date;
import java.util.Optional;

/**
 * @author xucheng.liu
 * @date 2019/9/5
 */
public class Test {

    public static void main(String[] args) {
        User user = new User(1, "tony", new Date());
        Optional<User> optional = Optional.of(user);
        optional.filter(tmp -> "tony".equals(tmp.getName()))
                .ifPresent(System.out::println);
    }
}
