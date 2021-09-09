package com.hsns.retrofitdemo.model;

import com.hsns.retrofitdemo.bean.UserInfo;
import com.hsns.retrofitdemo.viewmodel.IViewModel;

public interface IModel {
    /**
     * 请求得到用户信息
     * @param info  用户信息
     */
    void requestUserInfo(UserInfo info, IViewModel.UserInfoCallback callback);
}
