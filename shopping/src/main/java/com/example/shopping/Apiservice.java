package com.example.shopping;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Apiservice {

    String url = "http://fun.51fanli.com/";
    @GET("api/taohuasuan/getHotItems/?c_src=5&cids=9000&page={pages}&size=10")
    Call<bean> getdats(@Path("pages")  int pages);

}
