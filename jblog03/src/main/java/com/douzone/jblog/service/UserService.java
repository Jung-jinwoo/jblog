package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void join(UserVo vo) {
		userRepository.insert(vo);
	}
	
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public void updateUser(UserVo userVo) {
		userRepository.update(userVo);
	}

	public UserVo findByNo(Long userNo) {
		return userRepository.findByNo(userNo);
	}

	public List<UserVo> findId() {
		return userRepository.findId();
	}

	
}
