package klass.layout.test;

import lombok.Data;

import java.util.Date;

/**
 * @author xucheng.liu
 * @date 2020/9/22
 */
@Data
public class User {

    private int id;

    private String name;

    private Date birthday;

    private double weight;

    private double height;
}
