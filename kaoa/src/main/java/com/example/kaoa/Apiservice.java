package com.example.kaoa;

import com.example.kaoa.fragments.Food;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiservice {

    String url = "http://www.qubaobei.com/";
    @GET("ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
    Call<Food>  getdats();

}
