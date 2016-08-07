package com.zhengrenkun.zrk.chat.model;

/**
 * Created by RK on 2016/8/3.
 */

public class JoinMsg {
    public String email;
    public String openid;

    public JoinMsg(String email, String openid) {
        this.email = email;
        this.openid = openid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        String a="{\"email\":\"%s\",\"openid\":\"%s\"}";
        return String.format(a,this.email,this.openid);
    }
}
