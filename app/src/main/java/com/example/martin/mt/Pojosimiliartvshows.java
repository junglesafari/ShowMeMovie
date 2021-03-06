
package com.example.martin.mt;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Pojosimiliartvshows {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<resulttv> mResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<resulttv> getResults() {
        return mResults;
    }

    public void setResults(List<resulttv> results) {
        mResults = results;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
