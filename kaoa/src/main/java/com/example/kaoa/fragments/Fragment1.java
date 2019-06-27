package com.example.kaoa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kaoa.Apiservice;
import com.example.kaoa.R;
import com.example.kaoa.adapers.RecycAdaper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    private View view;
    private RecyclerView mRecyc;
    private SmartRefreshLayout mSma;
    private RecycAdaper adaper;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment1, container, false);
        initView(inflate);
        initrecuc();
        initdata();
        return inflate;
    }

    private void initdata() {
        Call<Food> getdats = new Retrofit.Builder()
                .baseUrl(Apiservice.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Apiservice.class)
                .getdats();
        getdats.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                List<Food.DataBean> data = response.body().getData();
                adaper.initdata(data);
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });


    }

    private void initrecuc() {
        mRecyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyc.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        adaper = new RecycAdaper(getActivity());
        mRecyc.setAdapter(adaper);



    }

    private void initView(View inflate) {
        mRecyc = (RecyclerView) inflate.findViewById(R.id.recyc);
        mSma = (SmartRefreshLayout) inflate.findViewById(R.id.sma);
    }
}
