package com.dhruv.newsaggregator.services.interfaces;

import java.util.List;

import com.dhruv.newsaggregator.models.Article;

public interface ArticleServiceInterface {

    List<Article> getArticles(String searchParam);

    Article getArticle(String articleId, String source);

}
