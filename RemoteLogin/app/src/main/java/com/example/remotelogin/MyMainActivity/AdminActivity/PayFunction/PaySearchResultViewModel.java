package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.FindPayForms;
import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.PayForms;

import java.util.List;

public class PaySearchResultViewModel extends ViewModel implements FindPayForms.CallBacks {
    public MutableLiveData<List<PayForms>> payFormsMutableLiveData = new MutableLiveData<>();
    public Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<PayForms>> getPayFormsMutableLiveData() {
        return payFormsMutableLiveData;
    }

    public void setPayFormsMutableLiveData(MutableLiveData<List<PayForms>> payFormsMutableLiveData) {
        this.payFormsMutableLiveData = payFormsMutableLiveData;
    }

    public void getPayFormViewModel() {
        FindPayForms findPayForms = new FindPayForms(context);
        findPayForms.setCallBacks(PaySearchResultViewModel.this::getAllPayForms);
        findPayForms.execute();
    }

    @Override
    public void getAllPayForms(List<PayForms> payForms) {
        this.payFormsMutableLiveData.setValue(payForms);
    }
}
