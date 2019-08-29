package com.wael.android.mycard.Search;

import com.wael.android.mycard.Card.model.Card;

import java.util.ArrayList;

public interface SearchContract {

     interface view{
        void buildRecyclerView(ArrayList<Card>cards);
        void getListOfCards(ArrayList<Card> cards);
    }
     interface presenter{
            void setAdapter(String searchForThis);
    }
}
