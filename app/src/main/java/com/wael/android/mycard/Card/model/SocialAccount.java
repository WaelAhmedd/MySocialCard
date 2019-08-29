package com.wael.android.mycard.Card.model;


public class SocialAccount {
    String name;
    String accountUrl;
    Integer buttonBackground;

    public String getName() {
        return name;
    }

    public SocialAccount(String name, String accountUrl, Integer buttonBackground) {
        this.name = name;
        this.accountUrl = accountUrl;
        this.buttonBackground = buttonBackground;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public int getButtonBackground() {
        return buttonBackground;
    }

    public void setButtonBackground(Integer buttonBackground) {
        this.buttonBackground = buttonBackground;
    }
}
