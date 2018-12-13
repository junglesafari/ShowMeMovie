package com.example.martin.mt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;

import java.util.List;
import java.util.Vector;

public class adaptordemovideorecyclerview extends RecyclerView.Adapter<viewholderdemovideo> {

    List<pojofordemovideo> videolist;
    LayoutInflater inflater;
    //clicklistener listener;
    Context context;



    public adaptordemovideorecyclerview(Context context, Vector<pojofordemovideo> videolist) {
        this.videolist=videolist;
        this.context=context;
        // this.listener=listener;
    }

    @NonNull
    @Override
    public viewholderdemovideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
        assert inflater != null;
        View view= inflater.inflate( R.layout.demo_video_layout,parent,false );
        return new viewholderdemovideo( view );
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderdemovideo holder, int position) {

        holder.view.getSettings().setJavaScriptEnabled( true );
        holder.view.setWebChromeClient( new WebChromeClient(){} );
        holder.view.loadData( videolist.get( position ).getVideourl(),"text/html","utf-8" );

    }

    @Override
    public int getItemCount() {
        return videolist.size();
    }




















}
