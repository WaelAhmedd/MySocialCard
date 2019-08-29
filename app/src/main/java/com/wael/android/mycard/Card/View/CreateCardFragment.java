package com.wael.android.mycard.Card.View;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Card.presenter.CardPresenter;
import com.wael.android.mycard.PhotoActions;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.FragmentCreateCardBinding;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;



/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCardFragment extends Fragment implements PhotoActions {



    public interface createCardClick{
       void create(Card card);
       void choosePhoto(Uri filepath);
   }
    private CreateCardHandler cardHandler;
 private FragmentCreateCardBinding binding;
    private final int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    CardPresenter cardPresenter;
 private Card card;
        private createCardClick createCardClick;


    CardActivity activity;



        boolean n;
 public CreateCardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CardActivity) getActivity()).setmPhotoActions(CreateCardFragment.this);
    n=activity.doneImage;

 }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

   binding= DataBindingUtil.inflate(inflater, R.layout.fragment_create_card,container,false);
   card=new Card();
   cardHandler=new CreateCardHandler();
   binding.setCard(card);
   binding.setHandler(cardHandler);
   binding.pBar.setVisibility(View.INVISIBLE);
   card=binding.getCard();
        cardPresenter=new CardPresenter(getContext());
        if(getArguments()!=null)
        {


        }
   return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            createCardClick= (CreateCardFragment.createCardClick) getActivity();

        }
        catch (ClassCastException e)
        {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            filePath = data.getData();

            binding.pBar.setVisibility(View.VISIBLE);
            createCardClick.choosePhoto(filePath);




        }


    }
    @Override
    public void setInvisisble() {
binding.pBar.setVisibility(View.INVISIBLE);
    }


    public class CreateCardHandler{
    public void createCardClick(View view)
     {
         if(checkCard()){

             createCardClick.create(card);

     }

     }
            public void pickPhoto(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                getActivity().startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
     boolean checkCard()
     {
         if(TextUtils.isEmpty(card.getAddress()))
         {
             Toast.makeText(getContext(), "Please Enter Your Address", Toast.LENGTH_SHORT).show();
             return false;
         }
         if(TextUtils.isEmpty(card.getEmail()))
         {
             Toast.makeText(getContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
         return false;
         }
         if(TextUtils.isEmpty(card.getNumber()))
         {
             Toast.makeText(getContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
             return false;
         }
         if (TextUtils.isEmpty(card.getPosition()))
         {
             Toast.makeText(getContext(), "Please Enter Your Position", Toast.LENGTH_SHORT).show();
                return false;
         }
         if (TextUtils.isEmpty(card.getName()))
         {
             Toast.makeText(getContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
             return false;
         }
         else
             return true;
     }
    }

}
