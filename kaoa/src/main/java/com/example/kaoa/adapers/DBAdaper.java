package com.example.kaoa.adapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kaoa.R;
import com.example.kaoa.User;


import java.util.ArrayList;
import java.util.List;

public class DBAdaper extends RecyclerView.Adapter<DBAdaper.ViewHolder> {
    int index ;
    List<User> list = new ArrayList<>();
    Context context;

    public DBAdaper(Context context) {
        this.context = context;
    }

    public  void initdata ( List<User> lists){
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.recyc_item1_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
       viewHolder.tvl.setText(list.get(i).getTitle());
       Glide.with(context).load(list.get(i).getPic()).into(viewHolder.iamges);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvl;
        ImageView iamges;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iamges = itemView.findViewById(R.id.iamges);
            tvl = itemView.findViewById(R.id.tvss);


        }
    }
}
