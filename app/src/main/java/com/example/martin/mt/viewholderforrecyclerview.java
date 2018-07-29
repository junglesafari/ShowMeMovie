package com.example.martin.mt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class viewholderforrecyclerview extends RecyclerView.ViewHolder {
    ImageView posterimage;
    TextView postername;
    TextView posterrating;
    View itemview;
    TextView genere;

    public viewholderforrecyclerview(@NonNull View itemView) {
        super( itemView );
    posterimage=itemView.findViewById( R.id.posterimage );
    postername=itemView.findViewById( R.id.postername );
    posterrating=itemView.findViewById( R.id.posterrating );
    this.itemview=itemView;
     genere=itemView.findViewById( R.id.genere );
    }
}
