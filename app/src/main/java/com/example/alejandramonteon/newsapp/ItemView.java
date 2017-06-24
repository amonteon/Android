package com.example.alejandramonteon.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class ItemView extends RecyclerView.Adapter<ItemView.ItemHolder>{

    private ArrayList<News> data;
    ItemClickListener listener;


    public ItemView(ArrayList<News> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.item, parent, shouldAttachToParentImmediately);
        ItemHolder holder = new ItemHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView url;

        ItemHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            url = (TextView)view.findViewById(R.id.url);
            view.setOnClickListener(this);
        }

        public void bind(int pos){
            News news = data.get(pos);
            name.setText(news.getTitle());
            url.setText(news.getUrl());
        }

        @Override
        public void onClick(View v) {
            int pos = getItemCount();
            listener.onItemClick(pos);
        }
    }
}
