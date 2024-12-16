package com.dhruv.newsaggregator.services.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dhruv.newsaggregator.models.Article;
import com.dhruv.newsaggregator.models.GuardianArticle;
import com.dhruv.newsaggregator.models.GuardianSearchResponse;
import com.dhruv.newsaggregator.utils.GuardianEndpoint;

public class GuardianArticleProviderTest {

    private GuardianArticleProvider guardianArticleProvider;
    private RestTemplate restTemplate;

    @Value("${guardian.api.url}")
    private static String Base_Url;

    @Value("${guardian.api.key}")
    String apiKey;

    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        guardianArticleProvider = new GuardianArticleProvider(restTemplate);
    }

    @Test
    void testGetArticles() {

        // Mock Response
        GuardianSearchResponse searchResponse = new GuardianSearchResponse();
        searchResponse.setResponse(new GuardianSearchResponse.ResponseData("ok", "developer", "1", "1", "1", List.of(
                new GuardianArticle("article1", "article", "science", "Science", "2024-12-17T09:00:04Z", "Article 1",
                        "https://example.com/article1", null, false, null, null),
                new GuardianArticle("article2", "article", "technology", "Technology", "2024-12-17T09:00:04Z",
                        "Article 2", "https://example.com/article2", null, false, null, null))));

        when(restTemplate.getForEntity(anyString(), eq(GuardianSearchResponse.class)))
                .thenReturn(new ResponseEntity<>(searchResponse, HttpStatus.OK));

        List<Article> articles = guardianArticleProvider.getArticles("test");

        // Assert
        assertEquals(2, articles.size());
        assertEquals("Article 1", articles.get(0).getTitle());
        assertEquals("science", articles.get(0).getSection());
        assertEquals("https://example.com/article1", articles.get(0).getLink());
        assertEquals("Article 2", articles.get(1).getTitle());
        assertEquals("technology", articles.get(1).getSection());
        assertEquals("https://example.com/article2", articles.get(1).getLink());

        String expectedUrl = Base_Url + GuardianEndpoint.CONTENT.getEndpoint() + "?api-key=" + apiKey
                + "&q=test&page-size=200&page=1";
        verify(restTemplate, times(1)).getForEntity(eq(expectedUrl), eq(GuardianSearchResponse.class));
    }
}
