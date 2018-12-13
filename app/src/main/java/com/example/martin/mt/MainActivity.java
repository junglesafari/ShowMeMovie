package com.example.martin.mt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, upcomingmoviefragment.OnFragmentInteractionListener, tvshowfragment.OnFragmentInteractionListener {
    //RecyclerView recyclerViewforupcomingmovies;
    public static final String language = apikeycontainer.language;
    public static final String api_key = apikeycontainer.api_key;




// LinearLayout rootlayout;
    com.github.clans.fab.FloatingActionButton now_playing_movies;
    FloatingActionButton popular_movies;
    FloatingActionButton upcoming_movies;
    FloatingActionButton toprated_movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        this.getWindow().getDecorView().setBackgroundColor( getResources().getColor( R.color.colorgray ) );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        popular_movies=findViewById( R.id.popularmoviesfb );
        now_playing_movies=findViewById( R.id.nowplayingmoviesfb );
        upcoming_movies=findViewById( R.id.upcomingmoviesfb );
        toprated_movies=findViewById( R.id.topratedmoviesfb);
        popular_movies.setLabelText( "POPULAR MOVIES" );
        popular_movies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                intent.putExtra( "viewall","popular" );
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );
            }
        } );


        now_playing_movies.setLabelText( "NOW PLAYING MOVIES" );
        now_playing_movies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                intent.putExtra( "viewall","now_playing");
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );

            }
        } );

        upcoming_movies.setLabelText( "UPCOMING MOVIES" );
        upcoming_movies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                intent.putExtra( "viewall","upcoming" );
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );
            }
        } );

        toprated_movies.setLabelText( "TOP RATED MOVIES" );
        toprated_movies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                intent.putExtra( "viewall","top_rated" );
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );
            }
        } );




        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        upcomingmoviefragment fragment = new upcomingmoviefragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace( R.id.contianerupcomingmovie, fragment );
        transaction.commit();

//        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.themoviedb.org/3/genre/movie/" ).addConverterFactory( GsonConverterFactory.create() );
//        Retrofit retrofit = builder.build();
//        callbackListener service = retrofit.create( callbackListener.class );
//        Call<Generepojo> call = service.getgenere( api_key, language );
//        call.enqueue( new Callback<Generepojo>() {
//            @Override
//            public void onResponse(Call<Generepojo> call, Response<Generepojo> response) {
//                generelist.addAll( response.body().getGenres() );
//            }
//
//            @Override
//            public void onFailure(Call<Generepojo> call, Throwable t) {
//
//            }
//        } );


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate( R.menu.main, menu );
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected( item );
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            tvshowfragment fragment = new tvshowfragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace( R.id.contianerupcomingmovie, fragment );

            popular_movies.setLabelText( "POPULAR SHOWS" );
            popular_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( MainActivity.this,viewalltvshows.class );
                    intent.putExtra( "baseurltvshow","https://api.themoviedb.org/3/tv/" );
                    intent.putExtra( "url2tvshow","popular" );
                    startActivity( intent );
                }
            } );

            now_playing_movies.setLabelText( "AIRING TODAY" );
            now_playing_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this,viewalltvshows.class );
                    intent.putExtra( "baseurltvshow","https://api.themoviedb.org/3/tv/");
                    intent.putExtra( "url2tvshow","airing_today" );
                    startActivity( intent );
                }
            } );

            upcoming_movies.setLabelText( "TV ON THE AIR" );
            upcoming_movies.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent( MainActivity.this,viewalltvshows.class );
                   intent.putExtra( "baseurltvshow","https://api.themoviedb.org/3/tv/" );
                   intent.putExtra( "url2tvshow","on_the_air" );
                   startActivity( intent );
               }
           } );


            toprated_movies.setLabelText( "TOP RATED SHOWS" );
            toprated_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( MainActivity.this,viewalltvshows.class );
                    intent.putExtra( "baseurltvshow","https://api.themoviedb.org/3/tv/" );
                    intent.putExtra( "url2tvshow","top_rated"  );
                    startActivity( intent );
                }
            } );


            transaction.commit();

        } else if (id == R.id.nav_gallery) {
            upcomingmoviefragment fragment = new upcomingmoviefragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace( R.id.contianerupcomingmovie, fragment );
            popular_movies.setLabelText( "POPULAR MOVIES" );
            popular_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                    intent.putExtra( "viewall","popular" );
                    intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                    startActivity( intent );
                }
            } );


            now_playing_movies.setLabelText( "NOW PLAYING MOVIES" );
            now_playing_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                    intent.putExtra( "viewall","now_playing");
                    intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                    startActivity( intent );

                }
            } );

            upcoming_movies.setLabelText( "UPCOMING MOVIES" );
            upcoming_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                    intent.putExtra( "viewall","upcoming" );
                    intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                    startActivity( intent );
                }
            } );

            toprated_movies.setLabelText( "TOP RATED MOVIES" );
            toprated_movies.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( MainActivity.this,viewallactivity.class );
                    intent.putExtra( "viewall","top_rated" );
                    intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                    startActivity( intent );
                }
            } );


            transaction.commit();


        }  else if (id == R.id.nav_manage) {
            Intent intent = new Intent( MainActivity.this, latestmovie.class );
            startActivity( intent );

        } else if (id == R.id.favouritetv) {

        } else if (id == R.id.favouritemovie) {
            Intent intent=new Intent( MainActivity.this,favouritemovies.class );
            startActivity( intent );

        }else if(id == R.id.demo_youtube_videos){
            Intent intent = new Intent( MainActivity.this,demovideorecyclerview.class );
             startActivity( intent );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}



