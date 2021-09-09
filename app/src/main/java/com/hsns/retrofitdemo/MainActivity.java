package com.hsns.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hsns.retrofitdemo.bean.UserInfo;
import com.hsns.retrofitdemo.databinding.ActivityMainBinding;
import com.hsns.retrofitdemo.viewmodel.RetrofitViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RetrofitViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(RetrofitViewModel.class);
        listenerData();
        binding.login.setOnClickListener(this);
    }

    /**
     * 监听获取数据
     */
    private void listenerData() {
        viewModel.getUserData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                if (token != null) {
                    Toast.makeText(MainActivity.this, "Get token success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String username = binding.username.getText().toString();
                String password = binding.password.getText().toString();
                UserInfo userInfo = new UserInfo(username, password);
                viewModel.getToken(userInfo);
                break;
        }
    }
}
