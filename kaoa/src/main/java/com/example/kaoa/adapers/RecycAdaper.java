package com.example.kaoa.adapers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.kaoa.DBUtil;
import com.example.kaoa.R;
import com.example.kaoa.User;
import com.example.kaoa.fragments.Food;

import java.util.ArrayList;
import java.util.List;

public class RecycAdaper extends RecyclerView.Adapter<RecycAdaper.ViewHolder> {

    List<Food.DataBean>  list  =new ArrayList<>();
    Context context;

    public RecycAdaper(Context context) {
        this.context = context;
    }

    public void initdata ( List<Food.DataBean>  lists){
        list.addAll(lists);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.recyc_item_layout, null);
        return new ViewHolder(inflate);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(list.get(i).getTitle());
        RoundedCorners roundedCorners = new RoundedCorners(20);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(list.get(i).getPic()).apply(requestOptions).into(viewHolder.imgs);



        String title = list.get(i).getTitle();
        User queryid = DBUtil.queryid(title);
        if (queryid!=null){
            viewHolder.cb.setChecked(true);
        }

        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked){
                    String title1 = list.get(i).getTitle();
                    String pic = list.get(i).getPic();
                    User user = new User();
                    user.setTitle(title1);
                    user.setPic(pic);
                    DBUtil.insert(user);


                }else {
                   String titles = list.get(i).getTitle();
                   User queryids = DBUtil.queryid(titles);


                    if (queryids!=null){
                        DBUtil.delete(queryids);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        ImageView imgs;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb= itemView.findViewById(R.id.cb);
            imgs= itemView.findViewById(R.id.imgs);
            tv= itemView.findViewById(R.id.tv);
        }
    }
}
