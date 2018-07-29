package com.example.martin.mt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adaptorformovietrailer extends RecyclerView.Adapter<Viewholdermovietrailer> {
    List<Resultmovietrailer> resultList;
    itemclicklistener listener;
    Context context;
    View output;
    LayoutInflater inflater;

    public adaptorformovietrailer(Context context,List<Resultmovietrailer> resultList, itemclicklistener listener) {
        this.resultList = resultList;
        this.listener = listener;
        this.context = context;
    }


    @NonNull
    @Override
    public Viewholdermovietrailer onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflater= (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        output=inflater.inflate( R.layout.layoutformovietrailer,viewGroup,false );
        return new Viewholdermovietrailer( output );
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholdermovietrailer viewholdermovietrailer, int i) {
         viewholdermovietrailer.movietrailername.setText( resultList.get( i ).getName() );
         viewholdermovietrailer.trailertype.setText(  resultList.get( i ).getType());
        Picasso
                .with( context )
                .load(String.valueOf("http://img.youtube.com/vi/" + resultList.get( i ).getKey() + "/maxresdefault.jpg\n"))
                .fit()
                .placeholder(R.color.green_grass)
                .into(viewholdermovietrailer.movietrailerthumbnail);
         viewholdermovietrailer.view.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listener.myclick( view,viewholdermovietrailer.getAdapterPosition() );
             }
         } );

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
