package com.lyg.dao;

import java.util.List;

import com.lyg.model.Article;

public interface ArticleMapper {
	public List<Article> selectAllArticles();
}
