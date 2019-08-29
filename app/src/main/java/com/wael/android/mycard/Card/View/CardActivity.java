package com.wael.android.mycard.Card.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.wael.android.mycard.Card.CardContract;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Card.presenter.CardPresenter;
import com.wael.android.mycard.Main.View.MainActivity;
import com.wael.android.mycard.PhotoActions;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.ActivityCardActivtyBinding;
import com.wael.android.mycard.databinding.FragmentCreateCardBinding;

import java.io.IOException;

public class CardActivity extends AppCompatActivity implements CardContract.view,CreateCardFragment.createCardClick,EditCardFragment.editCardClick {


    ActivityCardActivtyBinding activityCardActivtyBinding;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CardPresenter cardPresenter;
    ShowCardFragment showCardFragment;
    String userName;
    private String stringProfilePic;



    public PhotoActions mPhotoActions;

    public  static boolean doneImage;
    CreateCardFragment createCardFragment;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityCardActivtyBinding= DataBindingUtil.setContentView(this,R.layout.activity_card_activty);
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
       doneImage=false;
        cardPresenter=new CardPresenter(this);
         createCardFragment=new CreateCardFragment();
        Intent intent=getIntent();
        if(intent.hasExtra("firstTime"))
        {
            userName=intent.getStringExtra("firstTime");
            getSupportActionBar().setTitle("Create Account");
            fragmentTransaction.add(R.id.container,createCardFragment,"createFragment").commit();

        }
        if(intent.hasExtra("logged")) {
            if (cardPresenter.checkPreference())
            {
                cardPresenter.getFromPreference();
            }


            else {
            Card cardToPreference= cardPresenter.getCard();
            cardPresenter.addToPreference(cardToPreference);
        }
        }
        if(intent.hasExtra("edit"))
        {
                EditCardFragment editCardFragment=new EditCardFragment();
            getSupportActionBar().setTitle("Edit Card");
            Bundle bundle =new Bundle();
            bundle.putSerializable("cardToEdit",cardPresenter.getCardToEdit());
            editCardFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.container,editCardFragment).commit();
        }
        if(intent.hasExtra("searchedCard"))
        {
         showCard((Card) intent.getSerializableExtra("searchedCard"));
        }
       // ((IOnFocusListenable) sign_signing_fragment).onWindowFocusChanged(hasFocus);



        }
    public void setmPhotoActions(PhotoActions mPhotoActions) {
        this.mPhotoActions = mPhotoActions;
    }


    @Override
    public void showMainActivity() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFaildMessage() {

    }

    @Override
    public void showCard(Card card) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("Card", card);
        showCardFragment=new ShowCardFragment();
        showCardFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.container,showCardFragment).commit();


    }

    @Override
    public void createSocialAccounts(Card card) {

    }

    @Override
    public void setProgressInvisible() {
        mPhotoActions.setInvisisble();
    }

    @Override
    public void choosePhoto(Uri filePath) {
    cardPresenter.uploadImage(filePath);

        stringProfilePic=filePath.toString();


    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

    }

    @Override
    public void create(Card card) {
        card.setUserName(userName);

       Card cardToSave= cardPresenter.createCard(card);

        cardPresenter.addToPreference(cardToSave);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("createFragment");
        fragment.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void Edit(Card card) {
        cardPresenter.editCard(card);
        cardPresenter.addToPreference(card);
    }
}
