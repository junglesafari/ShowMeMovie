package com.example.martin.mt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class tvshowfragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView airingtvshow;
    RecyclerView tvontheair;
    RecyclerView populartvshows;
    RecyclerView topratedtvshows;
    TextView vallairingtoday;
    TextView valltvontheair;
    TextView vallpopulartvshow;
    TextView valltopratedtvshow;




    LinearLayout rootlayout;
    ProgressBar progressBar;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public tvshowfragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static tvshowfragment newInstance(String param1, String param2) {
        tvshowfragment fragment = new tvshowfragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output = inflater.inflate( R.layout.fragment_tvshowfragment, container, false );
        airingtvshow = output.findViewById( R.id.airingtvshow );
        tvontheair=output.findViewById( R.id.tvontheair );
        populartvshows=output.findViewById( R.id.populartvshows );
        topratedtvshows=output.findViewById( R.id.topratedtvshows );
        rootlayout=output.findViewById( R.id.rootlayouttvshow );
        progressBar=output.findViewById( R.id.progressbartvshow );
         vallairingtoday=output.findViewById( R.id.viewallairingtoday );
         valltvontheair=output.findViewById( R.id.viewalltvontheair );
         vallpopulartvshow=output.findViewById( R.id.viewallpopulartvshows );
         valltopratedtvshow=output.findViewById( R.id.viewalltopratedtvshows );

         final String baseurl = "https://api.themoviedb.org/3/tv/";

        vallairingtoday.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewalltvshows.class );
                intent.putExtra( "baseurltvshow",baseurl );
                intent.putExtra( "url2tvshow","airing_today" );
                startActivity( intent );
            }
        } );
        valltvontheair.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewalltvshows.class );
                intent.putExtra( "baseurltvshow",baseurl );
                intent.putExtra( "url2tvshow","on_the_air" );
                startActivity( intent );
            }
        } );
        vallpopulartvshow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewalltvshows.class );
                intent.putExtra( "baseurltvshow",baseurl );
                intent.putExtra( "url2tvshow","popular" );
                startActivity( intent );
            }
        } );
        valltopratedtvshow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewalltvshows.class );
                intent.putExtra( "baseurltvshow",baseurl );
                intent.putExtra( "url2tvshow","top_rated"  );
                startActivity( intent );
            }
        } );


        fetchdata( baseurl, 1, airingtvshow, "airing_today" );
        fetchdata( baseurl, 1, tvontheair, "on_the_air" );
        fetchdata( baseurl, 1, populartvshows, "popular" );
        fetchdata( baseurl, 1, topratedtvshows, "top_rated" );


        return output;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString() + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void fetchdata(String url, int pagenumber, final RecyclerView recyclerView, String url2) {


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( url ).addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        callbackListener service = retrofit.create( callbackListener.class );

        Call<Pojotvairingtoday> call = service.getairingtodaytvshow( url2, MainActivity.api_key, MainActivity.language, pagenumber );
        call.enqueue( new Callback<Pojotvairingtoday>() {
            @Override
            public void onResponse(Call<Pojotvairingtoday> call, Response<Pojotvairingtoday> response) {
               rootlayout.setVisibility( View.VISIBLE );
               progressBar.setVisibility( View.INVISIBLE );
                final List<resulttv> resulttvs = response.body().getResults();
                tvshowrecyclerviewadaptor recyclerviewadaptor = new tvshowrecyclerviewadaptor( getContext(), resulttvs,1, new itemclicklistener() {
                    @Override
                    public void myclick(View view, int position) {
                        Toast.makeText( getContext(), "item " + position, Toast.LENGTH_SHORT ).show();
               detailactivity(resulttvs.get( position ).getId());

                    }
                } );

                LinearLayoutManager manager = new LinearLayoutManager( getContext(), LinearLayoutManager.HORIZONTAL, false );

                recyclerView.addItemDecoration( new DividerItemDecoration( Objects.requireNonNull( getContext() ), DividerItemDecoration.VERTICAL ) );
                recyclerView.setLayoutManager( manager );
                recyclerView.setAdapter( recyclerviewadaptor );


            }


            @Override
            public void onFailure(Call<Pojotvairingtoday> call, Throwable t) {

            }
        } );


    }
public void detailactivity(Long id){
        Intent intent=new Intent( getContext(),detailactivity.class );
        intent.putExtra( "movieId",id );
        intent.putExtra( "tvormovie","tv" );
        startActivity( intent );

}



}

