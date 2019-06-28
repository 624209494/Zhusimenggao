package com.example.shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 朱思萌
public class RecycAdaper extends RecyclerView.Adapter<RecycAdaper.ViewHolder> {

    List<bean.DataBean.ItemsBean> list = new ArrayList<>();
    //  private Map<Integer, Boolean> checkStatus = new HashMap<>();
    private List<Boolean> booleans = new ArrayList<>();


    private List<String> lists;
    Context context;

/*    private void initData() {
        lists = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            lists.add("CheckBox" + i);
        }
        initCheck(false);
    }

    //更改集合内部存储的状态
    public void initCheck(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            //更改指定位置的数据
            checkStatus.put(i, flag);
        }
    }*/


    public RecycAdaper(Context context) {
        this.context = context;
    }

    public void initdata(List<bean.DataBean.ItemsBean> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            for (int i = 0; i < list.size(); i++) {
                booleans.add(false);
            }
            notifyDataSetChanged();
        }
    }


    public void initloadmode(List<bean.DataBean.ItemsBean> list) {
        this.list.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            booleans.add(false);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.recyc_item_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.price.setText(list.get(i).getPrice() + "");
        Glide.with(context).load(list.get(i).getImg1()).into(viewHolder.img);


        final Boolean aBoolean = booleans.get(i);
        if (aBoolean) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }

        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    booleans.set(i, false);
                } else {
                    booleans.set(i, true);
                }
                notifyDataSetChanged();

                couption();
            }
        });





      /*  //清除监听器
        viewHolder.cb.setOnCheckedChangeListener(null);
        //设置选中状态
        viewHolder.cb.setChecked(false);
        //再设置一次CheckBox的选中监听器，当CheckBox的选中状态发生改变时，把改变后的状态储存在Map中
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double price = list.get(i).getPrice();
                checkStatus.put(i, isChecked);
                //check状态一旦改变，保存的check值也要发生相应的变化
                if (isChecked){

                    maxs+=price;
                }else {

                    maxs-=price;
                }
                MainActivity.mMax.setText(""+maxs);
            }
        });*/

/*
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            double price = list.get(i).getPrice();
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    map.put(i,true);
                    maxs+=price;
                }else {
                    map.remove(i);
                    maxs-=price;
                }
                MainActivity.mMax.setText(""+maxs);
            }
        });

        if (map!=null && map.containsKey(i)){
            viewHolder.cb.setChecked(true);
        }else {
            viewHolder.cb.setChecked(false);
        }*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        ImageView img;
        TextView name;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }

    public void couption() {

        double maxs = 0;
        for (int j = 0; j < this.booleans.size(); j++) {
            if (booleans.get(j) == true) {
                maxs += list.get(j).getPrice();
            }
        }

        DecimalFormat df = new DecimalFormat("######0.00");
        MainActivity.mMax.setText("" + df.format(maxs));

    }
}
