package com.example.martin.mt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;

public class viewholderdemovideo  extends RecyclerView.ViewHolder  {
    WebView view;


    public viewholderdemovideo(View itemView) {
        super( itemView );
        view=itemView.findViewById(R.id.webview);

    }
}
