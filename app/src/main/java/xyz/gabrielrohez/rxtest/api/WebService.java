package xyz.gabrielrohez.rxtest.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private Retrofit retrofit;
    private static WebService instance;
    private OkHttpClient.Builder httpClientBuilder;
    private HttpLoggingInterceptor loggingInterceptor;
    private static final String BASE_URL = "https://api.github.com/";

    private WebService(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized WebService getInstance(){
        if (instance == null)
            instance = new WebService();

        return instance;
    }

    public WebServiceApi createService(){
        return retrofit.create(WebServiceApi.class);
    }
}
