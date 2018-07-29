package com.example.martin.mt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class viewholdercastmovie extends RecyclerView.ViewHolder {
        View view;
        ImageView castimage;
        TextView castname;
        TextView castcharectorname;

    public viewholdercastmovie(@NonNull View itemView) {
        super( itemView );

        castimage=itemView.findViewById( R.id.castImage );
        castname=itemView.findViewById( R.id.castname );
        castcharectorname=itemView.findViewById( R.id.castcharectorname );
        this.view=itemView;
    }
}
