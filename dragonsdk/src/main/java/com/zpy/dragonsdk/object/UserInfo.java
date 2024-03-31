package com.zpy.dragonsdk.object;

import com.zpy.dragonsdk.util.HiLog;
import com.zpy.dragonsdk.util.SystemUtil;

public class UserInfo {
    private static final String TAG = "UserInfo";

    String name;

    String phone;

    String password;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    private UserInfo(Builder builder) {
        this.name = builder.name;
        this.phone = builder.phone;
        this.password = builder.password;
    }

    public static class Builder {
        String name;

        String phone;

        String password;

        public UserInfo build() {
            return new UserInfo(this);
        }

        public UserInfo.Builder setName(String name) {
            if (SystemUtil.isBlank(name)) {
                HiLog.d(TAG, "name is blank,use default name");
                name = "张三";
            }
            this.name = name;
            return this;
        }

        public UserInfo.Builder setPhone(String phone) {
            if (SystemUtil.isBlank(phone)) {
                HiLog.d(TAG, "phone is blank,use default phone");
                phone = "11111111111";
            }
            this.phone = phone;
            return this;
        }

        public UserInfo.Builder setPassword(String password) {
            if (SystemUtil.isBlank(password)) {
                HiLog.d(TAG, "password is blank,use default password");
                password = "兄弟你好香";
            }
            this.password = password;
            return this;
        }
    }
}
