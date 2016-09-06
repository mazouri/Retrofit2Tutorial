package com.mazouri.retrofit2tutorial.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangdongdong on 16/9/6.
 */
public class RestClient {
    private static GitApiInterface gitApiInterface ;
    private static String BASE_URL = "https://api.github.com/";

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    /*Request original = chain.request();

                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization", "auth-token")
                            .method(original.method(), original.body())
                            .build();

                    Response response = chain.proceed(request);

                    return response;*/
                    return chain.proceed(chain.request());
                }
            });

            OkHttpClient okClient = okBuilder.build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }

        return gitApiInterface;
    }
}
