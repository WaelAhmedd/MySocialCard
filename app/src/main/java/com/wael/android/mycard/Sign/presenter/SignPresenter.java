package com.wael.android.mycard.Sign.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.wael.android.mycard.Account;
import com.wael.android.mycard.Sign.Check_internet;
import com.wael.android.mycard.Sign.SignContract;


public class SignPresenter implements SignContract.presenter {
    private SignContract.view mView;
    private Context mContext;
    private static FirebaseAuth firebaseAuth;
    Check_internet check_internet;


    private FirebaseAuth.AuthStateListener mAuthListener= new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser currentUser=firebaseAuth.getCurrentUser();
            if(currentUser!=null)

            {   mView.showHomeScreen();

            }
            else
            {
                mView.showMessageFaildCreateMail();
            }
        }
    };

    public SignPresenter(Context context)
    {

        mContext=context;
        mView= (SignContract.view) mContext;
        firebaseAuth=FirebaseAuth.getInstance();



        }
    @Override
    public void createEmail(Account account) {

        firebaseAuth.createUserWithEmailAndPassword(account.getEmail(),account.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    mView.showCreateCard();
                }
                else {
                  mView.showMessageFaildCreateMail();

                }
            }
        });

    }

    @Override
    public void loginWithEmail(String email,String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity)mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mView.showHomeScreen();

                           // updateUI(user);
                        } else {

                            mView.showMessageFaildCreateMail();

                        }


                    }
                });

           }

    @Override
    public void checkNetwork() {


    }

    @Override
    public void setAuthListener() {
firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void removeAuthListener() {
        firebaseAuth.removeAuthStateListener(mAuthListener);

    }



    @Override
    public void logInWithGoogle(GoogleSignInAccount googleAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(googleAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                           // updateUI(user);
                            Toast.makeText(mContext, "Authentication not failed.",
                                    Toast.LENGTH_SHORT).show();
                            mView.showHomeScreen();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            mView.showMessageFaildCreateMail();
                        }

                        // ...
                    }
                });
    }


}





