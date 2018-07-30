package com.example.martin.mt;

import android.app.ActivityOptions;
import android.arch.persistence.room.Room;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.robertlevonyan.views.expandable.Expandable;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailactivity extends AppCompatActivity {
ImageView backgroundposterdetailactivity;
ImageView posterdetailactivity;
Pojomoviedetail moviedetail;
Toolbar toolbar;
RecyclerView castrecyclerview;
RecyclerView similiarmovierecyclerview;
RecyclerView movietrailerrecyclerview;
AppBarLayout appBarLayout;
TextView moviename;
TextView moviegenere;
TextView detailactivityoverview;
TextView detailactivityreleasedate;
TextView detailactivityruntime;
TextView detailratingtextview;
TextView trailerheading;
TextView similiarheading;
ImageButton likebutton;
    Expandable expandable ;
    movieDao movieDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detailactivity );
        this.getWindow().getDecorView().setBackgroundColor( getResources().getColor( R.color.colorgray ) );
        toolbar=(Toolbar) findViewById( R.id.toolbarmoviedetail );
        appBarLayout=findViewById( R.id.app_bar_movie_detail_activity );
        likebutton=findViewById( R.id.likebutton );

        Intent intent=getIntent();
        long Id=intent.getLongExtra( "movieId" ,0);
        String tvOrMovie=intent.getStringExtra( "tvormovie" );


        moviedatabase database = Room.databaseBuilder(getApplicationContext(),moviedatabase.class,"classmoviestore").allowMainThreadQueries().build();
        movieDao = database.getmovieDao();
        List<classmoviestore> storedmovielist=movieDao.getmovie();
       for(int i=0;i<storedmovielist.size();i++){
           if(storedmovielist.get( i ).movieId==Id){
               likebutton.setBackground( getResources().getDrawable( R.drawable.ic_favorite_black_24dp ) );
               break;
           }
       }


       // Toast.makeText( this, Id + "", Toast.LENGTH_SHORT ).show();
        backgroundposterdetailactivity = findViewById( R.id.backgroundposterdetailactivity );
        posterdetailactivity = findViewById( R.id.posterdetailactivity );
        castrecyclerview = findViewById( R.id.moviecastrecyclerview );
        similiarmovierecyclerview = findViewById( R.id.similiarmovierecyclerview );
        movietrailerrecyclerview = findViewById( R.id.movietrailerrecyclerview );
        moviename=findViewById( R.id.moviename );
        moviegenere=findViewById( R.id.moviegenere );
        detailactivityoverview=findViewById( R.id.detailactivityoverview );
        detailactivityreleasedate=findViewById( R.id.detailactivityreleasedate );
        detailactivityruntime=findViewById( R.id.detailactivityruntime );
        detailratingtextview=findViewById( R.id.detailratingtextview );
        trailerheading=findViewById( R.id.textView6 );
        similiarheading=findViewById( R.id.textView8 );

        expandable = findViewById(R.id.expandable);
        expandable.setAnimateExpand(true);

        appBarLayout.addOnOffsetChangedListener( new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(i>=-400){
                    toolbar.setVisibility( View.GONE );
                }else {
                    toolbar.setVisibility( View.VISIBLE );
                }
            }
        } );


        if(tvOrMovie.equals( "movie" )) {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.themoviedb.org/3/movie/" ).addConverterFactory( GsonConverterFactory.create() );
            Retrofit retrofit = builder.build();
            callbackListener service = retrofit.create( callbackListener.class );


           Call<Pojomoviedetail> call = service.getmoviedetail( Id + "", MainActivity.api_key, MainActivity.language );
           call.enqueue( new Callback<Pojomoviedetail>() {
               @Override
               public void onResponse(Call<Pojomoviedetail> call, Response<Pojomoviedetail> response) {
                   moviedetail = response.body();
                   Picasso
                           .with( detailactivity.this )
                           .load( "https://image.tmdb.org/t/p/w342/" + moviedetail.getBackdropPath() )
                           .fit()
                           .placeholder( android.R.color.darker_gray )
                           .into( backgroundposterdetailactivity );


                   Picasso
                           .with( detailactivity.this )
                           .load( "https://image.tmdb.org/t/p/w92/" + moviedetail.getPosterPath() )
                           .fit()
                           .placeholder( android.R.color.darker_gray )
                           .into( posterdetailactivity );

                   detailratingtextview.setText( moviedetail.getVoteAverage()+"" );
                   detailactivityreleasedate.setText( moviedetail.getReleaseDate() );
                   detailactivityruntime.setText( moviedetail.getRuntime()+"" );
                   detailactivityoverview.setText( moviedetail.getOverview() );
                   toolbar.setTitle( moviedetail.getTitle() );
                   moviename.setText( moviedetail.getTitle() );
                   moviegenere.setText( "genere to be set" );

                   likebutton.setOnClickListener( new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           classmoviestore data=new classmoviestore( moviedetail.getId() );
                           moviedatabase database = Room.databaseBuilder(getApplicationContext(),moviedatabase.class,"classmoviestore").allowMainThreadQueries().build();
                           movieDao = database.getmovieDao();
                           movieDao.addmovie( data );
                           // Toast.makeText( detailactivity.this,"item liked",Toast.LENGTH_SHORT ).show();
                           likebutton.setBackground( getResources().getDrawable( R.drawable.ic_favorite_black_24dp ) );
                       }
                   } );



                   List<Genre> genere=moviedetail.getGenres();
                   StringBuilder generetoshow= new StringBuilder();
                   for(int k=0;k<genere.size();k++){
                       if(generemap.allgenre().containsKey(  genere.get( k ).getId())){
                           generetoshow.append( " " ).append( generemap.allgenre().get( genere.get( k ).getId() ) );
                       }
                   }

                   moviegenere.setText(generetoshow );



                   setSupportActionBar( toolbar );


               }

               @Override
               public void onFailure(Call<Pojomoviedetail> call, Throwable t) {

               }
           } );


           Call<Pojomoviecast> callforcast = service.getmoviecast( Id + "/credits", MainActivity.api_key );
           callforcast.enqueue( new Callback<Pojomoviecast>() {
               @Override
               public void onResponse(Call<Pojomoviecast> call, Response<Pojomoviecast> response) {
                   final List<Cast> castdata = response.body().getCast();
                   castadaptor castadaptor = new castadaptor( detailactivity.this, castdata, new itemclicklistener() {
                       @Override
                       public void myclick(View view, int position) {
                           Toast.makeText( getApplicationContext(), "item" + position, Toast.LENGTH_SHORT ).show();
                           castdetail( castdata.get( position ).getId() );

                       }


                   } );
                   LinearLayoutManager manager = new LinearLayoutManager( detailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                   castrecyclerview.addItemDecoration( new DividerItemDecoration( detailactivity.this, DividerItemDecoration.VERTICAL ) );
                   castrecyclerview.setLayoutManager( manager );
                   castrecyclerview.setAdapter( castadaptor );

               }

               @Override
               public void onFailure(Call<Pojomoviecast> call, Throwable t) {

               }
           } );

           Call<Pojoforsimiliarmovies> callforsimiliarmovies = service.getsimiliarmovies( Id + "/similar", MainActivity.api_key, MainActivity.language, 1 );
           callforsimiliarmovies.enqueue( new Callback<Pojoforsimiliarmovies>() {
               @Override
               public void onResponse(Call<Pojoforsimiliarmovies> call, Response<Pojoforsimiliarmovies> response) {
                   Pojoforsimiliarmovies data = response.body();
                   final List<Result> resultsimiliarmovies = data.getResults();
                      if(resultsimiliarmovies.size()==0){
                          similiarheading.setVisibility( View.GONE );
                      }
                   recyclerviewadaptor adaptor = new recyclerviewadaptor( detailactivity.this, resultsimiliarmovies, 2, new itemclicklistener() {
                       @Override
                       public void myclick(View view, int position) {
                           Toast.makeText( detailactivity.this, "item" + position, Toast.LENGTH_SHORT ).show();
                           detail( resultsimiliarmovies.get( position ).getId(),"movie" );

                       }
                   } );
                   LinearLayoutManager manager = new LinearLayoutManager( detailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                   similiarmovierecyclerview.addItemDecoration( new DividerItemDecoration( detailactivity.this, DividerItemDecoration.VERTICAL ) );
                   similiarmovierecyclerview.setLayoutManager( manager );
                   similiarmovierecyclerview.setAdapter( adaptor );


               }

               @Override
               public void onFailure(Call<Pojoforsimiliarmovies> call, Throwable t) {

               }
           } );

           Call<Pojomoivietrialer> callforvideo = service.getmovietrailer( Id + "/videos", MainActivity.api_key, MainActivity.language );
           callforvideo.enqueue( new Callback<Pojomoivietrialer>() {
               @Override
               public void onResponse(Call<Pojomoivietrialer> call, Response<Pojomoivietrialer> response) {

                   Pojomoivietrialer data = response.body();
                   final List<Resultmovietrailer> resultvideo = data.getResults();
                   if(resultvideo.size()==0){trailerheading.setVisibility( View.GONE );}
                   adaptorformovietrailer adaptor = new adaptorformovietrailer( detailactivity.this, resultvideo, new itemclicklistener() {
                       @Override
                       public void myclick(View view, int position) {
                           Toast.makeText( detailactivity.this, "item" + position, Toast.LENGTH_SHORT ).show();
                           openyoutube( resultvideo.get( position ).getKey() );

                       }
                   } );
                   LinearLayoutManager manager = new LinearLayoutManager( detailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                   movietrailerrecyclerview.addItemDecoration( new DividerItemDecoration( detailactivity.this, DividerItemDecoration.VERTICAL ) );
                   movietrailerrecyclerview.setLayoutManager( manager );
                   movietrailerrecyclerview.setAdapter( adaptor );


               }

               @Override
               public void onFailure(Call<Pojomoivietrialer> call, Throwable t) {

               }
           } );

//            likebutton.setOnClickListener( new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    classmoviestore data=new classmoviestore( moviedetail.getId() );
//                    moviedatabase database = Room.databaseBuilder(getApplicationContext(),moviedatabase.class,"classmoviestore").allowMainThreadQueries().build();
//                    movieDao = database.getmovieDao();
//                    movieDao.addmovie( data );
//                   // Toast.makeText( detailactivity.this,"item liked",Toast.LENGTH_SHORT ).show();
//                    likebutton.setBackground( getResources().getDrawable( R.drawable.ic_favorite_black_24dp ) );
//                }
//            } );



       }else if(tvOrMovie.equals( "tv" )){
            trailerheading.setVisibility( View.GONE );
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.themoviedb.org/3/tv/" ).addConverterFactory( GsonConverterFactory.create() );
            Retrofit retrofit = builder.build();
            callbackListener service = retrofit.create( callbackListener.class );


            Call<Pojofortvshowdetail> call=service.gettvshowdetail( Id+"",MainActivity.api_key,MainActivity.language );
            call.enqueue( new Callback<Pojofortvshowdetail>() {
                @Override
                public void onResponse(Call<Pojofortvshowdetail> call, Response<Pojofortvshowdetail> response) {
                    Pojofortvshowdetail moviedetail = response.body();

                    GlideApp
                            .with( detailactivity.this )
                            .load( "https://image.tmdb.org/t/p/w342/" + moviedetail.getBackdropPath())
                            .thumbnail( Glide.with(detailactivity.this).load(R.drawable.finalplaceholder))
                            .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                            .override( 500,500 )
                            .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
                            .into(backgroundposterdetailactivity  );

                    GlideApp
                            .with( detailactivity.this )
                            .load( "https://image.tmdb.org/t/p/w92/" + moviedetail.getPosterPath())
                            .thumbnail( Glide.with(detailactivity.this).load(R.drawable.finalplaceholder2))
                            .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                            .override( 500,500 )
                            .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
                            .into(posterdetailactivity  );


//


                    detailratingtextview.setText( moviedetail.getVoteAverage()+"" );
                    detailactivityreleasedate.setText( moviedetail.getFirstAirDate() );
                    detailactivityruntime.setText( moviedetail.getEpisodeRunTime()+"" );
                    detailactivityoverview.setText( moviedetail.getOverview() );
                    toolbar.setTitle( moviedetail.getName() );
                    moviename.setText( moviedetail.getName() );
                   List<Genretvshow> genere=moviedetail.getGenres();
                    StringBuilder generetoshow= new StringBuilder();
                    for(int k=0;k<genere.size();k++){
                        if(generemap.allgenre().containsKey(  genere.get( k ).getId())){
                            generetoshow.append( " " ).append( generemap.allgenre().get( genere.get( k ).getId() ) );
                        }
                    }

                   moviegenere.setText( generetoshow);
                    setSupportActionBar( toolbar );



                }

                @Override
                public void onFailure(Call<Pojofortvshowdetail> call, Throwable t) {

                }
            } );




        Call<Pojotvcast> callfortvcast=service.gettvcast( Id + "/credits", MainActivity.api_key, MainActivity.language);
        callfortvcast.enqueue( new Callback<Pojotvcast>() {
            @Override
            public void onResponse(Call<Pojotvcast> call, Response<Pojotvcast> response) {

                final List<Casttv> castdata = response.body().getCast();
                adaptortvcast adaptortvcast = new adaptortvcast( detailactivity.this, castdata, new itemclicklistener() {
                    @Override
                    public void myclick(View view, int position) {
                        Toast.makeText( getApplicationContext(), "item" + position, Toast.LENGTH_SHORT ).show();
                        castdetail( castdata.get( position ).getId() );

                    }


                } );
                LinearLayoutManager manager = new LinearLayoutManager( detailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                castrecyclerview.addItemDecoration( new DividerItemDecoration( detailactivity.this, DividerItemDecoration.VERTICAL ) );
                castrecyclerview.setLayoutManager( manager );
                castrecyclerview.setAdapter( adaptortvcast );





            }

            @Override
            public void onFailure(Call<Pojotvcast> call, Throwable t) {

            }
        } );


        Call<Pojosimiliartvshows> callforsimiliartvshows=service.getSimiliartvshows( Id+"/similar", MainActivity.api_key, MainActivity.language,1);
        callforsimiliartvshows.enqueue( new Callback<Pojosimiliartvshows>() {
            @Override
            public void onResponse(Call<Pojosimiliartvshows> call, Response<Pojosimiliartvshows> response) {
                final List<resulttv> resulttvs = response.body().getResults();
                tvshowrecyclerviewadaptor recyclerviewadaptor = new tvshowrecyclerviewadaptor( detailactivity.this, resulttvs,1, new itemclicklistener() {
                    @Override
                    public void myclick(View view, int position) {
                        Toast.makeText( detailactivity.this, "item " + position, Toast.LENGTH_SHORT ).show();
                        detail(resulttvs.get( position ).getId(),"tv");

                    }
                } );

                LinearLayoutManager manager = new LinearLayoutManager(detailactivity.this, LinearLayoutManager.HORIZONTAL, false );

                similiarmovierecyclerview.addItemDecoration( new DividerItemDecoration( detailactivity.this, DividerItemDecoration.VERTICAL ) );
                similiarmovierecyclerview.setLayoutManager( manager );
                similiarmovierecyclerview.setAdapter( recyclerviewadaptor );


            }

            @Override
            public void onFailure(Call<Pojosimiliartvshows> call, Throwable t) {

            }
        } );


       }





    }

    private void castdetail(Long id) {
        Intent intent=new Intent( detailactivity.this,castdetailactivity.class );
        intent.putExtra( "castId",id );
        startActivity( intent );
    }

    public void detail(long mId,String tvormovie){
        Intent intent=new Intent( detailactivity.this,detailactivity.class );
        intent.putExtra( "movieId",mId );
        intent.putExtra( "tvormovie",tvormovie );
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation( this, backgroundposterdetailactivity,"imageTransition");

        startActivity( intent ,options.toBundle());
    }


   public void openyoutube(String id){
       Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
       Intent webIntent = new Intent(Intent.ACTION_VIEW,
               Uri.parse("http://www.youtube.com/watch?v=" + id));
       try {
           startActivity(appIntent);
       } catch (ActivityNotFoundException ex) {
           startActivity(webIntent);
       }

   }





}
