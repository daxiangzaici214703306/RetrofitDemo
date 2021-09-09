package com.hsns.retrofitdemo.model;

import com.hsns.retrofitdemo.bean.UserInfo;
import com.hsns.retrofitdemo.manager.HttpManager;
import com.hsns.retrofitdemo.viewmodel.IViewModel;

public class RetrofitModel implements IModel {
    @Override
    public void requestUserInfo(UserInfo info, IViewModel.UserInfoCallback callback) {
        HttpManager.getInstance().requestUserInfo(info,callback);
    }
}
