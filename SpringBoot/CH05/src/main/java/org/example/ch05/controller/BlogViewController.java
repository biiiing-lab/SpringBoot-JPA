package org.example.ch05.controller;

import lombok.RequiredArgsConstructor;
import org.example.ch05.domain.Article;
import org.example.ch05.dto.ArticleResponse;
import org.example.ch05.dto.ArticleViewResponse;
import org.example.ch05.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String findAllArticles(Model model) {
        List<ArticleViewResponse> articleResponseList = blogService.findAll().stream().map(ArticleViewResponse::new).toList();
        model.addAttribute("articles", articleResponseList);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", article);
        }
        return "newArticle";
    }
}
