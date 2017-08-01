package BeanUtilsTest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

/**
 * Created by lxcfour on 2017/7/31.
 */
public class MainTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.setId(1);
        person.setName("four");
        person.setAge(26);
        person.setHeight(180);
        person.setWeight(65);

        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(person, personDTO);
        System.out.println(JSON.toJSONString(personDTO));
    }
}
