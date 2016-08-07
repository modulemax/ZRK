package com.zhengrenkun.zrk.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhengrenkun.zrk.R;
import com.zhengrenkun.zrk.ui.activity.LoginActivity;


/**
 * Created by RK on 2016/6/8.
 */
public class FeedFragment extends BaseFragment {
    public FeedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_feedfragment,  container,false);
        TextView textView= (TextView) view.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        textView.setText(""+Math.random());
        return view;
    }
}
