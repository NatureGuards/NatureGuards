package com.example.toshiba.natureguards;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder> {

    private List<Bitmap> images;
    public MyRecAdapter(List<Bitmap> images) {
        this.images = images;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView.setImageBitmap(images.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);


            this.imageView = (ImageView) itemView.findViewById(R.id.im_view);
        }
    }
}
