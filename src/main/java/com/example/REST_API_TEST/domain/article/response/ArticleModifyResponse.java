package com.example.REST_API_TEST.domain.article.response;

import com.example.REST_API_TEST.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleModifyResponse {
    private final Article article;
}