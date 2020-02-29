package com.bawei.chinesenewyearhomwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.chinesenewyearhomwork.R;
import com.bawei.chinesenewyearhomwork.bean.LinkLabelBean;

import java.util.List;

/**
 * @author 鸿瑶
 * @date 2020/1/17.
 * GitHub：https://github.com/huosiyuan11
 * email：362877155@qq.com
 * description：
 */
public class LinkLabelAdapter extends RecyclerView.Adapter{
    private Context context;
    private final int TITLE = 99;
    private final int CONTENT = 100;
    private int Zeng = 0;
    private List<LinkLabelBean> list;

    public LinkLabelAdapter(Context context, List<LinkLabelBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new LabelHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_content, null));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((LabelHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class LabelHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private final LinearLayout llContent;

        public LabelHolder(View inflate) {
            super(inflate);
            textView = (TextView) inflate.findViewById(R.id.textViewContent);
            llContent = (LinearLayout) inflate.findViewById(R.id.llContent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 50;
            llContent.setLayoutParams(layoutParams);
        }

        public void setData(final int position) {
            textView.setText(list.get(position).getStr());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).isSelect()) {
                        Zeng--;
                    }
                    if (Zeng < 4) {
                        if (textView.isSelected()) {
                            textView.setSelected(false);
                            list.get(position).setSelect(false);
                        } else {
                            ++Zeng;
                            textView.setSelected(true);
                            list.get(position).setSelect(true);
                        }
                    } else {
                        Toast.makeText(context, "最多选择四个", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
