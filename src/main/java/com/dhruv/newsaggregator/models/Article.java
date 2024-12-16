package com.dhruv.newsaggregator.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {

    String title;
    String source;
    String section;
    String link;
    Date publicationDate;
    String content;

}
