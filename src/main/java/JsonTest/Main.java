package JsonTest;

import com.dianping.midas.adaccount.api.dto.DayBookDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

//        String msg = "{\"msg\":{\"ID\":\"76399307\",\"accountId\":\"73722512\",\"cash\":\"500.0000\",\"redPack\":\"306.0000\",\"payType\":\"1\",\"memo\":\"\",\"addTime\":\"2019-07-18 13:06:53\",\"addUser\":\"2523234\",\"updateUser\":\"2523234\",\"updateTime\":\"2019-07-18 13:06:53\",\"userType\":\"1\",\"cashPoolType\":\"0\",\"cashPoolId\":\"73447250\",\"innerAccountId\":\"73722512\",\"hotelFreeRoomRedPack\":\"0.0000\",\"chargeBizLogID\":\"31680166\"},\"code\":0}";
//        DayBookDTO dayBook = mapper.treeToValue(mapper.readTree(msg).get("msg"), DayBookDTO.class);
//        System.out.println(dayBook.toString());
//
//        String msg2 = "{\"logDate\":\"2019-07-18\",\"hourId\":19,\"accountId\":126175,\"isRenew\":1,\"buType\":25,\"cash\":2601.97940000,\"charge\":2601.97940000,\"availableBudget\":2950.000000}";
//        JsonNode jsonNode = mapper.readTree(msg2);
//        String logDate = jsonNode.get("logDate").textValue();
//        int accountId = jsonNode.get("accountId").intValue();
//        int isRenew = jsonNode.get("isRenew").intValue();
//        BigDecimal cash = jsonNode.get("cash").decimalValue();
//        BigDecimal charge = jsonNode.get("charge").decimalValue();
//        BigDecimal availableBudget = jsonNode.get("availableBudget").decimalValue();

        String msg3 = "{\"id\":1,\"birthday\":\"\"}";
        User user = mapper.readValue(msg3, User.class);
        System.out.println(user.toString());
    }
}
