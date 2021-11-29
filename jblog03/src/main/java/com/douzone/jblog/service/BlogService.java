package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.GalleryRepositoryException;
import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class BlogService {

	private static String SAVE_PATH = "/blog-images";
	private static String URL_BASE = "/images";
	
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo find(String id) {
		return blogRepository.find(id);
	}

	/*
	 * public void update(BlogVo blogVo) { blogRepository.update(blogVo);
	 * 
	 * }
	 */

	public void insert(CategoryVo categoryVo) {
		blogRepository.insert(categoryVo);
		
	}
	
	public List<CategoryVo> findCategory() {
		return blogRepository.findCategory();
	}
	
	public void delete(CategoryVo categoryVo) {
		blogRepository.delete(categoryVo);
	}
	
public void restore(BlogVo blogVo, MultipartFile file) throws GalleryRepositoryException{
		
		try {
			if(file.isEmpty()) {
				throw new GalleryRepositoryException("File Not Uploaded...");
			}

			UUID id = UUID.randomUUID();

			String origin = file.getOriginalFilename();
			String extName = origin.substring(origin.lastIndexOf('.') + 1);
			String saveName = id + "." + extName;

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveName);
			os.write(data);
			os.close();
		
			blogVo.setLogo(URL_BASE + "/" + saveName);
			blogRepository.update(blogVo);
			
		} catch (IOException e) {
			throw new GalleryRepositoryException("File Not Uploaded" + e);
		}
	}



}
