package com.zhengrenkun.zrk.chat.model;

/**
 * Created by RK on 2016/8/6.
 */

public class LeaveMsg {
    public String email;
    public String openid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenid() {
        return openid;
    }
    @Override
    public String toString() {
        String a="{\"email\":\"%s\",\"openid\":\"%s\"}";
        return String.format(a,this.email,this.openid);
    }
}
