package com.example.androidgezginapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidgezginapp.model.Place;
import com.example.androidgezginapp.R;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {
    public List <Place> productList;
    LayoutInflater inflater;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView post_description;
        public TextView post_title;
        public ImageView post_picture;

        public MyViewHolder(View v) {
            super(v);
            post_title = (TextView) itemView.findViewById(R.id.place);
            post_description = (TextView) itemView.findViewById(R.id.place_description);
            post_picture = (ImageView) itemView.findViewById(R.id.image_place);

        }
    }

    public PlaceAdapter(List<Place> _productList, Context _context) {
        productList = _productList;
        context = _context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PlaceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_places, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.post_description.setText(productList.get(position).getPlaceDescription());
        holder.post_title.setText(String.valueOf(productList.get(position).getPlace()));
        holder.post_picture.setImageResource(productList.get(position).getPlaceImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

