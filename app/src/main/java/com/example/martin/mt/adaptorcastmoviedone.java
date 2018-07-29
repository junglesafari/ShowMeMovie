package com.example.martin.mt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adaptorcastmoviedone extends RecyclerView.Adapter<viewholderforrecyclerview> {
    List<Castmoviedone> resultList;
    itemclicklistener listener;
    Context context;
    LayoutInflater inflater;
    int catagory=0;
    View output;

    public  adaptorcastmoviedone(Context context,List<Castmoviedone> resultList,int catagory, itemclicklistener listener) {
        this.resultList = resultList;
        this.listener = listener;
        this.context = context;
        this.catagory=catagory;
    }


    @NonNull
    @Override
    public viewholderforrecyclerview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflater=(LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        if(catagory==1) {
            output = inflater.inflate( R.layout.layoutforrecyclerview, viewGroup, false );
        }else if(catagory==2){
            output = inflater.inflate( R.layout.layoutforviewallrecyclerview, viewGroup, false );
        }


        return new viewholderforrecyclerview( output );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final viewholderforrecyclerview viewholderforrecyclerview, int i) {
        Castmoviedone result=resultList.get( i );
        viewholderforrecyclerview.postername.setText( result.getTitle() );
        viewholderforrecyclerview.posterrating.setText( result.getVoteAverage().toString() );
        List<Long> genre_ids=result.getGenreIds();
        StringBuilder generetoshow= new StringBuilder();
//       for(int k=0;k<genre_ids.size();k++){
//           if(genre_ids.get( k ).equals( MainActivity.generelist.get( k ).getId() )){
//               generetoshow.append( " " ).append( MainActivity.generelist.get( k ).getName() );
//           }
//       }
        // viewholderforrecyclerview.genere.setText( generetoshow.toString() );
        Picasso.with( context )
                .load( "https://image.tmdb.org/t/p/w342/"+result.getPosterPath())
                .fit()
                .centerCrop()
                .placeholder( android.R.color.darker_gray )
                .into(viewholderforrecyclerview.posterimage  );
        viewholderforrecyclerview.itemview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.myclick( view,viewholderforrecyclerview.getAdapterPosition() );
            }
        } );




    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
