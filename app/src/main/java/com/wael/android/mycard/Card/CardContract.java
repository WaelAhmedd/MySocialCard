package com.wael.android.mycard.Card;

import android.net.Uri;

import com.wael.android.mycard.Card.model.Card;

public interface CardContract {

interface view{

    void showMainActivity();
    void showFaildMessage();
    void showCard(Card card);
   void createSocialAccounts(Card card);
   void setProgressInvisible();

}

interface presenter{
    Card  createCard(Card card);
    void addToPreference(Card card);
    Card getCard();
    boolean checkPreference();
    void getFromPreference();
    Card getCardToEdit();
    void editCard(Card card);
    void uploadImage(Uri filepath);
    void retrivePhoto(String userId);


}
}

