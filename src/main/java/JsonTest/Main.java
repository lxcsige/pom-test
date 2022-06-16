package JsonTest;

import com.dianping.midas.adaccount.api.dto.DayBookDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @author xucheng.liu
 * @date 2019/4/12
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        String msg3 = "{\"id\":1,\"birthday\":\"\"}";
        User user = mapper.readValue(msg3, User.class);
        System.out.println(user.toString());


        List<String> ids = Lists.newArrayList();
        for (int i = 0; i < 200; i++) {
            ids.add(i + "");
        }

        System.out.println(mapper.writeValueAsString(ids));
    }
}
