package com.zpy.dragonsdk.handler;

public interface ISpHandler {
    void setUsername(String username);

    String getUsrname();

    void setTelephone(String telephone);

    String getTelephone();

    void setPassword(String password);

    String getPassword();

    void setLoginTime(long loginTime);

    long getLoginTime();
}
