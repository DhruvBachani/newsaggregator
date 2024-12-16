package com.dhruv.newsaggregator.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuardianSingleItemResponse {

    @JsonProperty("response")
    private ResponseData response;

    @Getter
    @Setter
    public static class ResponseData {
        private String status;
        private String pageSize;
        private String currentPage;
        private String orderBy;
        private String pages;

        @JsonProperty("results")
        private List<GuardianArticle> results;
    }
}
