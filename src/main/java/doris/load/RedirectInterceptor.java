package doris.load;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author xucheng.liu
 * @date 2019/4/3
 */
public class RedirectInterceptor implements Interceptor {

    /**
     * 临时重定向
     */
    public static final int HTTP_TEMP_REDIRECT = 307;

    /**
     * 永久重定向
     */
    public static final int HTTP_PERM_REDIRECT = 308;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() == HTTP_TEMP_REDIRECT || response.code() == HTTP_PERM_REDIRECT) {
            String location = response.headers().get("Location");
            if (location == null) {
                return response;
            }

            Request followUpRequest = response.request().newBuilder().url(location).build();
            response = chain.proceed(followUpRequest);
        }

        return response;
    }
}
