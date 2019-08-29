package com.wael.android.mycard.Main.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.wael.android.mycard.Card.View.CardActivity;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Main.MainContract;
import com.wael.android.mycard.Main.Presenter.MainPresenter;
import com.wael.android.mycard.R;
import com.wael.android.mycard.Search.View.SearchActivity;
import com.wael.android.mycard.Sign.View.SignActivity;
import com.wael.android.mycard.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ActivityMainBinding binding;
    MainPresenter mainPresenter;
    String searchString;
    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
       MainHandler mainHandler=new MainHandler();
       searchString=" ";
       binding.setHandler(mainHandler);
       binding.setUserName(searchString);
            currentCard=new Card();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        mainPresenter=new MainPresenter(this);

        String s="https://firebasestorage.googleapis.com/v0/b/social-card-fec55.appspot.com/o/images%2FmDgiDRTlxAW5XiNnXNOSqYm4b5C2?alt=media&token=4b13b036-57a3-4183-b045-4a813c57e35d";
        Uri uri=Uri.parse(s);
        currentCard=mainPresenter.getFromPreference();
     Picasso.get().load(currentCard.getPhotoUrl()).into(binding.profileImage);


    }



    public  class MainHandler {

    public void openCard(View view) {
        Intent intent = new Intent(MainActivity.this, CardActivity.class);
        intent.putExtra("logged", "log");
        startActivity(intent);
    }

    public void editCard(View view)
    {
        Intent intent = new Intent(MainActivity.this, CardActivity.class);
        intent.putExtra("edit", "edit");
        startActivity(intent);

    }
    public void doSearch(View view)
    {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }
        public void loOut(View view)
        {

            AuthUI.getInstance()
                    .signOut(MainActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(MainActivity.this, SignActivity.class));
                                           finish();
                        }
                    });

        }
}


    }



