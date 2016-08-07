package com.zhengrenkun.zrk.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhengrenkun.zrk.ChatService;
import com.zhengrenkun.zrk.R;
import com.zhengrenkun.zrk.ui.adapter.MainFragmentAdapter;
import com.zhengrenkun.zrk.ui.fragment.ConversationFragment;
import com.zhengrenkun.zrk.ui.fragment.FeedFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationBar bottomNavigationBar;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        initBottomNavigationBar();
        initViewPager();
        initChatService();
    }
    private void initChatService(){
        startService(new Intent(this, ChatService.class));
    }
    private void initBottomNavigationBar(){
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setAutoHideEnabled(false);
        bottomNavigationBar
                .setActiveColor(R.color.BarActiveColor)
                .setInActiveColor(R.color.BarInActiveColor)
                .setBarBackgroundColor(R.color.BarBackgroundColor);
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position,false);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.huihua, "会话"))
                .addItem(new BottomNavigationItem(R.mipmap.tongxunlu, "通讯录"))
                .addItem(new BottomNavigationItem(R.mipmap.faxian, "发现"))
                .addItem(new BottomNavigationItem(R.mipmap.wo, "我"))
                .initialise();
    }
    private void initViewPager(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ConversationFragment());
        fragments.add(new FeedFragment());
        fragments.add(new FeedFragment());
        fragments.add(new FeedFragment());
        MainFragmentAdapter fragmentAdapter=new MainFragmentAdapter(getSupportFragmentManager(),fragments,MainActivity.this);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
