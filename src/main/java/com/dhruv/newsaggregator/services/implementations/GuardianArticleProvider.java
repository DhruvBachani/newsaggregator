package com.dhruv.newsaggregator.services.implementations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dhruv.newsaggregator.models.Article;
import com.dhruv.newsaggregator.models.GuardianArticle;
import com.dhruv.newsaggregator.models.GuardianSearchResponse;
import com.dhruv.newsaggregator.models.GuardianSearchResponse.ResponseData;
import com.dhruv.newsaggregator.services.interfaces.ArticleProviderInterface;
import com.dhruv.newsaggregator.utils.GuardianEndpoint;

@Component
public class GuardianArticleProvider implements ArticleProviderInterface {

    @Value("${guardian.api.url}")
    private String Base_Url;

    @Value("${guardian.api.key}")
    String apiKey;

    private final RestTemplate restTemplate;
    private String source = "Guardian";

    public GuardianArticleProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String addApiKey(String url) {
        return url + "?api-key=" + apiKey;
    }

    @Override
    public List<Article> getArticles(String searchParam) {

        int currentPage = 0;
        int totalPages;
        String endpoint = addApiKey(Base_Url + GuardianEndpoint.CONTENT.getEndpoint()) + "&q="
                + searchParam
                + "&page-size=200";
        List<Article> articles = new ArrayList<>();

        do {
            currentPage++;
            endpoint += "&page=" + currentPage;
            ResponseData response = restTemplate.getForEntity(endpoint,
                    GuardianSearchResponse.class).getBody().getResponse();

            totalPages = Integer.parseInt(response.getPages());
            for (GuardianArticle guardianArticle : response.getResults()) {
                articles.add(new Article(guardianArticle.getWebTitle(), source, guardianArticle.getSectionId(),
                        guardianArticle.getWebUrl(), Date.from(Instant.parse(guardianArticle.getWebPublicationDate())),
                        null));
            }
        } while (currentPage < totalPages && currentPage < 2);

        return articles;
    }

    @Override
    public Article getArticle(String articleId) {

        return null;
    }

    @Override
    public String getName() {
        return source;
    }
}
