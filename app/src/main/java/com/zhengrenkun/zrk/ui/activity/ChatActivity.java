package com.zhengrenkun.zrk.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zhengrenkun.chatview.ChatView;
import com.zhengrenkun.chatview.models.ChatMessage;
import com.zhengrenkun.zrk.MyApplication;
import com.zhengrenkun.zrk.R;
import com.zhengrenkun.zrk.chat.ChatServerManage;
import com.zhengrenkun.zrk.chat.model.NewMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.socket.client.Socket;

public class ChatActivity extends AppCompatActivity {
    private ChatView chatView;
    private ChatServerManage mChatServerManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        EventBus.getDefault().register(this);
        chatView = (ChatView) findViewById(R.id.chat_view);
        mChatServerManage= ChatServerManage.getChatServerManage();

        chatView.setChatListener(new ChatView.ChatListener() {
            @Override
            public void userIsTyping() {

            }

            @Override
            public void userHasStoppedTyping() {

            }

            @Override
            public void onMessageReceived(String message, long timestamp) {

            }

            @Override
            public boolean sendMessage(String message, long timestamp) {

                mChatServerManage.emit("message",message);
                return true;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NewMsg msg) {
        Log.d("harvic", msg.msg);
        chatView.newMessage(new ChatMessage(msg.msg,1, ChatMessage.Type.RECEIVED));
        Toast.makeText(this, msg.msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
