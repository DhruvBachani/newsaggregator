package com.dhruv.newsaggregator.utils;

public enum GuardianEndpoint {

    CONTENT("search"),
    SINGLE_ITEM("");

    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    private GuardianEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
