package com.stu.dao;

import java.sql.Connection;
import java.util.List;

import com.stu.dto.Student;

public interface IStudent {

	public void setConnection(Connection con) throws Exception;

	public Student sign(String stuName, String stuNum) throws Exception;

	// �Ѿ�ǩ��
	public List<Student> listStuYes(int pageNo, int pageSize) throws Exception;

	// δǩ��
	public List<Student> listStuNo(int pageNo, int pageSize) throws Exception;

	public boolean updateStatus(Student stu) throws Exception;

	public int getCount(Student stu) throws Exception;

	public Student getById(String  stuNum) throws Exception;
	
	public int searchStatus(String stuNum) throws Exception ;

}
