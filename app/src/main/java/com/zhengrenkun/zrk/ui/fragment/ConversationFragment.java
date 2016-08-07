package com.zhengrenkun.zrk.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhengrenkun.zrk.MyApplication;
import com.zhengrenkun.zrk.R;
import com.zhengrenkun.zrk.chat.ChatServerManage;
import com.zhengrenkun.zrk.chat.model.Customer;
import com.zhengrenkun.zrk.ui.adapter.ConversationAdapter;

import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends BaseFragment {
    private ConversationAdapter mConversationAdapter;
    private ArrayList<Customer> mCustomers;

    public ConversationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_conversation, container, false);
        initData();
        initView(view);
        return view;
    }
    private void initData(){
        mCustomers=new ArrayList<>();
        for (int i=0;i<20;i++){
            mCustomers.add(new Customer("","renkun","90:90","你说"));
        }
    }

    private void initView(View view){
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mConversationAdapter=new ConversationAdapter(getActivity(),mCustomers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);

        layoutManager.setOrientation(OrientationHelper. VERTICAL);

        mRecyclerView.setAdapter( mConversationAdapter);
        //设置分隔线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity()
                , LinearLayoutManager.HORIZONTAL
                , 1,
                Color.rgb(205,205,205))
        );
    }
}
