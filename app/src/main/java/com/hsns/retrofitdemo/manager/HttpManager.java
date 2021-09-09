package com.hsns.retrofitdemo.manager;
import com.hsns.retrofitdemo.bean.UserInfo;
import com.hsns.retrofitdemo.constant.RetrofitConstants;
import com.hsns.retrofitdemo.network.ApiService;
import com.hsns.retrofitdemo.viewmodel.IViewModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HttpManager {
    /**
     * 单例模式
     **/
    private static volatile HttpManager mHttpManager = null;

    private Retrofit retrofit;

    private ApiService service;


    /**
     * 构造函数私有化
     **/
    private HttpManager() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(RetrofitConstants.BASE_URL)
                .build();
    }

    /**
     * 公有的静态函数，对外暴露获取单例对象的接口
     **/
    public static HttpManager getInstance() {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager();
                }
            }
        }
        return mHttpManager;
    }


    public void requestUserInfo(final UserInfo info, final IViewModel.UserInfoCallback callback) {
        ExecutorsThreadManager.getInstance().submitThread(new Runnable() {
            @Override
            public void run() {
                service = retrofit.create(ApiService.class);
                service.login(info.getUsername(), info.getPassword())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.io())
                        .doOnNext(new Action1<String>() {
                            @Override
                            public void call(String token) {
                                if (callback != null) {
                                    callback.onTokenCallback(token);
                                }
                            }
                        });
            }
        });
    }


}
