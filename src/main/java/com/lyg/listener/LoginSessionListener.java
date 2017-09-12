package com.lyg.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.lyg.model.User;
/**
 * 用于监听任何时刻只能在一台机器登陆
 * @author lx
 *
 */
public class LoginSessionListener implements HttpSessionAttributeListener{

	private Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		String name = event.getName();
		if(name.equals("user")){
			if(event.getValue() instanceof User){
				User user = (User)event.getValue();
				if(map.get(user.getUserName())!=null){
					HttpSession session = map.get(user.getUserName());
					session.setAttribute("msg", "您的账号已在别的机器登录！");
				}
				map.put(user.getUserName(), event.getSession());
			}
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		String name = event.getName();
		if(name.equals("user")){
			if(event.getValue() instanceof User){
				User user = (User)event.getValue();
				map.remove(user.getUserName());
			}
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}
	
}
