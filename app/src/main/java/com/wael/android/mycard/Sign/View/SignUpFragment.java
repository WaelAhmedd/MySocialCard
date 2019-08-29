package com.wael.android.mycard.Sign.View;


import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wael.android.mycard.Account;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.CreateAccountFragmentBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends DialogFragment  {
    Account account;
    public interface OnInputListener{
        void sendInput(Account input);
    }
    public OnInputListener mOnInputListener;
    CreateAccountFragmentBinding binding;



    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding=DataBindingUtil.inflate(inflater,R.layout.create_account_fragment,container,false);
        account=new Account();
        SignUpFragmentHandler signUpFragmentHandler=new SignUpFragmentHandler();
        binding.setAcc(account);
        binding.setFragmentHandler(signUpFragmentHandler);
        account=binding.getAcc();
        return binding.getRoot();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
    mOnInputListener= (OnInputListener) getActivity();

        }catch (ClassCastException e)
        {
            Toast.makeText(context, "fe error", Toast.LENGTH_SHORT).show();
        }
    }

    public class SignUpFragmentHandler{

        public void clickOk(View view)
        {
            if( checkAccount(account)) {

                mOnInputListener.sendInput(account);

                getDialog().dismiss();
            }
        }

        private boolean checkAccount(Account account) {
            if(TextUtils.isEmpty(account.getEmail()) )
            {
                Toast.makeText(getContext(), "Email is empty ", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(TextUtils.isEmpty(account.getPassword()))
            {
                Toast.makeText(getContext(), "Password is empty ", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(TextUtils.isEmpty(account.getUserName()))
            {
                //check if the user name is used or not
                Toast.makeText(getContext(), "User Name is empty ", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }

    }


}
