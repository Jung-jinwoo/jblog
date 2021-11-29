package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public BlogVo find(String id) {
		return sqlSession.selectOne("blog.find", id);
	}

	public boolean update(BlogVo blogVo) {
		return 1 == sqlSession.update("blog.update", blogVo);
		
	}

	public boolean insert(CategoryVo categoryVo) {
		return 1 == sqlSession.insert("blog.insert", categoryVo);
	}
	
	public List<CategoryVo> findCategory(){
		return sqlSession.selectList("blog.findCategory");
	}

	public boolean delete(CategoryVo categoryVo) {
		return 1 == sqlSession.delete("blog.delete", categoryVo);
		
	}

}
