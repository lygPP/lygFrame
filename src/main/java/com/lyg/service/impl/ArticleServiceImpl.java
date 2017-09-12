package com.lyg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyg.dao.ArticleMapper;
import com.lyg.model.Article;
import com.lyg.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	ArticleMapper articleMapper;

	public List<Article> getAllArticles() {
		
		return articleMapper.selectAllArticles();
	}

}
