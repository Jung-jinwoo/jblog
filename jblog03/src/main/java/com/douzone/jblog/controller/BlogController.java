package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("")
	public String blogmain(Model model,
							@PathVariable("id") String id) {
		BlogVo blog = blogService.find(id);
		model.addAttribute("blog", blog);
		return "blog/main";
	}
	
	@RequestMapping("/admin/basic")
	public String blogbasic(@PathVariable("id") String id, Model model) {
		BlogVo blog = blogService.find(id);
		model.addAttribute("blog", blog);
		return "blog/basic";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@PathVariable("id") String id,
						@RequestParam(value="title") String title,
						@RequestParam(value="logo") MultipartFile logo,
						Model model) {
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setTitle(title);
		blogService.restore(blogVo, logo);
		model.addAttribute("blog", blogVo);
		return "/" + blogVo.getId();
	}
	
	@RequestMapping("/admin/category")
	public String blogcategory(@PathVariable("id") String id,
								Model model, CategoryVo categoryVo) {
		categoryVo.setBlogId(id);
		if(categoryVo.getName() == null || categoryVo.getName() == null) {
			List<CategoryVo> list = blogService.findCategory();
			model.addAttribute("category", list);
		} else {
			blogService.insert(categoryVo);
			List<CategoryVo> list = blogService.findCategory();
			model.addAttribute("category", list);
		}
		
		return "blog/category";
	}
	
	@RequestMapping("/admin/delete/{no}")
	public String delete(@PathVariable("id") String id, CategoryVo categoryVo) {
		categoryVo.setBlogId(id);
		blogService.delete(categoryVo);
		
		return "redirect:/"+id+"/admin/category";
	}
	
	@RequestMapping("/admin/write")
	public String blogwrite() {
		return "blog/write";
	}
}
