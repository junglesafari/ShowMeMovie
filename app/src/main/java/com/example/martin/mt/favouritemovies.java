package com.example.martin.mt;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Objects;

public class favouritemovies extends AppCompatActivity {
RecyclerView favouritemoviesrecyclerview;
movieDao fmoviesdao;
List<classmoviestore> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_favouritemovies );
        favouritemoviesrecyclerview=findViewById( R.id.favouritemoviesrecyclerview );
        moviedatabase database = Room.databaseBuilder(getApplicationContext(),moviedatabase.class,"classmoviestore").allowMainThreadQueries().build();
        fmoviesdao= database.getmovieDao();
        movies=fmoviesdao.getmovie();
        Log.d("u",movies.size()+"");
        adaptorforfavouritemovie adaptor=new adaptorforfavouritemovie( this, movies, new itemclicklistener() {
            @Override
            public void myclick(View view, int position) {
               // Toast.makeText( favouritemovies.this,"item clicked at favourite",Toast.LENGTH_SHORT ).show();
                Intent intent=new Intent( favouritemovies.this,detailactivity.class );
                intent.putExtra( "movieId",movies.get(position ).movieId );
                intent.putExtra( "tvormovie","movie" );
                startActivity( intent );

            }
        } );
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager( 2,1);

        favouritemoviesrecyclerview.addItemDecoration( new DividerItemDecoration( Objects.requireNonNull( favouritemovies.this ), DividerItemDecoration.VERTICAL ) );
        favouritemoviesrecyclerview.setLayoutManager( manager );
        favouritemoviesrecyclerview.setAdapter( adaptor );



    }
}
