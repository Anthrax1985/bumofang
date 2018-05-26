package com.fh.extend.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static String upload(MultipartFile file, HttpServletRequest request, String path, String name) {
		if (file.isEmpty())
			return "";

		try {
			// 文件保存路径
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			String newFileName = name + "." + suffix;
			String urlPath = "upload/" + path + "/" + newFileName;
			String filePath = request.getSession().getServletContext().getRealPath("/") + urlPath;
			// 转存文件
			file.transferTo(new File(filePath));
			return urlPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	//名称不变
	public static String uploadfileNameUnchanged(MultipartFile file, HttpServletRequest request, String path) {
		if (file.isEmpty())
			return "";
		
		try {
			// 文件保存路径
			String fileName = file.getOriginalFilename();
			//String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			//String newFileName = name + "." + suffix;
			String urlPath = "upload/" + path + "/" + fileName;
			String filePath = request.getSession().getServletContext().getRealPath("/") + urlPath;
			//判断文件是否存在，如果是，则删除
			File newFile = new File(filePath);
			if(newFile.exists()){
				System.out.println("new file exists");
				newFile.delete();
			}
			// 转存文件
			file.transferTo(new File(filePath));
			return urlPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
