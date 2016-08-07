package com.zhengrenkun.zrk;

import android.app.Application;
import com.zhengrenkun.zrk.chat.ChatServerManage;

/**
 * Created by RK on 2016/8/1.
 */
public class MyApplication extends Application {
    private ChatServerManage mChatServerManage;

    @Override
    public void onCreate() {
        super.onCreate();
        mChatServerManage=ChatServerManage.getChatServerManage();
    }
}
