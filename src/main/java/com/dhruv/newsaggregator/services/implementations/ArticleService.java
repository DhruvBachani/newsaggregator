package com.dhruv.newsaggregator.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dhruv.newsaggregator.models.Article;
import com.dhruv.newsaggregator.services.interfaces.ArticleProviderInterface;
import com.dhruv.newsaggregator.services.interfaces.ArticleServiceInterface;

@Service
public class ArticleService implements ArticleServiceInterface {

    List<ArticleProviderInterface> articleProviders;

    public ArticleService(List<ArticleProviderInterface> articleProviders) {
        this.articleProviders = articleProviders;
    }

    @Override
    public List<Article> getArticles(String searchParam) {
        List<Article> articles = new ArrayList<>();

        for (ArticleProviderInterface provider : articleProviders) {
            articles.addAll(provider.getArticles(searchParam));
        }
        return articles;
    }

    @Override
    public Article getArticle(String articleId, String source) {

        for (ArticleProviderInterface articleProvider : articleProviders) {
            if (articleProvider.getName().equals(source)) {
                return articleProvider.getArticle(articleId);
            }
        }

        return null;
    }

}
