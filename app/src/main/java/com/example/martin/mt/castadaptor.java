package com.example.martin.mt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class castadaptor extends RecyclerView.Adapter<viewholdercastmovie> {
   LayoutInflater inflater;
    List<Cast> castlist;
   Context context;
    itemclicklistener listener;
    View output;
    public castadaptor(Context context, List<Cast> castlist, itemclicklistener listener) {
        this.castlist = castlist;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public viewholdercastmovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflater= (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        output=inflater.inflate( R.layout.layoutforcastmovie,viewGroup,false );
        return new viewholdercastmovie( output );
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholdercastmovie viewholdercastmovie, int i) {

       Cast cast=castlist.get( i );
        GlideApp
                .with( context )
                .load( "https://image.tmdb.org/t/p/w342/"+cast.getProfilePath())
                .placeholder( android.R.color.darker_gray )
                .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                .override( 200,200 )
                .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
                .into(viewholdercastmovie.castimage );

        viewholdercastmovie.castname.setText( cast.getName() );
        viewholdercastmovie.castcharectorname.setText( cast.getCharacter() );
        viewholdercastmovie.view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.myclick( view,viewholdercastmovie.getAdapterPosition() );
            }
        } );





    }

    @Override
    public int getItemCount() {
        return castlist.size();
    }
}
