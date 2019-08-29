package com.wael.android.mycard.Main.View;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.databinding.SearchItemBinding;

public class MainViewHolder extends RecyclerView.ViewHolder {
    SearchItemBinding binding;
    public MainViewHolder(@NonNull SearchItemBinding itemView) {
        super(itemView.getRoot());
            binding=itemView;
    }

    public void setDetails(Context context, Card card)
    {
        binding.setFoundedCard(card);
    }
}
