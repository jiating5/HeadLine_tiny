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
import com.jt.homemodule.mvvm.bean.NewsTypeBean;
import com.jt.homemodule.mvvm.bean.RecommendNewsBean;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/18.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class NewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<NewsTypeBean> list;

    public NewTypeAdapter(Context context, List<NewsTypeBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else{
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            return  new MyHodler1(LayoutInflater.from(context).inflate(R.layout.recommend_item1,parent,false));
        }else{
            return  new MyHodler2(LayoutInflater.from(context).inflate(R.layout.recommend_item2,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == 0){
            MyHodler1 hodler1 = (MyHodler1) holder;
            hodler1.tv_auth.setText(list.get(position).getAuth());
            hodler1.textView.setText(list.get(position).getTitle());
            hodler1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(position);
                }
            });
        }else if (getItemViewType(position) == 1){
            MyHodler2 hodler2 = (MyHodler2) holder;
            hodler2.textView.setText(list.get(position).getTitle());
            hodler2.tv_auth.setText(list.get(position).getAuth());
            Glide.with(context).load(list.get(position).getMainimgurl()).into(hodler2.imageView);
            hodler2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHodler1 extends RecyclerView.ViewHolder{

        TextView textView,tv_auth;

        public MyHodler1(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recommend1_tv);
            tv_auth = itemView.findViewById(R.id.recommend1_tv_auth);
        }
    }

    class MyHodler2 extends RecyclerView.ViewHolder{

        TextView textView,tv_auth;
        ImageView imageView;

        public MyHodler2(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recommend2_tv);
            imageView = itemView.findViewById(R.id.recommend2_iv);
            tv_auth = itemView.findViewById(R.id.recommend2_tv_auth);
        }
    }

    //声明item回调接口
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //定义
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
