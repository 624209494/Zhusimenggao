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

import com.example.kaoa.DBUtil;
import com.example.kaoa.R;
import com.example.kaoa.User;
import com.example.kaoa.adapers.DBAdaper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    private View view;
    private RecyclerView mRecycs;
    private DBAdaper adaper;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment2, container, false);
        initView(inflate);
        initrecyc();
        insitdata();
        return inflate;
    }

    private void insitdata() {

        List<User> users = DBUtil.queryAll();
        adaper.initdata(users);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            insitdata();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void initrecyc() {
        mRecycs.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycs.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        adaper = new DBAdaper(getActivity());
        mRecycs.setAdapter(adaper);


    }

    private void initView(View inflate) {
        mRecycs = (RecyclerView) inflate.findViewById(R.id.recycs);
    }
}
