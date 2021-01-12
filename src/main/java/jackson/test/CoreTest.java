package jackson.test;

import com.fasterxml.jackson.core.*;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xucheng.liu
 * @date 2019/7/9
 */
public class CoreTest {

    public static void main(String[] args) throws IOException {
        // factory instance
        JsonFactory factory = new JsonFactory();

        // write json file
        JsonGenerator generator = factory.createGenerator(new File("user.json"), JsonEncoding.UTF8);
        // pretty print
        generator.useDefaultPrettyPrinter();
        generate(generator);

        // read json file
        JsonParser parser = factory.createParser(new File("user.json"));
        User user = parse(parser);
        System.out.println(user.toString());
    }

    private static void generate(JsonGenerator generator) throws IOException {
        // "{"
        generator.writeStartObject();
        // "name": "linus"
        generator.writeStringField("name", "linus");
        // "age": 30
        generator.writeNumberField("age", 30);
        // "memo": ["memo1", "memo2"]
        generator.writeFieldName("memo");
        generator.writeStartArray();
        generator.writeString("memo1");
        generator.writeString("memo2");
        generator.writeEndArray();
        // "}"
        generator.writeEndObject();

        generator.close();
    }

    private static User parse(JsonParser parser) throws IOException {
        User user = new User();

        // START_OBJECT
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            return user;
        }

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            // continue to get value token
            parser.nextToken();
            switch (fieldName) {
                case "name":
                    user.setName(parser.getText());
                    break;
                case "age":
                    user.setAge(parser.getIntValue());
                    break;
                case "memo":
                    // traverse array
                    List<String> memo = Lists.newArrayList();
                    user.setMemo(memo);
                    while (parser.nextToken() != JsonToken.END_ARRAY) {
                        memo.add(parser.getText());
                    }
                    break;
                default:
                    break;
            }
        }

        parser.close();

        return user;
    }

    public static class User {

        private String name;

        private int age;

        private List<String> memo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getMemo() {
            return memo;
        }

        public void setMemo(List<String> memo) {
            this.memo = memo;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", memo=" + memo +
                    '}';
        }
    }
}
