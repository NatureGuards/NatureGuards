package com.example.toshiba.natureguards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by toshiba on 17.4.2016 г..
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<Events> event = new ArrayList<>();

    public MyAdapter(List<Events> event) {
        this.event = event;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.intent_event, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position = position;
        holder.txtDescription.setText(event.get(position).getDescription());
        holder.txtLocation.setText(event.get(position).getLocation());
        holder.txtOffice.setText(event.get(position).getOffice());
        holder.img.setImageBitmap(convertImage(position));
    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_description)
        TextView txtDescription;
        @Bind(R.id.txt_location)
        TextView txtLocation;
        @Bind(R.id.txt_office)
        TextView txtOffice;
        @Bind(R.id.img)
        ImageView img;
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    public Bitmap convertImage(int position) {
        String recievingString = event.get(position).getImg();
        byte[] decodedString = Base64.decode(recievingString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
