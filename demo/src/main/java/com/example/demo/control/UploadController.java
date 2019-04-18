package com.example.demo.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	@RequestMapping("/upload")
	public String uplaod(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		System.out.println("文件类型ContentType=" + file.getContentType());
		System.out.println("文件组件名称Name=" + file.getName());
		System.out.println("文件原名称OriginalFileName=" + file.getOriginalFilename());
		System.out.println("文件大小Size=" + file.getSize() / 1024 + "KB");
		
		if (!file.isEmpty()) {
			try
			{
				String path  = ResourceUtils.getURL("classpath:static").getPath() + "/upload/";
				System.out.println("path = " + path);
				
				// 获取上传的文件名称，并结合存放路径，构建新的文件名称
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);
				
				// 判断路径是否存在，不存在则新创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				
				// 将上传文件保存到目标文件目录
				file.transferTo(new File(path + File.separator + filename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "上传文件成功!";
		} else {
			return "error";
		}
	}
}