package com.example.REST_API_TEST.domain.article.controller;

import com.example.REST_API_TEST.domain.article.dto.ArticleDTO;
import com.example.REST_API_TEST.domain.article.entity.Article;
import com.example.REST_API_TEST.domain.article.request.ArticleCreateRequest;
import com.example.REST_API_TEST.domain.article.request.ArticleModifyRequest;
import com.example.REST_API_TEST.domain.article.response.ArticleCreateResponse;
import com.example.REST_API_TEST.domain.article.response.ArticleModifyResponse;
import com.example.REST_API_TEST.domain.article.response.ArticleResponse;
import com.example.REST_API_TEST.domain.article.response.ArticlesResponse;
import com.example.REST_API_TEST.domain.article.service.ArticleService;
import com.example.REST_API_TEST.global.rsdata.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/articles")
@RequiredArgsConstructor
@Tag(name = "ApiV1ArticleController", description = "게시글 CRUD API")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("")
    @Operation(summary = "게시글 다건 조회")
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleList = this.articleService.getList();

        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 단건 조회")
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);

        if (article == null) return RsData.of(
                "404",
                "게시글이 존재하지 않습니다.",
                null
        );

        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }

    @PostMapping("")
    @Operation(summary = "게시글 등록")
    public RsData<ArticleCreateResponse> create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        Article article = this.articleService.write(articleCreateRequest.getSubject(), articleCreateRequest.getContent(), articleCreateRequest.getAuthor());

        return RsData.of("200", "등록성공", new ArticleCreateResponse(article));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "게시글 수정")
    public RsData<ArticleModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.getArticle(id);

        if (article == null) return RsData.of(
                "404",
                "게시글이 존재하지 않습니다.",
                null
        );

        article = this.articleService.update(article, articleModifyRequest.getSubject(), articleModifyRequest.getContent(), articleModifyRequest.getAuthor());

        return RsData.of("200", "수정성공", new ArticleModifyResponse(article));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제")
    public RsData<ArticleResponse> delete(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);

        if (article == null) return RsData.of(
                "404",
                "게시글이 존재하지 않습니다.",
                null
        );

        this.articleService.delete(article);
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "삭제성공", new ArticleResponse(articleDTO));
    }
}