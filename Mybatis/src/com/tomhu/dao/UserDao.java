package com.tomhu.dao;

import java.util.List;

import com.tomhu.model.User;

public interface UserDao {

	public int insert(User user);

	public int update(User user);

	/*
	 * 删除成功返回1，否则是0.
	 */
	public int delete(int userId);  

	public List<User> selectAll();

	public int countAll();

	public User findByUserId(int userId);
}
