package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.UserRepositoryException;
import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;

	public boolean update(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);
		return count == 1;
	}

	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public UserVo findByIdAndPassword(String id, String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);

		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}

	public UserVo findById(String id) {

		return sqlSession.selectOne("user.findById", id);
	}

	public boolean insert(UserVo userVo) throws UserRepositoryException {
		int count = sqlSession.insert("user.insert", userVo);
		return count == 1;
	}

	public boolean delete(UserVo userVo) {
		int count = sqlSession.delete("user.delete", userVo);
		return count == 1;
	}

	public List<UserVo> findId() {
		return sqlSession.selectList("user.findId");
	}

}
