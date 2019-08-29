package com.wael.android.mycard.Search.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wael.android.mycard.Card.View.CardActivity;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.R;
import com.wael.android.mycard.Search.Presenter.SearchPresenter;
import com.wael.android.mycard.Search.SearchAdapter;
import com.wael.android.mycard.Search.SearchContract;
import com.wael.android.mycard.databinding.ActivitySearchBinding;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchContract.view ,SearchAdapter.CardListItemClick {

    ActivitySearchBinding binding;
    SearchAdapter searchAdapter;
    SearchPresenter searchPresenter;
    String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            binding= DataBindingUtil.setContentView(this,R.layout.activity_search);
            binding.setSearchName(search);
            SearchHandler searchHandler=new SearchHandler();
            binding.setHandler(searchHandler);
            searchPresenter=new SearchPresenter(this);


    }

    @Override
    public void buildRecyclerView(ArrayList<Card> cards) {
        searchAdapter=new SearchAdapter(this,cards,this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void getListOfCards(ArrayList<Card> cards) {

    }

    @Override
    public void onCardClick(Card selectedCard) {
        Intent intent=new Intent(this, CardActivity.class);
        intent.putExtra("searchedCard",selectedCard);
        startActivity(intent);
    }

    public class SearchHandler{
        public void doSearch(View view)
        {
            search=binding.getSearchName();
            searchPresenter.setAdapter(search);
        }

    }
}
