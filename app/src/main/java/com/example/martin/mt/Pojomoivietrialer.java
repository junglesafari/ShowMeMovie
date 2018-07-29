
package com.example.martin.mt;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pojomoivietrialer {

    @SerializedName("id")
    private Long mId;
    @SerializedName("results")
    private List<Resultmovietrailer> mResults;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Resultmovietrailer> getResults() {
        return mResults;
    }

    public void setResults(List<Resultmovietrailer> results) {
        mResults = results;
    }

}
