package com.dhruv.newsaggregator.services.implementations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhruv.newsaggregator.models.Article;
import com.dhruv.newsaggregator.services.interfaces.ArticleProviderInterface;

class ArticleServiceTest {

    @Mock
    private ArticleProviderInterface provider1, provider2;

    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articleService = new ArticleService(Arrays.asList(provider1, provider2));
    }

    @Test
    void testGetArticles() {
        // Mock Data
        String searchParam = "test";
        Article article1 = new Article("title1", "source1", "section1", "url1", null, null);
        Article article2 = new Article("title2", "source2", "section2", "url2", null, null);
        when(provider1.getArticles(searchParam)).thenReturn(Arrays.asList(article1));
        when(provider2.getArticles(searchParam)).thenReturn(Arrays.asList(article2));

        // When
        List<Article> articles = articleService.getArticles(searchParam);

        // Then
        assertEquals(2, articles.size());
        assertTrue(articles.contains(article1));
        assertTrue(articles.contains(article2));
    }
}
