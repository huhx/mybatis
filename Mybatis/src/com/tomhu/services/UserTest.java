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
		// 1. ��ȡ�����ļ�
		String resource = "mybatis/SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resource);

		// 2. ���������ļ�����sqlSession����
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(reader);
	}

	/**
	 * �����û�
	 * 
	 * @throws Exception
	 */
	@Test
	public void insertUser() throws Exception {

		// 3. ����session��������session
		SqlSession session = factory.openSession();

		// 4. ����userDaoMapping.xml�ļ��õ��û���Dao
		UserDao userDao = session.getMapper(UserDao.class);

		User user = new User();
		user.setUserId(1);
		user.setUserName("tomhu");
		user.setPassword("linux");
		user.setComment("I love java");

		userDao.insert(user);
		System.out.println("��¼������" + userDao.countAll());

		// �ύsession���ر�
		session.commit();
		session.close();
	}

	/**
	 * �����û�
	 */
	@Test
	public void updateUser() {
		// 3. ����session��������session
		SqlSession session = factory.openSession();

		// 4. ����userDaoMapping.xml�ļ��õ��û���Dao
		UserDao userDao = session.getMapper(UserDao.class);

		// 5. �����û���id�õ��û�
		User user = userDao.findByUserId(1);

		user.setUserName("������");
		user.setComment("comment is changed!");
		userDao.update(user);

		// �ύsession���ر�
		session.commit();
		session.close();
	}

	/**
	 * ɾ���û�
	 */
	@Test
	public void deleteUser() {
		// 3. ����session��������session
		SqlSession session = factory.openSession();

		// 4. ����userDaoMapping.xml�ļ��õ��û���Dao
		UserDao userDao = session.getMapper(UserDao.class);

		int deleteNumber = userDao.delete(1);
		System.out.println(deleteNumber);
		
		// �ύsession���ر�
		session.commit();
		session.close();
	}

	@After
	public void closeSesssionFactory() {
	}
}