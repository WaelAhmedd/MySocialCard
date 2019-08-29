package com.wael.android.mycard.Sign.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.wael.android.mycard.Account;
import com.wael.android.mycard.Card.View.CardActivity;
import com.wael.android.mycard.Main.View.MainActivity;
import com.wael.android.mycard.R;
import com.wael.android.mycard.Search.View.SearchActivity;
import com.wael.android.mycard.Sign.SignContract;
import com.wael.android.mycard.Sign.presenter.SignPresenter;
import com.wael.android.mycard.databinding.ActivitySignBinding;


public class SignActivity extends AppCompatActivity implements SignContract.view ,SignUpFragment.OnInputListener, LogInFragment.signInInput ,GoogleApiClient.OnConnectionFailedListener{
  private   ActivitySignBinding binding;
   private SignPresenter signPresenter;



    private int SIGN_IN_REQUEST_CODE = 888;
    private FirebaseAuth mAuth;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding= DataBindingUtil.setContentView(this,R.layout.activity_sign);
      SignHandler signHandler=new SignHandler();
        binding.setSignHandler(signHandler);
    signPresenter=new SignPresenter(this);


        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    public void showHomeScreen() {

        Intent intent=new Intent(this,MainActivity.class);

        startActivity(intent);
        finish();
    }



    @Override
    public void showCreateCard() {
        Intent intent=new Intent(this, CardActivity.class);

        intent.putExtra("firstTime",userName);
        startActivity(intent);
            finish();

    }

    @Override
    public void showMessageFaildCreateMail() {
        Toast.makeText(this, "something is wrong please try again ", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void sendInput(Account input) {
        userName=input.getUserName();
        signPresenter.createEmail(input);

    }

    @Override
    public void getLogInInfo(Account input) {
        signPresenter.loginWithEmail(input.getEmail(),input.getPassword());
    }

    @Override
    protected void onStart() {
        super.onStart();
      signPresenter.setAuthListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
      signPresenter.removeAuthListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {

            if (requestCode == SIGN_IN_REQUEST_CODE) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
    }}


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){


            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                // Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                showHomeScreen();
                            } else {
                                // If sign in fails, display a message to the user.
                                showMessageFaildCreateMail();
                            }

                            // ...
                        }
                    });
        }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "connection failed", Toast.LENGTH_SHORT).show();
    }


    public class SignHandler{
        public void showCreateAccountt(View view){
            SignUpFragment signUpFragment=new SignUpFragment();
            signUpFragment.show(getSupportFragmentManager(),"MyCustomDialog");
        }
       public void showLoginWithMail(View view)
        {
            LogInFragment logInFragment=new LogInFragment();
            logInFragment.show(getSupportFragmentManager(),"loca loca");
        }
        public void openSearchActivity(View view)
        {
            startActivity(new Intent(SignActivity.this, SearchActivity.class));
        }



    }



}
