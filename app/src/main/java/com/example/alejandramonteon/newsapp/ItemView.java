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
        //TextView author;
        TextView title;
        TextView description;
        //TextView url;
        //TextView urlToImage;
        TextView publishedAt;

        ItemHolder(View view){
            super(view);
            //author = (TextView)view.findViewById(R.id.author);
            title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);
            //url = (TextView)view.findViewById(R.id.url);
            //urlToImage = (TextView)view.findViewById(R.id.urlToImage);
            publishedAt = (TextView)view.findViewById(R.id.publishedAt);
            view.setOnClickListener(this);
        }

        public void bind(int pos){
            News news = data.get(pos);
            //author.setText(news.getAuthor());
            title.setText(news.getTitle());
            description.setText(news.getDescription());
            //url.setText(news.getUrl());
            //urlToImage.setText(news.getUrltoImage());
            publishedAt.setText(news.getPublishedAt());
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }
}
