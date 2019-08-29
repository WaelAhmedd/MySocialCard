package com.wael.android.mycard;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Account extends BaseObservable {

    private String email;
    private String password;
    private String id;
    private String fullName;
    private String userName;


    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);

    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(com.wael.android.mycard.BR.password);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Bindable
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        notifyPropertyChanged(BR.fullName);
    }


    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }
}