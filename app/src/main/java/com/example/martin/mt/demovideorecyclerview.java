package com.example.martin.mt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Vector;

public class demovideorecyclerview extends AppCompatActivity {

    RecyclerView videorecyclerview;
    //vectors for video urls
    Vector<pojofordemovideo> youtubevideo = new Vector<>();
    adaptordemovideorecyclerview videorecyclerviewadaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_demovideorecyclerview );


        videorecyclerview = findViewById( R.id.videolist );
        //loading youtube video list by embed code;\
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ywJWVth2t7Y\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0xhK7yBYWCY\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ltGTy2gbt6A\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/LAVZ5RTPvGM\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/19lTLv2VguI\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/iyLRJbf1Bwg\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Hvk6OED7ZVs\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );
        youtubevideo.add( new pojofordemovideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eTls6-julhU\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" ) );


        videorecyclerviewadaptor = new adaptordemovideorecyclerview( this, youtubevideo );
        videorecyclerview.setAdapter( videorecyclerviewadaptor );

        videorecyclerview.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );

        LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );

        videorecyclerview.setLayoutManager( manager );














    }
}
