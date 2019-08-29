package com.wael.android.mycard.Sign;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.wael.android.mycard.Account;
import com.wael.android.mycard.Card.model.Card;

public interface SignContract {
    interface view{

        void showCreateCard();
        void showHomeScreen();
        void showMessageFaildCreateMail();

        //google Sign In

    }
    interface presenter{
        void createEmail(Account account);
        void loginWithEmail(String email ,String password);

        void checkNetwork();
        void setAuthListener();
        void removeAuthListener();


        void logInWithGoogle(GoogleSignInAccount googleAccount);


    }
}
