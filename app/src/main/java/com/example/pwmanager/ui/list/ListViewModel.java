package com.example.pwmanager.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;
// 기존에 activity클래스 안에서 관리하던 ui데이터 -> viewmodel에서 관리
public class ListViewModel extends ViewModel {

    // 이 클래스에서 저장하고 관리한 ui 데이터 변수 생성
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