package com.wael.android.mycard.Card.presenter;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.wael.android.mycard.Card.CardContract;
import com.wael.android.mycard.Card.View.CreateCardFragment;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Card.model.SocialAccount;
import com.wael.android.mycard.Main.View.MainActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CardPresenter implements CardContract.presenter {


    private Context mContext;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private CardContract.view mView;
    private FirebaseUser user;
    private String userID;
    private Card currentCard;
    private SharedPreferences sharedPreference;
    //keys
    private static final String MyPREFERENCES = "MyPrefs";
    private static final String Name_K="Name";
    private static final String Number_K="Number";
    private static final String Email_K="Email";
    private static final String Address_K="Address";
    private static final String Position_K="Position";

    private static final String FacebookURL_K="FaceURL";
    private static final String TwitterURL_K="TwitterURL";
    private static final String InstaURl_K="InstaURL";
    private static final String LinkedInURL_K="LinkedURL";

    private static final String SnapChat_K="SnapChat";
    private static final String user_nameK="User_Name";
    private static final String photo_k="User_photo";
    private static final String userId_K="User_ID";
    FirebaseStorage storage;
    StorageReference storageReference;
    private StorageTask mUploadTask;
    String imageUrl;
    boolean res =false;

    public  CardPresenter (Context context) {
              mContext=context;
              firebaseDatabase=FirebaseDatabase.getInstance();
              firebaseAuth=FirebaseAuth.getInstance();
              user=firebaseAuth.getCurrentUser();
              userID=user.getUid();
              reference=firebaseDatabase.getReference();
              mView= (CardContract.view) mContext;
            currentCard=new Card();
              sharedPreference=mContext.getSharedPreferences(MyPREFERENCES,mContext.MODE_PRIVATE);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    @Override
    public Card createCard(Card card) {
        FirebaseUser user=firebaseAuth.getCurrentUser();

        card.setPhotoUrl(imageUrl);
        card.setUserID(user.getUid());
            reference.child("Cards").child(user.getUid()).setValue(card);

            card.setSocialAccounts();

            mView.showMainActivity();
            return card;


    }



    @Override
    public void addToPreference(Card card) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.clear();
        editor.putString(Name_K,card.getName());
        editor.putString(Email_K,card.getEmail());
        editor.putString(Number_K,card.getNumber());
        editor.putString(Position_K,card.getPosition());
        editor.putString(LinkedInURL_K,card.getLinkedInURL());
        editor.putString(InstaURl_K,card.getInstaURl());
        editor.putString(Address_K,card.getAddress());
        editor.putString(FacebookURL_K,card.getFacebookURL());
        editor.putString(TwitterURL_K,card.getTwitterURL());
        editor.putString(SnapChat_K,card.getSnapChat());
        editor.putString(userId_K,card.getUserID());
        editor.putString(user_nameK,card.getUserName());

        editor.putString(photo_k,card.getPhotoUrl());
        editor.apply();

    }

    @Override
    public Card getCard() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
                mView.showCard(currentCard);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return currentCard;

    }

    @Override
    public boolean checkPreference( ) {
     String userIdInPreference= sharedPreference.getString(userId_K,null);
        if(userID.equals(userIdInPreference))
        {
            return true;
        }
    return false;
    }

    @Override
    public void getFromPreference() {
        Card cardInPreference=new Card();
        cardInPreference.setAddress(sharedPreference.getString(Address_K,null));
        cardInPreference.setNumber(sharedPreference.getString(Number_K,null));
        cardInPreference.setName(sharedPreference.getString(Name_K,null));
        cardInPreference.setEmail(sharedPreference.getString(Email_K,null));
        cardInPreference.setFacebookURL(sharedPreference.getString(FacebookURL_K,null));
        cardInPreference.setTwitterURL(sharedPreference.getString(TwitterURL_K,null));
        cardInPreference.setInstaURl(sharedPreference.getString(InstaURl_K,null));
        cardInPreference.setLinkedInURL(sharedPreference.getString(LinkedInURL_K,null));
        cardInPreference.setSnapChat(sharedPreference.getString(SnapChat_K,null));
        cardInPreference.setPosition(sharedPreference.getString(Position_K,null));
        cardInPreference.setUserID(sharedPreference.getString(userId_K,null));
        cardInPreference.setUserName(sharedPreference.getString(user_nameK,null));
        cardInPreference.setPhotoUrl(sharedPreference.getString(photo_k,null));
        mView.showCard(cardInPreference);

    }

    @Override
    public Card getCardToEdit() {
        Card cardInPreference=new Card();
        cardInPreference.setAddress(sharedPreference.getString(Address_K,null));
        cardInPreference.setNumber(sharedPreference.getString(Number_K,null));
        cardInPreference.setName(sharedPreference.getString(Name_K,null));
        cardInPreference.setEmail(sharedPreference.getString(Email_K,null));
        cardInPreference.setFacebookURL(sharedPreference.getString(FacebookURL_K,null));
        cardInPreference.setTwitterURL(sharedPreference.getString(TwitterURL_K,null));
        cardInPreference.setInstaURl(sharedPreference.getString(InstaURl_K,null));
        cardInPreference.setSnapChat(sharedPreference.getString(SnapChat_K,null));
        cardInPreference.setPosition(sharedPreference.getString(Position_K,null));
        cardInPreference.setUserID(sharedPreference.getString(userId_K,null));
        return  cardInPreference;
    }

    @Override
    public void editCard(final Card card) {
        reference=firebaseDatabase.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reference.child("Cards").child(user.getUid()).setValue(card);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       mView.showMainActivity();
    }

    @Override
    public void uploadImage(Uri filePath) {

        if(filePath != null)
        {

            storageReference = storageReference.child("images/"+ userID);
            storageReference.putFile(filePath)
               .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      if (taskSnapshot.getMetadata() != null) {
                           if (taskSnapshot.getMetadata().getReference() != null) {
                               Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                               result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                       imageUrl=uri.toString();
                                       CreateCardFragment createCardFragment=new CreateCardFragment();

                                        mView.setProgressInvisible();
                                   }
                               });

                   }
               }
                   }

               });}


    }

    @Override
    public void retrivePhoto(String userId) {

    }


    private Card getData(DataSnapshot dataSnapshot) {

    for(DataSnapshot ds:dataSnapshot.getChildren())
    {
        currentCard.setAddress(ds.child(userID).getValue(Card.class).getAddress());
        currentCard.setName(ds.child(userID).getValue(Card.class).getName());
        currentCard.setEmail(ds.child(userID).getValue(Card.class).getEmail());
        currentCard.setNumber(ds.child(userID).getValue(Card.class).getNumber());
        currentCard.setPosition(ds.child(userID).getValue(Card.class).getPosition());
        currentCard.setFacebookURL(ds.child(userID).getValue(Card.class).getFacebookURL());
        currentCard.setInstaURl(ds.child(userID).getValue(Card.class).getInstaURl());
        currentCard.setTwitterURL(ds.child(userID).getValue(Card.class).getTwitterURL());
        currentCard.setSnapChat(ds.child(userID).getValue(Card.class).getSnapChat());
        currentCard.setLinkedInURL(ds.child(userID).getValue(Card.class).getLinkedInURL());
        currentCard.setUserName(ds.child(userID).getValue(Card.class).getUserName());
        currentCard.setPhotoUrl(ds.child(userID).getValue(Card.class).getPhotoUrl());

    }

    return currentCard;

    }



}
