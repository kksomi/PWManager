package com.example.pwmanager.model;

//비밀번호 정보 클래스
public class PasswordItem {

    public PasswordItem() {
        this.pwId++;
    }

    private int pwId=0;
    private String name;
    private String url;
    private String id;
    private String encryptPassword;
    private String push;
    private boolean push_on_off; //push의 On,Off
    private int month; //몇 개월 후
    private int year; //몇 년 후
    private String memo;
    private String date; //날짜

    public int getPwId() {
        return pwId;
    }

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

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public boolean getPushOnOff() {
        return push_on_off;
    }

    public void setPushOnOff(boolean push_on_off) {
        this.push_on_off = push_on_off;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year){
        this.year = year;
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

    // 업데이트 시 name을 통해 passworditem검색 필요
//    @Override public boolean equals(Object obj) {
//        PasswordItem other = (PasswordItem)obj;
//        if (other == null) return false;
//
//        boolean result = name != null && name.length() > 0;
//        result = result && name.equals(other.name);
//        return result;
//    }


    @Override
    public String toString() {
        return "PasswordItem{" +
                "pwId'" + pwId + '\'' +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", encryptPassword='" + encryptPassword + '\'' +
                ", push='" + push + '\'' +
                ", memo='" + memo + '\'' +
                ", Date='" + date + '\'' +
                ", pushOnOff='" + push_on_off + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    //형 변환
    public void setPushOnOff(String pushOnOff) {
        this.push_on_off = Boolean.parseBoolean(pushOnOff);
    }

    public void setMonth(String month) {
        this.month = Integer.parseInt(month);
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }
}
