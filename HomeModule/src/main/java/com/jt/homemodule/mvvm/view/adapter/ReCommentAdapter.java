package com.jt.homemodule.mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jt.homemodule.R;
import com.jt.homemodule.mvvm.bean.RecommendNewsBean;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/18.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class ReCommentRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<RecommendNewsBean> list;

    public ReCommentRecycler(Context context, List<RecommendNewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getNewstypeid() == 3){
            return 0;
        }else if (list.get(position).getNewstypeid() == 10){
            return 1;
        }else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            return  new MyHodler1(LayoutInflater.from(context).inflate(R.layout.recommend_item1,parent,false));
        }else if (viewType == 1){
            return  new MyHodler2(LayoutInflater.from(context).inflate(R.layout.recommend_item2,parent,false));
        }else {
            return  new MyHodler2(LayoutInflater.from(context).inflate(R.layout.recommend_item2,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            MyHodler1 hodler1 = (MyHodler1) holder;
            hodler1.textView.setText(list.get(position).getTitle());
        }else if (getItemViewType(position) == 1){
            MyHodler2 hodler2 = (MyHodler2) holder;
            hodler2.textView.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getSourceurl()).into(hodler2.imageView);
        }else {
            MyHodler3 hodler3 = (MyHodler3) holder;
            hodler3.textView.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getSourceurl()).into(hodler3.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHodler1 extends RecyclerView.ViewHolder{

        TextView textView;

        public MyHodler1(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recommend1_tv);
        }
    }

    class MyHodler2 extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyHodler2(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recommend2_tv);
            imageView = itemView.findViewById(R.id.recommend2_iv);
        }
    }

    class MyHodler3 extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyHodler3(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recommend3_tv);
            imageView = itemView.findViewById(R.id.recommend3_iv);
        }
    }
}
