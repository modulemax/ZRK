package com.zhengrenkun.zrk.chat;

import android.util.Log;

import com.gc.materialdesign.utils.Utils;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.widgets.SnackBar;
import com.zhengrenkun.zrk.chat.model.ChatSocketEvent;
import com.zhengrenkun.zrk.chat.model.JoinMsg;
import com.zhengrenkun.zrk.chat.model.LeaveMsg;
import com.zhengrenkun.zrk.chat.model.NewMsg;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by RK on 2016/8/1.
 */

public class ChatServerManage {
    private static ChatServerManage mChatServerManage;
    private Socket mSocket;
    public static ChatServerManage getChatServerManage() {
        if(mChatServerManage==null){
            mChatServerManage=new ChatServerManage();
        }
        return mChatServerManage;
    }
    private ChatServerManage(){
        IO.Options options=new IO.Options();
        options.reconnectionDelay=4000;//重连间隔
        options.reconnectionAttempts=3;//重连次数
        try {
            String serverApi = "http://movielife.top/";
            mSocket = IO.socket(serverApi,options);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public void connect(){
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on(ChatSocketEvent.newMessage,newMessage);
        mSocket.connect();
    }
    public void disConnect(){
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT,onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off(ChatSocketEvent.newMessage,newMessage);

    }
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("socket", "connected");
            EventBus.getDefault().post(new SocketEvent("onConnect"));
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("socket", args[0].toString());
            EventBus.getDefault().post(new SocketEvent("onDisconnect"));
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("socket", args[0].toString());
            EventBus.getDefault().post(new SocketEvent("onConnectError"));
        }
    };
    private Emitter.Listener newMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject s=(JSONObject) args[0];
            Log.d("msg",s.toString());
            EventBus.getDefault().post(new NewMsg(s.toString()));
        }
    };

    public void emit(String event, Object[] args , Ack ack){
        mSocket.emit(event,args,ack);
    }
    public void emit(String event, Object[] args){
        mSocket.emit(event,args);
    }
    public void emit(String event, String message){
        mSocket.emit(event,message);
    }

    public void emitJoin(JoinMsg joinMsg ,Ack ack){
        Log.e("a",joinMsg.toString());
        mSocket.emit(ChatSocketEvent.join,joinMsg.toString(),ack);
    }
    public void emitLeave(LeaveMsg leaveMsg,Ack ack){
        mSocket.emit(ChatSocketEvent.leave,leaveMsg.toString(),ack);
    }
    public void emitNewMessage(String message){
        mSocket.emit(ChatSocketEvent.newMessage,message);
    }

}
