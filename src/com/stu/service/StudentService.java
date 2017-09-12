/**
 * 
 */
package com.stu.service;

import java.sql.Connection;
import java.util.List;

import com.stu.dao.IStudent;
import com.stu.dao.impl.StudentImpl;
import com.stu.db.DataBase;
import com.stu.dto.Student;

/**
 * @author Administrator
 *
 */

public class StudentService {
	Connection con = null;
	IStudent student = new StudentImpl();

	public Student sign(String stuName, String stuNum) throws Exception {
		// TODO Auto-generated method stub
		Student stu = null;
		try {
			con = DataBase.getConn();
			student.setConnection(con);
			stu = student.sign(stuName, stuNum);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return stu;
	}
	public int searchStatus(String stuNum) throws Exception {
		Connection con = null;
		int result=0;
		try {
			con = DataBase.getConn();
			student.setConnection(con);
			result = student.searchStatus(stuNum);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}
	public int getCount(Student  stu) throws Exception {
		Connection con = null;
		int count=0;
		try {
			con = DataBase.getConn();
			student.setConnection(con);
			count=student.getCount(stu);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return count;
	}
	public List<Student> listStuYes(int pageNo, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<Student> sList = null;
		con = DataBase.getConn();
		student.setConnection(con);
		try {
			sList = student.listStuYes(pageNo, pageSize);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return sList;
	}
	public Student getById(String stuNum) throws Exception {
		Connection con = null;
		Student stu = null;
		try {
			con = DataBase.getConn();
			student.setConnection(con);
			stu = student.getById(stuNum);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return stu;
	}
	public List<Student> listStuNo(int pageNo, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<Student> sList = null;
		con = DataBase.getConn();
		student.setConnection(con);
		try {
			sList = student.listStuNo(pageNo, pageSize);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return sList;
	}

	public boolean updateStatus(Student stu) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		con = DataBase.getConn();
		student.setConnection(con);
		try {
			result = student.updateStatus(stu);

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}

		return result;
	}
}
