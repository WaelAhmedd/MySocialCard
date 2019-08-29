package com.wael.android.mycard.Card.View;


import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.FragmentEditCardBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditCardFragment extends Fragment {

    public interface editCardClick{
        void Edit(Card card);
    }
   private FragmentEditCardBinding binding;
   private editCardClick mEditCardClick;
    private Card card;
    private EditHandler editHandler;
    public EditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        editHandler=new EditHandler();
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_edit_card,container,false);
       card=new Card();
       if (getArguments()!=null)
       {
           card=(Card) getArguments().getSerializable("cardToEdit");

           binding.setCard(card);
       }
       binding.setEditHandler(editHandler);
       return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            mEditCardClick= (editCardClick) getActivity();

        }
        catch (ClassCastException e)
        {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }

    public class EditHandler{

        public void doEdit(View view)

        {
            Toast.makeText(getContext(), "edit ahu ya 3am", Toast.LENGTH_SHORT).show();
                mEditCardClick.Edit(binding.getCard());

        }

    boolean checkCard()
    {
        if(TextUtils.isEmpty(card.getAddress()))
        {
            Toast.makeText(getContext(), getActivity().getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(card.getEmail()))
        {
            Toast.makeText(getContext(), getActivity().getString(R.string.enter_mail), Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(card.getNumber()))
        {
            Toast.makeText(getContext(),getActivity().getString( R.string.enter_name), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(card.getPosition()))
        {
            Toast.makeText(getContext(),getActivity().getString( R.string.enter_position), Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
}
}
