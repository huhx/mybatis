package com.tomhu.services;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tomhu.dao.UserDao;
import com.tomhu.model.User;

public class UserTest {

	private static SqlSessionFactory factory = null;

	@Before
	public void createSqlSessionFactory() throws IOException {
		// 1. 读取配置文件
		String resource = "mybatis/SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resource);

		// 2. 根据配置文件生成sqlSession工厂
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(reader);
	}

	/**
	 * 插入用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void insertUser() throws Exception {

		// 3. 根据session工厂生成session
		SqlSession session = factory.openSession();

		// 4. 根据userDaoMapping.xml文件得到用户的Dao
		UserDao userDao = session.getMapper(UserDao.class);

		User user = new User();
		user.setUserId(1);
		user.setUserName("tomhu");
		user.setPassword("linux");
		user.setComment("I love java");

		userDao.insert(user);
		System.out.println("记录条数：" + userDao.countAll());

		// 提交session并关闭
		session.commit();
		session.close();
	}

	/**
	 * 更新用户
	 */
	@Test
	public void updateUser() {
		// 3. 根据session工厂生成session
		SqlSession session = factory.openSession();

		// 4. 根据userDaoMapping.xml文件得到用户的Dao
		UserDao userDao = session.getMapper(UserDao.class);

		// 5. 根据用户的id得到用户
		User user = userDao.findByUserId(1);

		user.setUserName("胡红翔");
		user.setComment("comment is changed!");
		userDao.update(user);

		// 提交session并关闭
		session.commit();
		session.close();
	}

	/**
	 * 删除用户
	 */
	@Test
	public void deleteUser() {
		// 3. 根据session工厂生成session
		SqlSession session = factory.openSession();

		// 4. 根据userDaoMapping.xml文件得到用户的Dao
		UserDao userDao = session.getMapper(UserDao.class);

		int deleteNumber = userDao.delete(1);
		System.out.println(deleteNumber);
		
		// 提交session并关闭
		session.commit();
		session.close();
	}

	@After
	public void closeSesssionFactory() {
	}
}