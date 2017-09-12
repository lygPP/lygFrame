package com.lyg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyg.common.vo.ResultVo;
import com.lyg.dao.UserMapper;
import com.lyg.model.Article;
import com.lyg.model.User;
import com.lyg.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;
	
	public List<Article> getUserArticles(int id){
		return userMapper.getUserArticles(id);
	}

	public Map<String, Object> registerUser(User user) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> users = userMapper.selectUsers(user.getUserName());
		if(users!=null && users.size()>0){
			resultMap.put(ResultVo.RESULT_CODE, ResultVo.RESULT_CODE_FAIL);
			resultMap.put(ResultVo.RESULT_MSG, ResultVo.RESULT_MSG_FAIL);
			resultMap.put(ResultVo.RESULT_DETAIL, "用户名已存在！");
			return resultMap;
		}else {
			userMapper.addUser(user);
			resultMap.put(ResultVo.RESULT_CODE, ResultVo.RESULT_CODE_SUECCESS);
			resultMap.put(ResultVo.RESULT_MSG, ResultVo.RESULT_MSG_SUECCESS);
			return resultMap;
		}
	}

	public Map<String, Object> login(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userName = (String)params.get("userName");
		String password = (String)params.get("password");
		if(userName!=null && !"".equals(userName) && password!=null && !"".equals(password)){
			User user = new User();
			user.setPassword(password);
			user.setUserName(userName);
			user = userMapper.login(user);
			if(user!=null){
				resultMap.put(ResultVo.RESULT_CODE, ResultVo.RESULT_CODE_SUECCESS);
				resultMap.put(ResultVo.RESULT_MSG, ResultVo.RESULT_MSG_SUECCESS);
				resultMap.put("user", user);
				return resultMap;
			}else {
				resultMap.put(ResultVo.RESULT_CODE, ResultVo.RESULT_CODE_FAIL);
				resultMap.put(ResultVo.RESULT_MSG, ResultVo.RESULT_MSG_FAIL);
				resultMap.put(ResultVo.RESULT_DETAIL, "登录失败！用户名或密码错误！");
				return resultMap;
			}
		}else {
			resultMap.put(ResultVo.RESULT_CODE, ResultVo.RESULT_CODE_FAIL);
			resultMap.put(ResultVo.RESULT_MSG, ResultVo.RESULT_MSG_FAIL);
			resultMap.put(ResultVo.RESULT_DETAIL, "用户名或密码为空！");
			return resultMap;
		}
	}
}
