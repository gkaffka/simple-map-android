package com.kaffka.simplemap.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaffka.simplemap.Models.Unit;
import com.kaffka.simplemap.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdapterSimpleMapData extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Unit> mListUnits;

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_title)
        TextView mTitle;
        @Bind(R.id.txt_snippet)
        TextView mSnippet;

        public CardViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public AdapterSimpleMapData(List<Unit> mListUnits) {
        this.mListUnits = mListUnits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new CardViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CardViewHolder) {
            loadItemViews((CardViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if (mListUnits.isEmpty()) return 0;
        return mListUnits.size();
    }

    private void loadItemViews(final CardViewHolder holder, final int position) {
        final Unit u = mListUnits.get(position);
        holder.mTitle.setText(u.getNome());
        holder.mSnippet.setText(u.buildSnippet());
    }

}