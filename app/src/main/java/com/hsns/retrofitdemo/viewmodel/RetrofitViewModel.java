package com.hsns.retrofitdemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.retrofitdemo.bean.UserInfo;
import com.hsns.retrofitdemo.model.RetrofitModel;

public class RetrofitViewModel extends ViewModel implements IViewModel,IViewModel.UserInfoCallback{
    private MutableLiveData<String> tokenData=new MutableLiveData<>();
    private RetrofitModel mRetrofitModel;

    public MutableLiveData<String> getUserData() {
        return tokenData;
    }

    public RetrofitViewModel() {
       mRetrofitModel=new RetrofitModel();
    }

    @Override
    public void getToken(UserInfo info) {
       mRetrofitModel.requestUserInfo(info,this);
    }

    @Override
    public void onTokenCallback(String token) {
       tokenData.postValue(token);
    }
}
