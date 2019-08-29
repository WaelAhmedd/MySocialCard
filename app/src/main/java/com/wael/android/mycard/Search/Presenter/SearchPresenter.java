package com.wael.android.mycard.Search.Presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Search.SearchContract;

import java.util.ArrayList;

public class SearchPresenter implements SearchContract.presenter {
private DatabaseReference databaseReference;
private FirebaseUser firebaseUser;
ArrayList<Card> cards;
Context mContext;
SearchContract.view mView;

    public SearchPresenter(Context mContext) {
        this.mContext = mContext;
        databaseReference= FirebaseDatabase.getInstance().getReference();
        mView= (SearchContract.view) mContext;
        cards=new ArrayList<Card>();
    }

    @Override
    public void setAdapter(final String searchForThis) {
        final Card selectedCard=new Card();
        cards.clear();
        databaseReference.child("Cards").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String name=snapshot.child("userName").getValue(String.class);


                    if(name.equals(searchForThis)){
                        String number=snapshot.child("number").getValue(String.class);
                        String address=snapshot.child("address").getValue(String.class);
                        String email=snapshot.child("email").getValue(String.class);
                        String position=snapshot.child("position").getValue(String.class);
                        String facebookURL=snapshot.child("facebookURL").getValue(String.class);
                        String twitterURL=snapshot.child("twitterURL").getValue(String.class);
                        String instaURl=snapshot.child("instaURl").getValue(String.class);
                        String linkedInURL=snapshot.child("linkedInURL").getValue(String.class);
                        String snapChat=snapshot.child("snapChat").getValue(String.class);
                        String fullName=snapshot.child("name").getValue(String.class);
                        String userName=snapshot.child("userName").getValue(String.class);
                        String photoUrl=snapshot.child("photoUrl").getValue(String.class);
                        selectedCard.setName(fullName);
                        selectedCard.setPosition(position);
                        selectedCard.setUserName(userName);
                        selectedCard.setNumber(number);
                        selectedCard.setEmail(email);
                        selectedCard.setAddress(address);
                        selectedCard.setFacebookURL(facebookURL);
                        selectedCard.setTwitterURL(twitterURL);
                        selectedCard.setLinkedInURL(linkedInURL);
                        selectedCard.setSnapChat(snapChat);
                        selectedCard.setInstaURl(instaURl);
                        selectedCard.setPhotoUrl(photoUrl);
                        cards.add(selectedCard);


                    }

                }
                mView.buildRecyclerView(cards);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
