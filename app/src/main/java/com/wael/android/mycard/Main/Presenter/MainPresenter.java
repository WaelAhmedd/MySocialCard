package com.wael.android.mycard.Main.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Keys;
import com.wael.android.mycard.Main.MainContract;
import com.wael.android.mycard.Main.View.MainViewHolder;
import com.wael.android.mycard.R;



public class MainPresenter implements MainContract.Presenter {
Context mContext;
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
MainContract.View mView;
FirebaseAuth firebaseAuth;
FirebaseUser user;
Card cards;
Keys keys;
    private SharedPreferences sharedPreference;
//
    public MainPresenter(Context mContext) {
        this.mContext = mContext;
        mView= (MainContract.View) mContext;
        reference=FirebaseDatabase.getInstance().getReference("Cards");
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        sharedPreference=mContext.getSharedPreferences("MyPrefs",mContext.MODE_PRIVATE);
        keys=new Keys();

    }


    @Override
    public void logout() {
        firebaseAuth.signOut();
    }

    @Override
    public Card getFromPreference() {
        Card cardInPreference=new Card();
        cardInPreference.setAddress(sharedPreference.getString(keys.Address_K,null));
        cardInPreference.setNumber(sharedPreference.getString(keys.Number_K,null));
        cardInPreference.setName(sharedPreference.getString(keys.Name_K,null));
        cardInPreference.setEmail(sharedPreference.getString(keys.Email_K,null));
        cardInPreference.setFacebookURL(sharedPreference.getString(keys.FacebookURL_K,null));
        cardInPreference.setTwitterURL(sharedPreference.getString(keys.TwitterURL_K,null));
        cardInPreference.setInstaURl(sharedPreference.getString(keys.InstaURl_K,null));
        cardInPreference.setLinkedInURL(sharedPreference.getString(keys.LinkedInURL_K,null));
        cardInPreference.setSnapChat(sharedPreference.getString(keys.SnapChat_K,null));
        cardInPreference.setPosition(sharedPreference.getString(keys.Position_K,null));
        cardInPreference.setUserID(sharedPreference.getString(keys.userId_K,null));
        cardInPreference.setUserName(sharedPreference.getString(keys.user_nameK,null));
        cardInPreference.setPhotoUrl(sharedPreference.getString(keys.photo_k,null));
        return cardInPreference;
    }
}
