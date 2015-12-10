package com.tomhu.dao;

import java.util.List;

import com.tomhu.model.User;

public interface UserDao {

	public int insert(User user);

	public int update(User user);

	/*
	 * ɾ���ɹ�����1��������0.
	 */
	public int delete(int userId);  

	public List<User> selectAll();

	public int countAll();

	public User findByUserId(int userId);
}
