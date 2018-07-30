package com.example.martin.mt;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viewallactivity extends AppCompatActivity {
RecyclerView rootlayoutviewall;
List<Result> movielist;
    recyclerviewadaptor recyclerviewadaptor;
    int pagenumber=1;
    int viewItem, totalItem, pastVisibleItems;
    boolean isScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_viewallactivity );
        this.getWindow().getDecorView().setBackgroundColor( getResources().getColor( R.color.colorgray ) );
        rootlayoutviewall=findViewById( R.id.rootlayoutviewall );
        movielist=new ArrayList<>(  );

        Intent intent=getIntent();
        final String url2=intent.getStringExtra( "viewall" );
        final String baseurl=intent.getStringExtra( "baseurl" );



            recyclerviewadaptor = new recyclerviewadaptor( viewallactivity.this, movielist, 2,new itemclicklistener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void myclick(View view, int position) {
                    Toast.makeText( viewallactivity.this, "item " + position, Toast.LENGTH_SHORT ).show();
                    detail(movielist.get( position ).getId());
                }
            } );
            final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager( 2,1);

            rootlayoutviewall.addItemDecoration( new DividerItemDecoration( Objects.requireNonNull( viewallactivity.this ), DividerItemDecoration.VERTICAL ) );
            rootlayoutviewall.setLayoutManager( manager );
            rootlayoutviewall.setAdapter( recyclerviewadaptor );

            fetchdata( baseurl,pagenumber,url2 );

            rootlayoutviewall.addOnScrollListener( new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged( recyclerView, newState );
                    isScroll = true;
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled( recyclerView, dx, dy );

                    viewItem = manager.getChildCount();
                    int[] firstVisibleItems = null;
                    firstVisibleItems = manager.findFirstVisibleItemPositions(firstVisibleItems);
                    totalItem = manager.getItemCount();
                    if (firstVisibleItems != null && firstVisibleItems.length > 0) {
                        pastVisibleItems = firstVisibleItems[0];
                    }

                    if (isScroll) {
                        if ((viewItem + pastVisibleItems) >= totalItem) {
                            isScroll = false;
                            pagenumber++;
                            fetchdata( baseurl,pagenumber,url2 );

                }
            }


                }
            } );
    }














    public  void fetchdata(String url, int pagenumber,String url2) {
         Retrofit.Builder builder = new Retrofit.Builder().baseUrl( url ).addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        callbackListener service = retrofit.create( callbackListener.class );

        Call<Pojoupcomingmovie> call = service.getupcomingmoview( url2,MainActivity.api_key, MainActivity.language, pagenumber );
        call.enqueue( new Callback<Pojoupcomingmovie>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Pojoupcomingmovie> call, Response<Pojoupcomingmovie> response) {
                assert response.body() != null;
                movielist.addAll( response.body().getResults() );
                recyclerviewadaptor.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Pojoupcomingmovie> call, Throwable t) {

            }
        } );

    }


    public void detail(long mId){
        Intent intent=new Intent( viewallactivity.this,detailactivity.class );
        intent.putExtra( "movieId",mId );
        intent.putExtra( "tvormovie","movie" );
        startActivity( intent );
    }





}
