package com.lyg.dao;

import java.util.List;
import com.lyg.model.Article;
import com.lyg.model.User;

public interface UserMapper {
	public User selectUserByID(int id);
	public List<User> selectUsers(String userName); 
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public List<Article> getUserArticles(int id);
	public User login(User user);
}
