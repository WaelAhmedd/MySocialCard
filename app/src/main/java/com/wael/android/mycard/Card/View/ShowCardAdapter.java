package com.wael.android.mycard.Card.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wael.android.mycard.Card.model.SocialAccount;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.SocialAccountsRowItemBinding;

import java.util.ArrayList;

public class ShowCardAdapter extends RecyclerView.Adapter<ShowCardAdapter.ShowCardHolder> {
    SocialAccountsClick socialAccountsClick;
    public  interface SocialAccountsClick{

        void socialAccountClick(SocialAccount socialAccount);
    }
    Context context;
    ArrayList<SocialAccount> socialAccounts;

    public ShowCardAdapter(Context context, ArrayList<SocialAccount> socialAccounts,SocialAccountsClick socialAccountsClick) {
        this.context = context;
        this.socialAccounts = socialAccounts;
        this.socialAccountsClick=socialAccountsClick;
    }

    @NonNull
    @Override
    public ShowCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SocialAccountsRowItemBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.social_accounts_row_item,parent,false);
        return new ShowCardHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ShowCardHolder holder, int position) {
            SocialAccount socialAccount=socialAccounts.get(position);
   if (socialAccount.getName().equals("Facebook"))
   {
       holder.binding.profileImage.setImageResource(R.drawable.facebook_icon);
   }
        else if (socialAccount.getName().equals("Twitter"))
        {
            holder.binding.profileImage.setImageResource(R.drawable.twitter);
        }
        else if (socialAccount.getName().equals("Instagram"))
        {
            holder.binding.profileImage.setImageResource(R.drawable.instagram);
        }
        else if (socialAccount.getName().equals("LinkedIn"))
        {
            holder.binding.profileImage.setImageResource(R.drawable.linkedin);
        }
        else if (socialAccount.getName().equals("SnapChat"))
        {
            holder.binding.profileImage.setImageResource(R.drawable.snapchat);
        }
    }

    @Override
    public int getItemCount() {
        return (socialAccounts == null) ? 0 : socialAccounts.size();
    }

    class ShowCardHolder extends RecyclerView.ViewHolder
    {
        SocialAccountsRowItemBinding binding;
        public ShowCardHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    socialAccountsClick.socialAccountClick(socialAccounts.get(getAdapterPosition()));
                }
            });
        }
    }

}
