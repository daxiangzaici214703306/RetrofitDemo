package com.hsns.retrofitdemo.viewmodel;

import com.hsns.retrofitdemo.bean.UserInfo;

public interface IViewModel {

    /**
     * 得到秘钥
     * @param info 用户信息
     */
    void getToken(UserInfo info);

    interface UserInfoCallback {
        /**
         * 用户信息回调
         * @param token 秘钥
         */
        void onTokenCallback(String token);
    }
}
