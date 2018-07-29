package com.example.martin.mt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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


public class upcomingmoviefragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recyclerViewforupcomingmovies;
    RecyclerView recyclerviewnowplayingmovies;
    RecyclerView recyclerviewpopularmovies;
    RecyclerView recyclerviewtopratedmovie;
    TextView viewallupcomingmovies;
    TextView viewallnowplayingmovies;
    TextView viewallpopularmovies;
    TextView viewalltopratedmovies;
    ProgressBar elastic_download_view;
    //    public final String language = "en-US";
//    public final String api_key = "4b5825849f3d9ec57b490089228234eb";
//    public static List<Genre> generelist;

    LinearLayout rootlayout;
    private OnFragmentInteractionListener mListener;


    public upcomingmoviefragment() {
        // Required empty public constructor
    }

    public static upcomingmoviefragment newInstance(String param1, String param2) {
        upcomingmoviefragment fragment = new upcomingmoviefragment();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output = inflater.inflate( R.layout.fragment_upcomingmoviefragment, container, false );


        rootlayout = output.findViewById( R.id.rootlayout );

        elastic_download_view=output.findViewById( R.id.elastic_download_view );
        viewallupcomingmovies=output.findViewById( R.id.viewallupcomingmovies );
        viewallnowplayingmovies=output.findViewById( R.id.viewallnowplaying );
        viewallpopularmovies=output.findViewById( R.id.viewallpopularmovies );
        viewalltopratedmovies=output.findViewById( R.id.viewalltoprated );


        recyclerViewforupcomingmovies = output.findViewById( R.id.recyclerviewupcomingmovies );
        recyclerviewnowplayingmovies=output.findViewById( R.id.recyclerviewnowplayingmovies );
        recyclerviewpopularmovies=output.findViewById( R.id.recyclerviewpopularmovies );
        recyclerviewtopratedmovie=output.findViewById( R.id.recyclerviewtopratedmovies );
        String upcomingmovieurl = "https://api.themoviedb.org/3/movie/";
        fetchdata( upcomingmovieurl, 1, recyclerViewforupcomingmovies,"upcoming" );
        //upcoming movie url and nowplaying base url are same
        fetchdata( upcomingmovieurl,1,recyclerviewnowplayingmovies,"now_playing" );
        fetchdata( upcomingmovieurl,1, recyclerviewpopularmovies,"popular");
        fetchdata( upcomingmovieurl,1, recyclerviewtopratedmovie,"top_rated");

        viewalltopratedmovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewallactivity.class );
                intent.putExtra( "viewall","top_rated" );
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );
            }
        } );

        viewallupcomingmovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewallactivity.class );
                intent.putExtra( "viewall","upcoming" );
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );
            }
        } );

        viewallpopularmovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewallactivity.class );
                intent.putExtra( "viewall","popular" );
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );
            }
        } );

        viewallnowplayingmovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getContext(),viewallactivity.class );
                intent.putExtra( "viewall","now_playing");
                intent.putExtra( "baseurl", "https://api.themoviedb.org/3/movie/");
                startActivity( intent );

            }
        } );





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
        // TODO: Update argument type
        void onFragmentInteraction(Uri uri);
    }

    public  void fetchdata(String url, int pagenumber, final RecyclerView recyclerView,String url2) {


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( url ).addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        callbackListener service = retrofit.create( callbackListener.class );

        Call<Pojoupcomingmovie> call = service.getupcomingmoview( url2,MainActivity.api_key, MainActivity.language, pagenumber );
        call.enqueue( new Callback<Pojoupcomingmovie>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Pojoupcomingmovie> call, Response<Pojoupcomingmovie> response) {
                rootlayout.setVisibility( View.VISIBLE );
                elastic_download_view.setVisibility( View.GONE );
                final List<Result>  resultList = response.body().getResults();
                recyclerviewadaptor recyclerviewadaptor = new recyclerviewadaptor( getContext(), resultList, 1,new itemclicklistener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void myclick(View view, int position) {
                        long mId=resultList.get( position ).getId();
                        Toast.makeText( getContext(), "item " + position, Toast.LENGTH_SHORT ).show();
                      detail(mId);

                    }
                } );
                LinearLayoutManager manager = new LinearLayoutManager( getContext(), LinearLayoutManager.HORIZONTAL, false );

                recyclerView.addItemDecoration( new DividerItemDecoration( Objects.requireNonNull( getContext() ), DividerItemDecoration.VERTICAL ) );
                recyclerView.setLayoutManager( manager );
                recyclerView.setAdapter( recyclerviewadaptor );

            }

            @Override
            public void onFailure(Call<Pojoupcomingmovie> call, Throwable t) {

            }
        } );

    }

   public void detail(long mId){
       Intent intent=new Intent( getContext(),detailactivity.class );
       intent.putExtra( "movieId",mId );
       intent.putExtra( "tvormovie","movie" );
       startActivity( intent );
   }





}
