package serialization;

import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author xucheng.liu
 * @since 2022/3/9
 */
public class Main {

    private static final SerializerFactory SERIALIZER_FACTORY = new SerializerFactory();

    public static void main(String[] args) throws IOException {
        User user = new User(1, "test", 99);
        long begin = 0L;
        long end = 0L;

        // jdk序列化
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try(ObjectOutputStream output = new ObjectOutputStream(bo)) {
            begin = System.currentTimeMillis();
            output.writeObject(user);
            end = System.currentTimeMillis();
            output.flush();
        }
        System.out.println("JDK length: " + bo.toByteArray().length);
        System.out.println("JDK cost: " + (end - begin));

        // Hessian序列化
        ByteArrayOutputStream bo2 = new ByteArrayOutputStream();
        Hessian2Output h2o = new Hessian2Output(bo2);
        h2o.setSerializerFactory(SERIALIZER_FACTORY);
        begin = System.currentTimeMillis();
        h2o.writeObject(user);
        end = System.currentTimeMillis();
        h2o.flush();
        h2o.close();
        System.out.println("Hessian length: " + bo2.toByteArray().length);
        System.out.println("Hessian cost: " + (end - begin));
    }
}
