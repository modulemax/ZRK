package com.zhengrenkun.zrk.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhengrenkun.zrk.R;
import com.zhengrenkun.zrk.chat.model.Customer;
import com.zhengrenkun.zrk.ui.activity.ChatActivity;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;

/**
 * Created by RK on 2016/7/31.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder>{
    private ArrayList<Customer> mCustomers;
    private Context mContext;
    private LayoutInflater inflater;
    private Socket mSocket;
    public ConversationAdapter(Context context, ArrayList<Customer> mCustomers){
        this. mContext=context;
        this. mCustomers=mCustomers;
        inflater= LayoutInflater. from(mContext);
    }

    @Override
    public int getItemCount() {

        return mCustomers.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.customerName.setText( mCustomers.get(position).name);
        holder.lastMsg.setText( mCustomers.get(position).lastMsg);
        holder.createTime.setText( mCustomers.get(position).createTime);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.conversation_list_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView touXiang;
        TextView customerName;
        TextView lastMsg;
        TextView createTime;

        public MyViewHolder(View view) {
            super(view);
            touXiang=(ImageView) view.findViewById(R.id.touXiang);
            customerName=(TextView) view.findViewById(R.id.customerName);
            lastMsg=(TextView) view.findViewById(R.id.lastMsg);
            createTime=(TextView) view.findViewById(R.id.createTime);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent bintent = new Intent(mContext, ChatActivity.class);
                    bintent.setPackage(mContext.getPackageName());
                    mContext.startActivity(bintent);
                }
            });
        }

    }
}
