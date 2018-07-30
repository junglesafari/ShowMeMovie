package com.example.martin.mt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface callbackListener {
    @GET
    Call<Pojoupcomingmovie> getupcomingmoview(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language,@Query("page"  ) int pagenumber);

    @GET("list")
    Call<Generepojo> getgenere(@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET("latest")
    Call<Pojolatestmovie> getlatestmovie(@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET
    Call<Pojotvairingtoday> getairingtodaytvshow(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language,@Query("page"  ) int pagenumber);

    @GET
    Call<Pojomoviedetail>   getmoviedetail(@Url String mId,@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET
    Call<Pojomoviecast>   getmoviecast(@Url String mId,@Query( "api_key" ) String api);

    @GET
    Call<Pojoforsimiliarmovies> getsimiliarmovies(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language,@Query("page"  ) int pagenumber);

    @GET
    Call<Pojocastdetail> getCastDetail(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET
    Call<Pojocastmoviedone> getCastDoneMovie(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET
    Call<Pojocasttvdone>   getCastDoneTv(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET
    Call<Pojomoivietrialer> getmovietrailer(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language);

    @GET
    Call<Pojotvcast> gettvcast(@Url String mId,@Query( "api_key" ) String api,@Query("language"  ) String language);

     @GET
    Call<Pojosimiliartvshows> getSimiliartvshows(@Url String url,@Query( "api_key" ) String api,@Query("language"  ) String language,@Query("page"  ) int pagenumber);

     @GET
    Call<Pojofortvshowdetail>  gettvshowdetail(@Url String mId,@Query( "api_key" ) String api,@Query("language"  ) String language);
}

