package com.wael.android.mycard.Main;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Main.View.MainViewHolder;

public interface MainContract {
    interface View{

    }

    interface Presenter{

        void logout();
        Card getFromPreference();

    }
}
