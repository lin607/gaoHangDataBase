package com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class InfoUserViewModel extends ViewModel implements GetInfoUser.CallBacks {
    public MutableLiveData<List<InfoUser>> infoUsersLiveData;
    public Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<InfoUser>> getInfoUsersLiveData() {
        if (infoUsersLiveData == null) {
            infoUsersLiveData = new MutableLiveData<>();
        }
        return infoUsersLiveData;
    }

    public void setInfoUsersLiveData(MutableLiveData<List<InfoUser>> infoUsersLiveData) {
        this.infoUsersLiveData = infoUsersLiveData;
    }

    public void FindAllInfoUsers() {
        GetInfoUser getInfoUser = new GetInfoUser(context);
        getInfoUser.setCallBacks(InfoUserViewModel.this::getInfoUsers);
        getInfoUser.execute();
    }

    @Override
    public void getInfoUsers(List<InfoUser> adminUserList) {
        this.infoUsersLiveData.setValue(adminUserList);
    }
}
