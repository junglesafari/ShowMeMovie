
package com.example.martin.mt;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Pojotvcast {

    @SerializedName("cast")
    private List<Casttv> mCast;
    @SerializedName("crew")
    private List<Crewtv> mCrew;
    @SerializedName("id")
    private Long mId;

    public List<Casttv> getCast() {
        return mCast;
    }

    public void setCast(List<Casttv> cast) {
        mCast = cast;
    }

    public List<Crewtv> getCrew() {
        return mCrew;
    }

    public void setCrew(List<Crewtv> crew) {
        mCrew = crew;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

}
