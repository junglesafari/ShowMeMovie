package com.example.martin.mt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Viewholdermovietrailer extends RecyclerView.ViewHolder {
    View view;
     ImageView movietrailerthumbnail;
     TextView movietrailername;
     TextView trailertype;


    public Viewholdermovietrailer(@NonNull View itemView) {
        super( itemView );
         movietrailerthumbnail=itemView.findViewById( R.id. movietrailerthumbnail);
         movietrailername=itemView.findViewById( R.id. movietrailername);
         trailertype=itemView.findViewById( R.id.trailertype );
         this.view=itemView;

    }
}
