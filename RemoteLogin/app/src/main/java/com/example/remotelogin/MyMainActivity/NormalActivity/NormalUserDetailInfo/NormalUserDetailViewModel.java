package com.example.remotelogin.MyMainActivity.NormalActivity.NormalUserDetailInfo;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

public class NormalUserDetailViewModel extends AndroidViewModel implements IDFindNormalUser.CallBacks {
    private Context context;

    private MutableLiveData<NormalUserDetail> mutableLiveData = new MutableLiveData<>();

    public NormalUserDetailViewModel(@NonNull @NotNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<NormalUserDetail> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<NormalUserDetail> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    @Override
    public void getNormalUserDetail(NormalUserDetail normalUserDetail) {
        this.mutableLiveData.setValue(normalUserDetail);
    }

    public void startFindNormalUser(String id) {
        IDFindNormalUser idFindNormalUser = new IDFindNormalUser(context);
        idFindNormalUser.setCallBacks(NormalUserDetailViewModel.this::getNormalUserDetail);
        idFindNormalUser.execute(id);
    }
}
