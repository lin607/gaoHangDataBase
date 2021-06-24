package com.example.remotelogin.Login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

public class FindUserViewModel extends AndroidViewModel implements FindNormalUser.CallBacks,
        FindAdminUser.CallBacks {

    Context context;

    public MutableLiveData<NormalUser> viewModelGetNormalUser() {
        return normalUser;
    }

    public MutableLiveData<AdminUser> viewModelGetAdminUser() {
        return adminUser;
    }

    MutableLiveData<NormalUser> normalUser = new MutableLiveData<>();
    MutableLiveData<AdminUser> adminUser = new MutableLiveData<>();

    public FindUserViewModel(@NonNull @NotNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void loadUser(String id) {
        FindAdminUser findAdminUser = new FindAdminUser(id, context);
        findAdminUser.setCallBacks(FindUserViewModel.this::getAdmin);
        findAdminUser.execute();
        FindNormalUser findNormalUser = new FindNormalUser(id, context);
        findNormalUser.setCallBacks(FindUserViewModel.this::getNormal);
        findNormalUser.execute();
    }

    @Override
    public void getAdmin(AdminUser adminUser) {
        this.adminUser.setValue(adminUser);
    }

    @Override
    public void getNormal(NormalUser normalUser) {
        this.normalUser.setValue(normalUser);
    }
}
