package com.zhengrenkun.zrk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.zhengrenkun.zrk.chat.ChatServerManage;
import com.zhengrenkun.zrk.chat.SocketEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ChatService extends Service {
    private ChatServerManage mChatServerManage;
    public ChatService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mChatServerManage=ChatServerManage.getChatServerManage();
        mChatServerManage.connect();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mChatServerManage.disConnect();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SocketEvent event) {
        Toast.makeText(this, event.event, Toast.LENGTH_LONG).show();
    }
}
