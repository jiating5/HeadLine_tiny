package com.jt.homemodule.mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jt.homemodule.R;
import com.jt.homemodule.mvvm.bean.TypeBean;

import java.util.List;


/**
 * @author 贾婷
 * @date 2020/2/3.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class AllAdapterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<TypeBean> data;
    Context context;

    public AllAdapterAdapter(List<TypeBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHodler(LayoutInflater.from(context).inflate(R.layout.alltype_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHodler hodler = (MyHodler) holder;
        hodler.tv_title.setText(data.get(position).getTypename());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHodler extends RecyclerView.ViewHolder{

        TextView tv_title;

        public MyHodler(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.alltype_tv_title);
        }
    }
}
