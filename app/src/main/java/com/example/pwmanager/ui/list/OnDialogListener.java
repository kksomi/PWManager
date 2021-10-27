package com.example.pwmanager.ui.list;

import com.example.pwmanager.model.PasswordItem;

public interface OnDialogListener {
    void onFinish(int position, PasswordItem item);
}
