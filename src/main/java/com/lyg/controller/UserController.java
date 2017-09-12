package com.lyg.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lyg.common.vo.RegisterForm;
import com.lyg.common.vo.ResultVo;
import com.lyg.common.vo.SystemVo;
import com.lyg.model.Article;
import com.lyg.model.User;
import com.lyg.service.ArticleService;
import com.lyg.service.UserService;
import com.lyg.service.impl.FileUploadServiceImpl;

@Controller
@RequestMapping("/user")
@Api(value = "user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    private FileUploadServiceImpl fileUploadServiceImpl;

    @ResponseBody
    @RequestMapping("/listArticle")
    @ApiOperation(value = "获取文章列表",httpMethod ="POST",notes="获取所有文章列表信息")
    public List<Article> getUserArticles(){
    	List<Article> articles = articleService.getAllArticles(); 
    	return articles;
    }
    
    @ResponseBody
    @RequestMapping("/getUserById")
    @ApiOperation(value = "获取用户信息",httpMethod ="POST",notes="根据id获取用户信息")
    public User getUserById(@ApiParam(required = true,value = "用户id")@RequestParam int id){
    	User user = new User();
    	user.setId(id);
    	return user;
    }
    
    @RequestMapping(value="/register",method=GET)
    public String register2(RegisterForm registerForm){
    	return "register";
    }
    
    @RequestMapping(value="/register",method=POST)
    public String register(RegisterForm registerForm){
    	User user = registerForm.toUser();
    	Map<String, Object> map = userService.registerUser(user);
    	if ("1".equals((String)map.get(ResultVo.RESULT_CODE))) {
        	return "register";
		}else{
			MultipartFile profilePicture = registerForm.getProfilePicture();
			try {
				profilePicture.transferTo(new File(SystemVo.USER_PIC_PATH+profilePicture.getOriginalFilename()));
		    	return "index";
			} catch (IllegalStateException e) {
				e.printStackTrace();
	        	return "register";
			} catch (IOException e) {
				e.printStackTrace();
	        	return "register";
			}
		}
    }
    
    @RequestMapping(value="/login",method=POST)
    public String login(HttpSession session){
    	return "userIndex";
    }

	public FileUploadServiceImpl getFileUploadServiceImpl() {
		return fileUploadServiceImpl;
	}

	public void setFileUploadServiceImpl(FileUploadServiceImpl fileUploadServiceImpl) {
		this.fileUploadServiceImpl = fileUploadServiceImpl;
	}
}