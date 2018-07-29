
package com.example.martin.mt;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Pojocasttvdone {

    @SerializedName("cast")
    private List<Casttvdone> mCast;
    @SerializedName("crew")
    private List<Crew> mCrew;
    @SerializedName("id")
    private Long mId;

    public List<Casttvdone> getCast() {
        return mCast;
    }

    public void setCast(List<Casttvdone> cast) {
        mCast = cast;
    }

    public List<Crew> getCrew() {
        return mCrew;
    }

    public void setCrew(List<Crew> crew) {
        mCrew = crew;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

}
