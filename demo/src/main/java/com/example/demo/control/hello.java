package com.example.demo.control;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hello {

	@RequestMapping("/hello")
	public String hello(HashMap<String, Object> map) {
		map.put("hello", "欢迎进入HTML页面");
		return "/hello";
	}
	
	@RequestMapping("/UploadFile.html")
	public String uplaod() {
		return "/UploadFile";
	}
}
