package com.example.pwmanager;

public class Application extends android.app.Application {

    static private String masterKey;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getMasterKey() {
        //TODO: 여기에서 masterkey 설정을 하게 해주세요.
        masterKey = "$MY_SECRET_M@St#R_KeY";
        return masterKey;
    }

    public static void setMasterKey(String masterKey) {
        Application.masterKey = masterKey;
    }
}
