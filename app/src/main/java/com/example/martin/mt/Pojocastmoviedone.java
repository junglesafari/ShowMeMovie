
package com.example.martin.mt;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Pojocastmoviedone {

    @SerializedName("cast")
    private List<Castmoviedone> mCast;
    @SerializedName("crew")
    private List<Crewmoviedone> mCrew;
    @SerializedName("id")
    private Long mId;

    public List<Castmoviedone> getCast() {
        return mCast;
    }

    public void setCast(List<Castmoviedone> cast) {
        mCast = cast;
    }

    public List<Crewmoviedone> getCrew() {
        return mCrew;
    }

    public void setCrew(List<Crewmoviedone> crew) {
        mCrew = crew;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

}
