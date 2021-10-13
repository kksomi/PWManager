package com.example.pwmanager.model;

//비밀번호 정보 클래스
public class PasswordItem {
    private String name;
    private String url;
    private String id;
    private String encryptPassword;
    private boolean push;
    private String memo;
    private String date; //날짜

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PasswordItem{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", encryptPassword='" + encryptPassword + '\'' +
                ", push=" + push +
                ", memo='" + memo + '\'' +
                ", Date='" + date + '\'' +
                '}';
    }
}
