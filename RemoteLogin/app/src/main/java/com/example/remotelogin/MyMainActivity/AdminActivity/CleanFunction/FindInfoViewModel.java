package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.FindAllCleanInfo;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.FindAllStaff;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FindInfoViewModel extends ViewModel implements FindAllStaff.CallBacks,
        FindAllCleanInfo.CallBacks {
    public MutableLiveData<List<CleanStaffInfo>> staffResult;
    public MutableLiveData<List<CleanForms>> formsResult;
    public Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setStaffResult(MutableLiveData<List<CleanStaffInfo>> staffResult) {
        this.staffResult = staffResult;
    }

    public MutableLiveData<List<CleanForms>> getFormsResult() {
        if (formsResult == null) {
            formsResult = new MutableLiveData<>();
        }
        return formsResult;
    }

    public void setFormsResult(MutableLiveData<List<CleanForms>> formsResult) {
        this.formsResult = formsResult;
    }

    public MutableLiveData<List<CleanStaffInfo>> getStaffResult() {
        if (staffResult == null) {
            staffResult = new MutableLiveData<>();
        }
        return staffResult;
    }

    public void FindStaff() {
        FindAllStaff findAllStaff = new FindAllStaff(context);
        findAllStaff.setCallBacks(FindInfoViewModel.this::getAllCleanStaff);
        findAllStaff.execute();
    }

    public void FindForms() {
        FindAllCleanInfo findAllCleanInfo = new FindAllCleanInfo(context);
        findAllCleanInfo.setCallBacks(FindInfoViewModel.this::getAllCleanInfo);
        findAllCleanInfo.execute();
    }

    @Override
    public void getAllCleanInfo(List<CleanForms> cleanFormsList) {
        this.formsResult.setValue(cleanFormsList);
    }

    @Override
    public void getAllCleanStaff(List<CleanStaffInfo> adminUserList) {
        this.staffResult.setValue(adminUserList);
    }
}
