package com.wael.android.mycard.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.R;
import com.wael.android.mycard.databinding.SearchItemBinding;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context context;
    private ArrayList<Card> cards;

    final   private CardListItemClick cardListItemClick;
    public interface CardListItemClick{
        void onCardClick(Card selectedCard);
    }


    public SearchAdapter(Context context, ArrayList<Card> cards,CardListItemClick cardListItemClick) {
        this.context = context;
        this.cards = cards;
        this.cardListItemClick=cardListItemClick;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       SearchItemBinding searchItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
               R.layout.search_item,parent,false);


        return new SearchViewHolder(searchItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Card card=cards.get(position);
        holder.searchItemBinding.setFoundedCard(card);
        Glide.with(context.getApplicationContext()).load(card.getPhotoUrl()).into(holder.searchItemBinding.profileImage);
    }


    @Override
    public int getItemCount() {
        return (cards == null) ? 0 : cards.size();
    }

    public  class SearchViewHolder extends RecyclerView.ViewHolder {
        SearchItemBinding searchItemBinding;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            searchItemBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                cardListItemClick.onCardClick(cards.get(getAdapterPosition()));

                }
            });

        }


    }

}
