package com.example.REST_API_TEST.domain.article.response;

import com.example.REST_API_TEST.domain.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private  final ArticleDTO article;
}