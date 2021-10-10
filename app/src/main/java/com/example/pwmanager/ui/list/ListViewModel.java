package com.example.pwmanager.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;

public class ListViewModel extends ViewModel {

    private MutableLiveData<ArrayList<PasswordItem>> mPasswordList;
    private MutableLiveData<PasswordItem> mSelectItem;

    public ListViewModel() {
        mPasswordList = new MutableLiveData<>();
        mPasswordList.setValue(new ArrayList<>());
        mSelectItem = new MutableLiveData<>();
        mSelectItem.setValue(null);
    }

    public MutableLiveData<ArrayList<PasswordItem>> getPasswordList() {
        return mPasswordList;
    }

    public MutableLiveData<PasswordItem> getSelectItem() {
        return mSelectItem;
    }
}