package com.example.lib;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String Base_Url = "http://10.0.2.2:8083";
    //private static String Base_Url = "http://ngbinhan.somee.com";
    public static String getBase_url() {
        return Base_Url;
    }

    public static void setBase_url(String base_url) {
        Base_Url = base_url;
    }

    public static Retrofit getRetrofit()
    {
        if(retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
