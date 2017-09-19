package com.cleveroad.sample.datasource;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cleveroad.sample.R;

/**
 * Created by derichardson on 8/31/17.
 */

public class DetailsViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;

    public DetailsViewHolder (ViewGroup parent) {
        super(parent);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cell = inflater.inflate(R.layout.fragment_tab_layout, parent, false);

        new DetailsViewHolder(cell);
    }

    public DetailsViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
    }

    public void setImage(ImageView image1) {
        image = image1;
    }

    public ImageView getImage() { return image;}
}
