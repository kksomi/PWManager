package com.example.pwmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//저장 클래스
public class StoreUtils {
    SharedPreferences sharedPreferences;
    private static final String DELIM = "_@=DELIM@=_";

    public StoreUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE);
    }

    public void setMasterkey(String key) {
        sharedPreferences.edit().putString("masterkey", key).commit();
    }

    public String getMasterKey(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void addItem(PasswordItem item) {
        ArrayList<PasswordItem> items = getItems();
        items.add(item);
        updateItems(items);
    }

    public void readdItem(PasswordItem item1, PasswordItem item2) {
        ArrayList<PasswordItem> items = getItems();
        int index = items.indexOf(item1);
        items.set(index,item2);

    }

    public void updateItems(Collection<PasswordItem> items) {
        Set<String> set = new HashSet<>();
        for(PasswordItem item : items) {
            String name = item.getName();
            String url = item.getUrl();
            String id = item.getId();
            String password = item.getEncryptPassword();
            String memo = item.getMemo();
            String date = item.getDate();
            String result = name + DELIM + url + DELIM + id + DELIM + password + DELIM + memo + DELIM + date;
            String base64data = Base64.encodeToString(result.getBytes(), Base64.DEFAULT);
            set.add(base64data);
        }
        sharedPreferences.edit().putStringSet("passwords", set).commit();
    }

    public ArrayList<PasswordItem> getItems() {
        Set<String> set = sharedPreferences.getStringSet("passwords", new HashSet<>());
        ArrayList<PasswordItem> items = new ArrayList<>();
        for(String s : set) {
            String ds = new String(Base64.decode(s.getBytes(), Base64.DEFAULT));
            String ss[] = ds.split(DELIM);
            PasswordItem item = new PasswordItem();
            item.setName(ss[0]);
            item.setUrl(ss[1]);
            item.setId(ss[2]);
            item.setEncryptPassword(ss[3]);
            item.setMemo(ss[4]);
            item.setDate(ss[5]);
            items.add(item);
        }
        return items;
    }
}
