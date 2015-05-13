package com.Bloom.activity;

import android.text.Editable;

/**
 * Created by Im on 5/12/15.
 */
public class personInfo {
    private String email;
    private String pw;
    private String fullName;
    private String nickName;
    private String college;

    public String getEmail(){
        return email;
    }

    public String getPw(){
        return pw;
    }

    public String getFullName(){
        return fullName;
    }

    public String getNickName(){
        return nickName;
    }

    public String getCollege(){
        return college;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPw(String pw){
        this.pw = pw;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public void setCollege(String college){
        this.college = college;
    }


}
