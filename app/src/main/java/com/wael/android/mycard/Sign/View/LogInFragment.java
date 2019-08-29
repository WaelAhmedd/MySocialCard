package com.wael.android.mycard.Sign.View;


import android.content.Context;
import android.content.pm.SigningInfo;
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
import com.wael.android.mycard.databinding.FragmentLogInBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends DialogFragment {
    Account account;
    FragmentLogInBinding binding;

    public interface signInInput{
        void getLogInInfo(Account input);
    }
    public signInInput mOnInputListener;



    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_log_in,container,false);
        account=new Account();
        LogInFragmentHandler logInFragmentHandler=new LogInFragmentHandler();
        binding.setAccount(account);
        binding.setLogInHandler(logInFragmentHandler);
        account=binding.getAccount();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            mOnInputListener= (signInInput) getActivity();
        }catch (ClassCastException e)
        {
            Toast.makeText(context, "fe she2 ghareb", Toast.LENGTH_SHORT).show();
        }
    }

    public class LogInFragmentHandler{
        public void signIn(View view) {

            if(checkLogIn()) {
                mOnInputListener.getLogInInfo(account);
                getDialog().dismiss();
            }

        }

    boolean checkLogIn()
    {
        if (TextUtils.isEmpty(account.getEmail())) {
            Toast.makeText(getContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
       return false;
        }

        if (TextUtils.isEmpty(account.getPassword())) {
            Toast.makeText(getContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        return false;
        }
        else return true;
    }
    }



}

