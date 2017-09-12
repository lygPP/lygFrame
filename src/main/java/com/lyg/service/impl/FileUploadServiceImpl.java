package com.lyg.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadServiceImpl {
	private MultipartFile[] pics;

	public void upload() {
		for (int i = 0; i < pics.length; i++) {
			try {
				pics[i].transferTo(new File("static/images/user/"
						+ pics[i].getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public MultipartFile[] getPics() {
		return pics;
	}

	public void setPics(MultipartFile[] pics) {
		this.pics = pics;
	}
}
