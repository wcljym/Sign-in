package com.stu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stu.dao.IStudent;
import com.stu.db.DataBase;
import com.stu.dto.Student;

public class StudentImpl implements IStudent {

	Connection con = null;

	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con = con;

	}

	public Student getById(String stuNum) throws Exception {
		// TODO Auto-generated method stub
		Student stu = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement("select * from stuInfo where stuNum=?");
			ps.setString(1, stuNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setStuName(rs.getString("stuName"));
				stu.setStuNum(rs.getString("stuNum"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return stu;
	}

	public Student sign(String stuName, String stuNum) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student stu = null;
		try {
			ps = con.prepareStatement("select * from  stuInfo where stuName=? and stuNum=?");
			ps.setString(1, stuName);
			ps.setString(2, stuNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setStuName(rs.getString("stuName"));
				stu.setStuNum(rs.getString("stuNum"));
				stu.setTime(rs.getString("time"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ps.close();
		}
		return stu;
	}

	public List<Student> listStuYes(int pageNo, int pageSize) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student stu = null;
		List<Student> sList = null;
		try {
			ps = con.prepareStatement("select * from stuInfo where status=? limit ?,?");
			ps.setInt(1, 1);
			ps.setInt(2, (pageNo - 1) * pageSize);
			ps.setInt(3, pageSize);
			sList = new ArrayList<Student>();
			rs = ps.executeQuery();
			while (rs.next()) {
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setStuName(rs.getString("stuName"));
				stu.setStuNum(rs.getString("stuNum"));
				stu.setStatus(rs.getInt("status"));
				stu.setSessionName(rs.getString("session_name"));
				sList.add(stu);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ps.close();
		}
		return sList;
	}

	public List<Student> listStuNo(int pageNo, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student stu = null;
		List<Student> sList = null;
		try {
			ps = con.prepareStatement("select * from stuInfo where status=? limit ?,?");
			ps.setInt(1, 0);
			ps.setInt(2, (pageNo - 1) * pageSize);
			ps.setInt(3, pageSize);
			sList = new ArrayList<Student>();
			rs = ps.executeQuery();
			while (rs.next()) {
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setStuName(rs.getString("stuName"));
				stu.setStuNum(rs.getString("stuNum"));
				stu.setStatus(rs.getInt("status"));
				stu.setSessionName(rs.getString("session_name"));

				sList.add(stu);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ps.close();
		}
		return sList;
	}

	public boolean updateStatus(Student stu) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		boolean boo = false;
		try {
			ps = con.prepareStatement("update stuInfo set status=?,time=?,session_name=? where stuNum=? ");
			ps.setInt(1, 1);
			// java.sql.Date time=(java.sql.Date) stu.getTime();
			ps.setString(2, stu.getTime());
			ps.setString(3, stu.getSessionName());
			ps.setString(4, stu.getStuNum());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				boo = true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			ps.close();
		}

		return boo;
	}

	public int getCount(Student stu) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from stuInfo where status=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, stu.getStatus());
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			ps.close();
		}
		return count;
	}

	public int searchStatus(String stuNum) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			ps = con.prepareStatement("select status  from stuInfo where stuNum=?");
			ps.setString(1, stuNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return result;
	}

}
