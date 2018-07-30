package com.example.martin.mt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class castdetailactivity extends AppCompatActivity {
    ImageView backgroundpostercastdetail;
    Toolbar toolbarcastdetail;
    RecyclerView moviecasttvdone;
    RecyclerView moviecastdetailrecyclerview;
    TextView birthplace;
    TextView birthday;
    TextView castbiography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_castdetailactivity );
        this.getWindow().getDecorView().setBackgroundColor( getResources().getColor( R.color.colorgray ) );

        backgroundpostercastdetail = findViewById( R.id.backgroundpostercastdetail );
        moviecastdetailrecyclerview = findViewById( R.id.moviecastmoviesdone );
        toolbarcastdetail = findViewById( R.id.toolbarcastdetail );
        moviecasttvdone=findViewById( R.id.moviecasttvdone );
        birthday=findViewById( R.id.birthday );
        birthplace=findViewById( R.id.birthplace );
        castbiography=findViewById( R.id.castbiography );

        Intent intent = getIntent();
        long castId = intent.getLongExtra( "castId", 0 );

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.themoviedb.org/3/person/" ).addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        callbackListener service = retrofit.create( callbackListener.class );
        Call<Pojocastdetail> call = service.getCastDetail( castId + "", MainActivity.api_key, MainActivity.language );
        call.enqueue( new Callback<Pojocastdetail>() {
            @Override
            public void onResponse(Call<Pojocastdetail> call, Response<Pojocastdetail> response) {
                Pojocastdetail data = response.body();
//                Picasso
//                        .with( castdetailactivity.this )
//                        .load( "https://image.tmdb.org/t/p/w342/" + data.getProfilePath() )
//                        .fit()
//                        .placeholder( android.R.color.darker_gray )
//                        .into( backgroundpostercastdetail );

                GlideApp
                        .with( castdetailactivity.this )
                        .load( "https://image.tmdb.org/t/p/w342/" + data.getProfilePath())
                        .thumbnail( Glide.with(castdetailactivity.this).load(R.drawable.finalplaceholder2))
                        .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                        .override( 500,500 )
                        .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
                        .into(backgroundpostercastdetail  );


                castbiography.setText( data.getBiography() );
                birthplace.setText( data.getPlaceOfBirth() );
                birthday.setText( data.getBirthday() );
                toolbarcastdetail.setTitle( data.getName() );
            }

            @Override
            public void onFailure(Call<Pojocastdetail> call, Throwable t) {

            }
        } );

        Call<Pojocastmoviedone> callcastmoviedone = service.getCastDoneMovie( castId + "/movie_credits", MainActivity.api_key, MainActivity.language );
        callcastmoviedone.enqueue( new Callback<Pojocastmoviedone>() {
            @Override
            public void onResponse(Call<Pojocastmoviedone> call, Response<Pojocastmoviedone> response) {

                Pojocastmoviedone data = response.body();
                final List<Castmoviedone> resultsimiliarmovies = data.getCast();

                adaptorcastmoviedone adaptor = new adaptorcastmoviedone( castdetailactivity.this, resultsimiliarmovies, 2, new itemclicklistener() {
                    @Override
                    public void myclick(View view, int position) {
                        Toast.makeText( castdetailactivity.this, "item" + position, Toast.LENGTH_SHORT ).show();
                        detail(resultsimiliarmovies.get( position ).getId());

                    }
                } );
                LinearLayoutManager manager = new LinearLayoutManager( castdetailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                moviecastdetailrecyclerview.addItemDecoration( new DividerItemDecoration( castdetailactivity.this, DividerItemDecoration.VERTICAL ) );
                moviecastdetailrecyclerview.setLayoutManager( manager );
                moviecastdetailrecyclerview.setAdapter( adaptor );


            }

            @Override
            public void onFailure(Call<Pojocastmoviedone> call, Throwable t) {

            }
        } );

        Call<Pojocasttvdone> callcasttvdone=service.getCastDoneTv( castId+"/tv_credits",MainActivity.api_key,MainActivity.language );
        callcasttvdone.enqueue( new Callback<Pojocasttvdone>() {
            @Override
            public void onResponse(Call<Pojocasttvdone> call, Response<Pojocasttvdone> response) {
                Pojocasttvdone data = response.body();
                List<Casttvdone> resultsimiliarmovies = data.getCast();

                adaptortvdone adaptor = new adaptortvdone( castdetailactivity.this, resultsimiliarmovies, 2, new itemclicklistener() {
                    @Override
                    public void myclick(View view, int position) {
                        Toast.makeText( castdetailactivity.this, "item" + position, Toast.LENGTH_SHORT ).show();
                    }
                } );
                LinearLayoutManager manager = new LinearLayoutManager( castdetailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                moviecasttvdone.addItemDecoration( new DividerItemDecoration( castdetailactivity.this, DividerItemDecoration.VERTICAL ) );
                moviecasttvdone.setLayoutManager( manager );
                moviecasttvdone.setAdapter( adaptor );


            }

            @Override
            public void onFailure(Call<Pojocasttvdone> call, Throwable t) {

            }
        } );

    }






    public void detail(long mId){
        Intent intent=new Intent( castdetailactivity.this,detailactivity.class );
        intent.putExtra( "movieId",mId );
        startActivity( intent );
    }










}
