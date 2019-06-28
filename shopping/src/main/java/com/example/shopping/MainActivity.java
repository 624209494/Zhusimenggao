package com.example.shopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyc;
    public static TextView mMax;
    private RecycAdaper adaper;
    int page = 1;
    private SmartRefreshLayout mSma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initrecyc();
        initdatas();

    }

    private void initdatas() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("http://fun.51fanli.com/api/taohuasuan/getHotItems/?c_src=5&cids=9000&page="+page+"&size=10")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                bean bean = gson.fromJson(string, bean.class);
                final List<com.example.shopping.bean.DataBean.ItemsBean> items = bean.getData().getItems();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (page ==1) {
                            adaper.initdata(items);
                            mSma.finishRefresh();
                        }else {
                            adaper.initloadmode(items);
                            mSma.finishLoadMore();
                        }
                    }
                });


            }
        });

    }

    private void initrecyc() {
        mRecyc.setLayoutManager(new LinearLayoutManager(this));
        mRecyc.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        adaper = new RecycAdaper(this);
        mRecyc.setAdapter(adaper);
        mSma.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++;
                initdatas();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page =1;
                initdatas();
            }
        });

    }

    private void initView() {
        mRecyc = (RecyclerView) findViewById(R.id.recyc);
        mMax = (TextView) findViewById(R.id.max);
        mSma = (SmartRefreshLayout) findViewById(R.id.sma);
    }
}
