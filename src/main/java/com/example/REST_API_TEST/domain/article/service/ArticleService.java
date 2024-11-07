package com.example.REST_API_TEST.domain.article.service;

import com.example.REST_API_TEST.domain.article.dto.ArticleDTO;
import com.example.REST_API_TEST.domain.article.entity.Article;
import com.example.REST_API_TEST.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<ArticleDTO> getList() {
        List<Article> articleList = this.articleRepository.findAll();

        List<ArticleDTO> articleDTOList = articleList.stream()
                .map(article -> new ArticleDTO(article))
                .collect(Collectors.toList());

        return articleDTOList;
    }

    public Article getArticle(Long id) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);

        return optionalArticle.orElse(null);
    }

    public Article write(String subject, String content, String author) {
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .build();
        this.articleRepository.save(article);

        return article;
    }

    public Article update(Article article, String content, String subject, String author) {
        article.setSubject(subject);
        article.setContent(content);
        article.setAuthor(author);

        this.articleRepository.save(article);

        return article;
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}