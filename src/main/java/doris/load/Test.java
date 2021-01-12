package doris.load;

import okhttp3.*;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author xucheng.liu
 * @date 2019/4/3
 */
public class Test {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(new RedirectInterceptor())
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(@Nullable Route route, Response response) {
                        String credential = Credentials.basic("aurora", "IdPEsa2XRwloQFs0");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                }).build();

        File file = new File("/data/appdatas/recharge_1554976727966.csv");
        RequestBody body = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), file);
        Request request = new Request.Builder()
                .url("http://gh-data-olap-experiment-test01.corp.sankuai.com:8410/api/aurora/cpc_account_daily/_stream_load")
                .addHeader("label", "palo_load_1554344761000")
                .addHeader("column_separator", ",")
                .addHeader("columns", "account_id,user_id,org_id,available_budget,is_renew,is_online,is_reach,charge_more_than_500,charge_more_than_1000,date")
                .addHeader("Expect", "100-continue")
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
