package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @author xucheng.liu
 * @date 2019/9/9
 */
public class MethodReferenceDemo {

    public static void main(String[] args) {
        Person person = new Person(1, "jackson", new Date());
        Function<Person, Integer> getId = Person::getId;
        System.out.println(getId.apply(person));

        Person person1 = new Person(2, "tony", new Date());
        Person person2 = new Person(3, "david", new Date());
        List<Person> list = Arrays.asList(person2, person, person1);
        list.sort(Comparator.comparing(Person::getId).reversed());
        System.out.println(list);

        long sum = list.stream()
                .filter(p -> !"".equals(p.getName()))
                .mapToLong(Person::getId)
                .sum();
    }
}
