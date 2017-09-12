package com.lyg.service;

import java.util.List;
import java.util.Map;

import com.lyg.model.Article;
import com.lyg.model.User;

public interface UserService {
	public List<Article> getUserArticles(int id);
	
	public Map<String, Object> registerUser(User user);
	
	public Map<String, Object> login(Map<String, Object> params);
}
