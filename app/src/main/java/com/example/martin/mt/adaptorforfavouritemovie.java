package com.example.martin.mt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class adaptorforfavouritemovie extends RecyclerView.Adapter<viewholderforrecyclerview> {
    List<classmoviestore> resultList;
    itemclicklistener listener;
    Context context;
    LayoutInflater inflater;
    public adaptorforfavouritemovie(Context context,List<classmoviestore> resultList, itemclicklistener listener) {
        this.resultList = resultList;
        this.listener = listener;
        this.context = context;
    }


    @NonNull
    @Override
    public viewholderforrecyclerview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflater= (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View output=inflater.inflate( R.layout.layoutforviewallrecyclerview,viewGroup,false );
        return new viewholderforrecyclerview( output );
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholderforrecyclerview viewholderforrecyclerview, int i) {


        final classmoviestore result=resultList.get( i );
        long movieId=result.movieId;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.themoviedb.org/3/movie/" ).addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        callbackListener service = retrofit.create( callbackListener.class );
        Call<Pojomoviedetail> call = service.getmoviedetail( movieId + "", MainActivity.api_key, MainActivity.language );
        call.enqueue( new Callback<Pojomoviedetail>() {
            @Override
            public void onResponse(Call<Pojomoviedetail> call, Response<Pojomoviedetail> response) {
                Pojomoviedetail   moviedetail = response.body();

                viewholderforrecyclerview.postername.setText( moviedetail.getTitle() );
                viewholderforrecyclerview.posterrating.setText( moviedetail.getVoteAverage()+"" );

                GlideApp
                        .with( context )
                        .load("https://image.tmdb.org/t/p/w342/" + moviedetail.getBackdropPath())
                        .thumbnail( Glide.with(context).load(R.drawable.finalplaceholder))
                        .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                        .override( 500,500 )
                        .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
                        .into(viewholderforrecyclerview.posterimage  );

                List<Genre> genere=moviedetail.getGenres();
                StringBuilder generetoshow= new StringBuilder();
                for(int k=0;k<genere.size();k++){
                    if(generemap.allgenre().containsKey(  genere.get( k ).getId())){
                        generetoshow.append( " " ).append( generemap.allgenre().get( genere.get( k ).getId() ) );
                    }
                }

                viewholderforrecyclerview.genere.setText(generetoshow );
                }

            @Override
            public void onFailure(Call<Pojomoviedetail> call, Throwable t) {

            }
        } );

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
