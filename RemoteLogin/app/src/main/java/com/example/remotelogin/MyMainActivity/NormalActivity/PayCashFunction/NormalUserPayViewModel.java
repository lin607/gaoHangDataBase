package com.example.remotelogin.MyMainActivity.NormalActivity.PayCashFunction;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NormalUserPayViewModel extends AndroidViewModel implements
        IDFindPayCash.CallBacks, IDFindRecord.CallBacks {

    public MutableLiveData<List<PostCostElement>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(MutableLiveData<List<PostCostElement>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    MutableLiveData<List<PostCostElement>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Double> viewModelGetPayCash() {
        return payCash;
    }


    MutableLiveData<Double> payCash = new MutableLiveData<>();
    Context context;

    public NormalUserPayViewModel(@NonNull @NotNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void findPayCash(String id) {
        IDFindPayCash idFindPayCash = new IDFindPayCash(context);
        idFindPayCash.setCallBacks(NormalUserPayViewModel.this::getPayCash);
        idFindPayCash.execute(id);
    }

    public void findRecord(String id) {
        IDFindRecord idFindRecord = new IDFindRecord(context);
        idFindRecord.setCallBacks(NormalUserPayViewModel.this::getPayRecord);
        idFindRecord.execute(id);
    }

    public void findChangePayCash(String id, String cash) {
        IDChangePayCash idChangePayCash = new IDChangePayCash(context);
        idChangePayCash.execute(id, cash);
    }

    @Override
    public void getPayCash(double cash) {
        payCash.setValue(cash);
    }

    @Override
    public void getPayRecord(List<PostCostElement> postCostElements) {
        this.listMutableLiveData.setValue(postCostElements);
    }
}
