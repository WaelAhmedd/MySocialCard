package com.wael.android.mycard.Card.View;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Card.model.SocialAccount;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.ActivityProfileBinding;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowCardFragment extends Fragment implements ShowCardAdapter.SocialAccountsClick {

    Card card;

    ActivityProfileBinding profileBinding;
    Intent  intent;
    public static String FACEBOOK_URL = "https://www.facebook.com/YourPageName";
    public static String FACEBOOK_PAGE_ID = "YourPageName";
    ShowCardAdapter showCardAdapter;
    ArrayList<SocialAccount>accounts;


    public ShowCardFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        profileBinding= DataBindingUtil.inflate(inflater,R.layout.activity_profile,container,false);


            card=new Card();
         card= (Card) getArguments().getSerializable("Card");
         card.setSocialAccounts();
         accounts=card.getSocialAccounts();
        showCardAdapter=new ShowCardAdapter(getContext(),accounts,this);
         profileBinding.setCard(card);
            profileBinding.content.accountsRs.setAdapter(showCardAdapter);

        Glide.with(getActivity().getApplicationContext()).load(card.getPhotoUrl()).into(profileBinding.profileImage);
        return profileBinding.getRoot();

    }
    //""
    //"content://com.android.providers.media.documents/document/image%3A67696"

    @Override
    public void socialAccountClick(SocialAccount socialAccount) {
        String acc=socialAccount.getName();
        switch (acc)
        {
            //comparing value of variable against each case
            case "Facebook":
               openFacebook(socialAccount.getAccountUrl());
                break;
            case "Twitter":
               openTwitter(socialAccount.getAccountUrl());
                break;
            case "SnapChat":
               openSnap(socialAccount.getAccountUrl());
                break;
            case"LinkedIn":
                openLinkedIn(socialAccount.getAccountUrl());
            //optional
            default:
                System.out.println("Invalid Input!");
        }
    }
    public void  openFacebook(String userName)
    {
        String facebookUrl = "https://www.facebook.com/"+userName;
        try {
            getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);


            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href="+facebookUrl));
        } catch (Exception e) {
            // no Facebook app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW);
            intent .setData(Uri.parse(facebookUrl));
        }
        startActivity(intent);

    }

    public void openSnap(String username)
    {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + username));
            intent.setPackage("com.snapchat.android");
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + username)));
        }
    }
    public void openTwitter(String username)
    {
        try
        {
            // Check if the Twitter app is installed on the phone.
            getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);


            // Don't forget to put the "L" at the end of the id.

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + username)));

        }
        catch (PackageManager.NameNotFoundException e)
        {
            // If Twitter app is not installed, start browser.
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+card.getTwitterURL())));
        }
    }
    public void openLinkedIn(String username)
    {
        String url = "https://www.linkedin.com/in/"+username;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        final PackageManager packageManager = getContext().getApplicationContext().getPackageManager();
        final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.isEmpty()) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + card.getLinkedInURL()));
        }
        startActivity(intent);

    }

    public class ShowCardHandler{
       public void  openFacebook(View view)
        {
            String facebookUrl = "https://www.facebook.com/"+card.getFacebookURL();
            try {
               getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);


                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href="+facebookUrl));
            } catch (Exception e) {
                // no Facebook app, revert to browser
                intent = new Intent(Intent.ACTION_VIEW);
                intent .setData(Uri.parse(facebookUrl));
            }
            startActivity(intent);

        }
        public void openTwitter(View view)
        {
            try
            {
                // Check if the Twitter app is installed on the phone.
                getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);


                // Don't forget to put the "L" at the end of the id.

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + card.getTwitterURL())));

            }
            catch (PackageManager.NameNotFoundException e)
            {
                // If Twitter app is not installed, start browser.
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+card.getTwitterURL())));
            }
        }

        public void openInsta(View view)
        {
            try {

                getContext().getPackageManager().getPackageInfo("com.instagram.android", 0);

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/"+card.getInstaURl())));
            } catch (PackageManager.NameNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com"+card.getInstaURl())));
            }
        }


        }}