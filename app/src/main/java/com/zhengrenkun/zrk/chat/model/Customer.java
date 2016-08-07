package com.zhengrenkun.zrk.chat.model;

/**
 * Created by RK on 2016/8/4.
 */

public class Customer {
    public String touxiang;
    public String name;
    public String createTime;
    public String lastMsg;

    public Customer(String touxiang, String name, String createTime, String lastMsg) {
        this.touxiang = touxiang;
        this.name = name;
        this.createTime = createTime;
        this.lastMsg = lastMsg;
    }
}
