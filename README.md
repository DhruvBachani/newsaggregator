# News Aggregator

## About

The News Aggregator is a project designed to collect news articles from various sources and display them in a unified interface.

## Features

- Aggregates news from multiple sources
- Get articles based on search query
- Get one article by ID

## Available Endpoints

- `GET /articles?search={keyword}` - Retrieves a list of news articles which match the search query.
- `GET /article?id={id}` - Retrieves a specific news article by ID

## Current Status

- There is only one news source implemented: The Guardian API.
- `GET /articles?search={keyword}` returns maximum of 400 articles. Limit is setup to make sure that the application does not run out of memory.
- `GET /article?id={id}` is yet to be implemented.

## Prerequisites

- Java Development Kit (JDK) 21 or higher
- Apache Maven

## Running the Project

1. Run the application:
   ```bash
   mvn spring-boot:run
   ```
2. Open the web browser and go to `http://localhost:8080/articles?search=test` to view the application.

## Configuration

- API key for Guardian API can be configured in the `application.properties` file.
