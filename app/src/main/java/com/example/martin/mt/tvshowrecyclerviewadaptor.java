package com.example.martin.mt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class tvshowrecyclerviewadaptor  extends RecyclerView.Adapter<viewholderforrecyclerview> {
   LayoutInflater inflater;
    Context context;
    List<resulttv> resultList;
    itemclicklistener listener;
    View output;
    int catagory=0;
    public tvshowrecyclerviewadaptor(Context context, List<resulttv> resultList,int catagory, itemclicklistener listener) {
        this.context = context;
        this.resultList = resultList;
        this.listener = listener;
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

        resulttv result=resultList.get( i );
        Log.d( "rr",result.getOriginalName()+"   "+result.getVoteAverage().toString() );
        viewholderforrecyclerview.postername.setText( result.getOriginalName() );
        viewholderforrecyclerview.posterrating.setText( result.getVoteAverage().toString() );
//        List<Long> genre_ids=result.getGenreIds();
//        StringBuilder generetoshow= new StringBuilder();
//       for(int k=0;k<genre_ids.size();k++){
//           if(genre_ids.get( k ).equals( MainActivity.generelist.get( k ).getId() )){
//               generetoshow.append( " " ).append( MainActivity.generelist.get( k ).getName() );
//           }
//       }
        // viewholderforrecyclerview.genere.setText( generetoshow.toString() );
        GlideApp
                .with( context )
                .load( "https://image.tmdb.org/t/p/w342/"+result.getPosterPath())
                .thumbnail( Glide.with(context).load(R.drawable.placeholder2))
                .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                .override( 500,500 )
                .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
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


