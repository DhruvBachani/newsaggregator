package com.dhruv.newsaggregator.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhruv.newsaggregator.models.Article;
import com.dhruv.newsaggregator.services.interfaces.ArticleServiceInterface;

@RestController
public class ArticleController {

    private final ArticleServiceInterface articleService;

    public ArticleController(ArticleServiceInterface articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles(@RequestParam String search) {
        return articleService.getArticles(search);
    }

    @GetMapping("/article")
    public Article getArticleById(@RequestParam String id, @RequestParam String source) {
        return articleService.getArticle(id, source);
    }
}
