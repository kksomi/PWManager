package com.example.pwmanager.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    // 첫 화면이 List 화면 -> Add 화면에서 뒤로가기 누르면 List 화면
    public AddViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
