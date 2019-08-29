package com.wael.android.mycard.Card.model;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wael.android.mycard.R;

import java.io.Serializable;
import java.util.ArrayList;

public class   Card extends BaseObservable implements Serializable
               {

    private String name;


    private String userName;
    private String number;
    private String email;
    private String address;
    private String position;
    private ArrayList<SocialAccount>socialAccounts;
    private String facebookURL;
    private String twitterURL;
    private String instaURl;
    private String linkedInURL;
    private String snapChat;
    private String photoUrl;



    private String userID;


                   public Card() {
                       socialAccounts=new ArrayList<>();
                   }
                   @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Bindable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }




    @Bindable
   public String getFacebookURL() {
       return facebookURL;
   }

   public void setFacebookURL(String facebookURL) {
       this.facebookURL = facebookURL;
   }
   @Bindable
   public String getTwitterURL() {
       return twitterURL;
   }

   public void setTwitterURL(String twitterURL) {
       this.twitterURL = twitterURL;
   }
   @Bindable
   public String getInstaURl() {
       return instaURl;
   }

   public void setInstaURl(String instaURl) {
       this.instaURl = instaURl;
   }
   @Bindable
   public String getLinkedInURL() {
       return linkedInURL;
   }

   public void setLinkedInURL(String linkedInURL) {
       this.linkedInURL = linkedInURL;
   }
   @Bindable
    public String getSnapChat() {
       return snapChat;
    }

    public void setSnapChat(String snapChat) {
       this.snapChat = snapChat;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
                   public String getUserName() {
                       return userName;
                   }

                   public void setUserName(String userName) {
                       this.userName = userName;
                   }



                   public void setSocialAccounts() {



                       if(facebookURL!=null) {


                          socialAccounts.add(new SocialAccount("Facebook",facebookURL,R.drawable.facebook_icon));

                       }
                       if(twitterURL!=null) {


                           socialAccounts.add(new SocialAccount("Twitter",twitterURL,R.drawable.twitter));
                       }
                       if(instaURl!=null)
                       {
                           socialAccounts.add(new SocialAccount("Instagram",instaURl,R.drawable.instagram));
                        }
                       if(linkedInURL!=null)
                       {
                           socialAccounts.add(new SocialAccount("LinkedIn",linkedInURL,R.drawable.linkedin));
                       }

                       if(snapChat!=null)
                       {
                           socialAccounts.add(new SocialAccount("SnapChat",snapChat,R.drawable.snapchat));
                       }


                   }
                   public ArrayList<SocialAccount> getSocialAccounts() {
                       return socialAccounts;
                   }

                   public String getPhotoUrl() {
                       return photoUrl;
                   }

                   public void setPhotoUrl(String photoUrl) {
                       this.photoUrl = photoUrl;
                   }

               }
