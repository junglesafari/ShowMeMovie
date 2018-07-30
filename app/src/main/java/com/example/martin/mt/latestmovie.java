package com.example.martin.mt;
//
//import android.os.Bundle;
//import android.app.Activity;
//
//public class latestmovie extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_latestmovie );
//    }
//}


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class latestmovie extends AppCompatActivity {
    //@BindView(R.id.titlelatestmovie)
    TextView title;
    //@BindView(R.id.overviewlatestmovie)
    TextView overview;
    //@BindView( R.id.runtimelatestmovie )
    TextView runtime;
    //@BindView( R.id.revenuelatestmovie )
    TextView revenue;
    //@BindView( R.id.ratinglatestmovie )
    TextView rating;
    //@BindView( R.id.releasedatelatestmovie )
    TextView releasedate;
    TextView genere;
    ImageView backgroundposterlatestmovie;
    FloatingActionImageView fab;
    TextView seemoreheading;
    TextView seemorebutton;
    TextView overviewheading;

  AppBarLayout app_barlatestmovie;
  Toolbar toolbar1latestmovie;

    private boolean seemoreclicked=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_latestmovie );

//        setSupportActionBar( toolbar );
        fab = findViewById( R.id.fab );
        title=findViewById( R.id.titlelatestmovie );
        overview=findViewById(R.id.overviewlatestmovie);
        runtime=findViewById( R.id.runtimelatestmovie  );
        revenue=findViewById( R.id.revenuelatestmovie );
        rating=findViewById( R.id.ratinglatestmovie );
        releasedate=findViewById( R.id.releasedatelatestmovie );
        genere=findViewById( R.id.generelatestmovie );
        backgroundposterlatestmovie=findViewById( R.id.backgroundposterlatestmovie );
        overviewheading=findViewById( R.id.overviewheading );
        seemorebutton=findViewById( R.id.seemorebottom );
        seemoreheading=findViewById( R.id.seemoreheading );
        app_barlatestmovie=findViewById( R.id.app_barlatestmovie );
        toolbar1latestmovie=findViewById( R.id.toolbar1latestmovie );
        app_barlatestmovie.addOnOffsetChangedListener( new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(i>=-300){
                    toolbar1latestmovie.setVisibility( View.GONE );
                }else {
                    toolbar1latestmovie.setVisibility( View.VISIBLE );
                }
            }
        } );





        Retrofit.Builder builder = new Retrofit.Builder()
                                               .baseUrl( "https://api.themoviedb.org/3/movie/" )
                                               .addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        final callbackListener service = retrofit.create( callbackListener.class );

        Call<Pojolatestmovie> call = service.getlatestmovie( MainActivity.api_key, MainActivity.language );
        call.enqueue( new Callback<Pojolatestmovie>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Pojolatestmovie> call, Response<Pojolatestmovie> response) {
                Pojolatestmovie datatoshow = response.body();
                Log.d( "datatoshow",datatoshow.toString()+"" );
//                Picasso.with( latestmovie.this )
//                        .load("https://image.tmdb.org/t/p/w500/"+ datatoshow.getPosterPath() )
//                        .fit()
//                        .placeholder( R.drawable.imgnotfound )
//                        .into( backgroundposterimage );

                GlideApp
                        .with( latestmovie.this )
                        .load( "https://image.tmdb.org/t/p/w500/"+ datatoshow.getPosterPath())
                        .thumbnail( Glide.with(latestmovie.this).load(R.drawable.finalplaceholder))
                        .transition( DrawableTransitionOptions.withCrossFade( 200 ) )
                        .override( 500,500 )
                        .diskCacheStrategy( DiskCacheStrategy.RESOURCE)
                        .into(backgroundposterlatestmovie );

                Picasso
                        .with( latestmovie.this )
                        .load( "https://image.tmdb.org/t/p/w500/"+datatoshow.getPosterPath() )
                        .fit()
                        .placeholder( R.drawable.ic_perm_media_black_24dp )
                        .into( fab );

                title.setText( datatoshow.getTitle() );

                Log.d( "mmm",datatoshow.getOverview() +"   title "+datatoshow.getTitle()+"image url "+datatoshow.getPosterPath());
                if(datatoshow.getOverview().equals( "" )){
                    overview.setVisibility( View.GONE );
                    overviewheading.setVisibility( View.GONE );
                    seemorebutton.setVisibility( View.GONE );
                    seemoreheading.setVisibility( View.GONE );
                }


                toolbar1latestmovie.setTitle( datatoshow.getTitle() );
                setSupportActionBar( toolbar1latestmovie );

                overview.setText( datatoshow.getOverview() );
                runtime.setText( datatoshow.getRuntime()+" " );
                revenue.setText( datatoshow.getRevenue()+" " );
                rating.setText( datatoshow.getVoteAverage()+" " );
                releasedate.setText( datatoshow.getReleaseDate()+" " );
                List<Genre> genre=datatoshow.getGenres();
                StringBuilder generetoshow= new StringBuilder();
                for(int i=0;i<genre.size();i++){
                    generetoshow.append( genre.get( i ).getName() ).append( " " );
                }
               genere.setText( generetoshow.toString() );

               seemorebutton.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) overview.getLayoutParams();
                       if(seemoreclicked){
                           params.height=15;
                           seemoreclicked=false;
                       }else {
                           seemoreclicked=true;
                           params.height = 100;

                       }
                       overview.setLayoutParams(params);
                   }
               } );








            }

            @Override
            public void onFailure(Call<Pojolatestmovie> call, Throwable t) {

            }
        } );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_scrolling, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
